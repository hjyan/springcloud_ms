/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hongdian.plat.term.exec.thread;

import com.hongdian.plat.term.config.SpringContextUtil;
import com.hongdian.plat.term.constant.SystemConstants;
import com.hongdian.plat.term.exec.devenum.ScheduleTaskStatusEnum;
import com.hongdian.plat.term.mqttmodel.base.FepDownMqttEntity;
import com.hongdian.plat.term.entity.ScheduleTask;
import com.hongdian.plat.term.service.ITaskService;
import java.util.Date;

/** 启动下发命令线程
 * @date 2018-05-17 00:00:00
 * @Description: 
 * @author zyyan
 * Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
@Deprecated
public class ProcessDevCtrlThread implements Runnable{
	
	private ITaskService taskService;
	
	private FepDownMqttEntity entity;

	public ProcessDevCtrlThread(FepDownMqttEntity entity) {
		this.entity = entity;
		taskService = (ITaskService) SpringContextUtil.getBean(ITaskService.class);
	}

	@Override
	public void run() {
		if(entity != null){
			//入库
			ScheduleTask task = new ScheduleTask();
			task.setTermSn(entity.getSn());
			task.setCtrlName(entity.getAction());
			task.setExecStartTime(new Date());
			task.setContent("");
			task.setStatus(ScheduleTaskStatusEnum.NON_EXEC.getStatus());
			task.setCtrlDescription(ScheduleTaskStatusEnum.NON_EXEC.getDescription());
			taskService.insert(task);
			//更新缓存
			SystemConstants.SCHEDULE_TASK_CACHE.put(task.getId(), task);
		}
	}

}
