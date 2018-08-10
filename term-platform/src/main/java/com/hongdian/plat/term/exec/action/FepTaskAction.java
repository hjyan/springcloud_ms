/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.exec.action;

import com.hongdian.plat.term.constant.SystemConstants;
import com.hongdian.plat.term.exec.devenum.ScheduleTaskStatusEnum;
import com.hongdian.plat.term.mqttmodel.base.FepReturnMessage;
import com.hongdian.plat.term.mqttmodel.base.FepUpMqttEntity;
import com.hongdian.plat.term.entity.ScheduleTask;
import com.hongdian.plat.term.exec.devenum.DevCtrlEnum;
import com.hongdian.plat.term.exec.devenum.ErrorEnum;
import com.hongdian.plat.term.service.IFloodVoxAlarmService;
import com.hongdian.plat.term.service.ITaskService;
import com.hongdian.plat.term.util.JSONUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @date 2018-05-29 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
@Component
public class FepTaskAction extends BaseAction {

	@Resource
	ITaskService taskService;

	@Resource
	IFloodVoxAlarmService alarmService;

	@Override
	public void excute(FepUpMqttEntity fep) {
		String cmdId = fep.getCmdId();
		FepReturnMessage returnMsg = (FepReturnMessage) JSONUtils.readValue(fep.getContent(), FepReturnMessage.class);
		ScheduleTask task = SystemConstants.SCHEDULE_TASK_CACHE.get(Integer.valueOf(cmdId));
		log.info("预置任务响应：" + cmdId);
		if (task == null) {
			log.error("缓存任务" + cmdId + "没有找到");
			return;
		}
		task.setFailCode(returnMsg.getCode());
		task.setFailMsg(ErrorEnum.findErrorMsgByCode(returnMsg.getCode()));
		if (ErrorEnum.SUCCEESS.getErrorCode().equals(returnMsg.getCode())) {
			task.setStatus(ScheduleTaskStatusEnum.SUCCESS.getStatus());
		} else {
			task.setStatus(ScheduleTaskStatusEnum.FAILURE.getStatus());
		}
		task.setExecStartTime(returnMsg.getBeginTime());
		task.setExecEndTime(returnMsg.getEndTime());
		SystemConstants.SCHEDULE_TASK_CACHE.remove(task.getId());//清除缓存任务
		taskService.updateById(task);//更新任务

		//如果是预警类型任务，则更新预警发布状态(不在这里更新状态，通过观察设备响应情况来判断)
		if (DevCtrlEnum.PLAY_WARN.getCtrlName().equals(task.getCtrlName()) || DevCtrlEnum.PLAY_MP3.getCtrlName().equals(task.getCtrlName())) {
			alarmService.updatePubStatusByTaskId(task.getId());
		}
	}

}
