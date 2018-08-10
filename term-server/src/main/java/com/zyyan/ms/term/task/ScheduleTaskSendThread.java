/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.task;

import com.zyyan.ms.term.config.SpringContextUtil;
import com.zyyan.ms.term.constant.SystemConstants;
import com.zyyan.ms.term.exec.devenum.ScheduleTaskStatusEnum;
import com.zyyan.ms.term.mqttmodel.base.FepDownMqttEntity;
import com.zyyan.ms.term.entity.ScheduleTask;
import com.zyyan.ms.term.exec.FepConsts;
import com.zyyan.ms.term.exec.PlatMqttClient;
import com.zyyan.ms.term.exec.devenum.DevCtrlEnum;
import com.zyyan.ms.term.service.ITaskService;
import com.zyyan.ms.term.util.JSONUtils;
import com.zyyan.ms.common.util.CodeTransUtil;
import com.zyyan.ms.common.util.CommUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @date 2018-05-17 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
public class ScheduleTaskSendThread implements Runnable {

	Logger log = LoggerFactory.getLogger(ScheduleTaskSendThread.class);
	private PlatMqttClient platMqttClient;
	private ITaskService taskService;

	private ScheduleTask task;

	public ScheduleTaskSendThread(ScheduleTask task) {
		platMqttClient = (PlatMqttClient) SpringContextUtil.getBean(PlatMqttClient.class);
		taskService = (ITaskService) SpringContextUtil.getBean(ITaskService.class);
		this.task = task;
	}

	@Override
	public void run() {
		String topic = getTopic();
		String msg = JSONUtils.writeValueAsString(getFepDownMqtt(topic));
		try {
			platMqttClient.sendMessage(msg, task.getSource());
			SystemConstants.OUTPUT_COUNT++;
			task.setStatus(ScheduleTaskStatusEnum.SENDED.getStatus());
			task.setExecStartTime(Calendar.getInstance().getTime());
			SystemConstants.SCHEDULE_TASK_CACHE.put(task.getId(), task);
			//更新数据库
			taskService.updateById(task);
		} catch (Exception e) {
			log.error(null, e);
		}
	}

	private FepDownMqttEntity getFepDownMqtt(String topic) {
		String version = "1.0";
		Object content = task.getContent();
		if (CommUtils.notNull(task.getContent()) && (task.getContent().contains("{") || task.getContent().contains("["))) {
			content = JSONUtils.readValue(task.getContent(), Object.class);
			if (DevCtrlEnum.PLAY_WARN.getCtrlName().equals(task.getCtrlName())) {//预警发布
				//发布内容需要转码
				LinkedHashMap map = (LinkedHashMap) content;
				map.put("playContent", CodeTransUtil.cnToUnicode(map.get("playContent").toString()));
			}
		}
		return new FepDownMqttEntity(topic, task.getCtrlName(), task.getSource(), task.getTermSn(), new Date(), version, null, String.valueOf(task.getId()), "json", content);
	}

	private String getTopic() {
		return "plat/" + FepConsts.platName + "/" + task.getSource();
	}

//    public static void main(String[] args) {
//        ScheduleTask task1 = new ScheduleTask();
//        task1.setContent("{\"a\":\"aasdfsd\"}");
//        
//        Object content = task1.getContent();
//        if (CommUtils.notNull(task1.getContent()) && task1.getContent().contains("{")) {
//            content = JSONUtils.readValue(task1.getContent(), Object.class);
//        }
//        FepDownMqttEntity mqtt = new FepDownMqttEntity("sds", task1.getCtrlName(), task1.getSource(), task1.getTermSn(), new Date(), "dsd", null, String.valueOf(task1.getId()), "json", content);
//        String msg = JSONUtils.writeValueAsString(mqtt);
//        System.out.println(msg);
//    }
}
