/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.exec.action;

import com.zyyan.ms.term.constant.SystemConstants;
import com.zyyan.ms.term.entity.ScheduleTask;
import com.zyyan.ms.term.exec.devenum.ScheduleTaskStatusEnum;
import com.zyyan.ms.term.mqttmodel.base.FepDownMqttEntity;
import com.zyyan.ms.term.service.ITaskService;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @date 2018-05-29 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
@Component
public class FepTaskAckAction {
	
	public final Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource
	ITaskService taskService;

	public void excute(FepDownMqttEntity fep) {
		String cmdId = fep.getCmdId();
		ScheduleTask task = SystemConstants.SCHEDULE_TASK_CACHE.get(Integer.valueOf(cmdId));
		log.info("预置任务确认响应：" + cmdId);
		if (task == null) {
			log.error("缓存任务" + cmdId + "没有找到");
			return;
		}

		if (task.getStatus().intValue() < ScheduleTaskStatusEnum.FAILURE.getStatus()) {
			task.setStatus(ScheduleTaskStatusEnum.EXECING.getStatus());
			SystemConstants.SCHEDULE_TASK_CACHE.put(Integer.valueOf(cmdId), task);
			taskService.updateById(task);//更新任务
		}
	}

}
