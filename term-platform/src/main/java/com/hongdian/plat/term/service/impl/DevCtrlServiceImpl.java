/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.service.impl;

import com.hongdian.plat.term.constant.SystemConstants;
import com.hongdian.plat.term.entity.FloodVoxAlarm;
import com.hongdian.plat.term.entity.FloodVoxRel;
import com.hongdian.plat.term.exec.devenum.DevCtrlEnum;
import com.hongdian.plat.term.exec.devenum.ScheduleTaskStatusEnum;
import com.hongdian.plat.term.entity.ScheduleTask;
import com.hongdian.plat.term.exec.devenum.AlarmPubStatusEnum;
import com.hongdian.plat.term.exec.devenum.AlarmSendTypeEnum;
import com.hongdian.plat.term.mqttmodel.down.GetEventMqttEntity;
import com.hongdian.plat.term.mqttmodel.down.PlayMp3MqttEntity;
import com.hongdian.plat.term.mqttmodel.down.SetParamMqttEntity;
import com.hongdian.plat.term.mqttmodel.down.WarnPubMqttEntity;
import com.hongdian.plat.term.service.IDevCtrlService;
import com.hongdian.plat.term.service.IFloodVoxAlarmService;
import com.hongdian.plat.term.service.IFloodVoxRelService;
import com.hongdian.plat.term.service.ITaskService;
import com.hongdian.plat.term.util.JSONUtils;
import com.hongdian.sys.common.util.CommUtils;
import com.hongdian.sys.common.util.TimeUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @date 2018-05-30 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
@Service
public class DevCtrlServiceImpl implements IDevCtrlService {

	public final Logger log = LoggerFactory.getLogger(this.getClass());

	private final String NULL_CONTENT = "";

	@Resource
	ITaskService taskService;

	@Resource
	IFloodVoxAlarmService alarmService;

	@Resource
	IFloodVoxRelService voxRelService;

	@Override
	public void setParam(String termSn, String source, String content) {
		try {
			//内容校验,防止不满足要求的内容直接下发
//			List<SetParamMqttEntity> list = JSONUtils.readValueAsList(content, SetParamMqttEntity.class);
//			createScheduleTask(termSn, source, DevCtrlEnum.SET_PARAM, JSONUtils.writeValueAsString(list));
			createScheduleTask(termSn, source, DevCtrlEnum.SET_PARAM, content);
		} catch (Exception ex) {
			log.error("参数不合法", ex);
		}
	}

	@Override
	public void getParam(String termSn, String source) {
		if (CommUtils.isNull(termSn)) {
			throw new RuntimeException("请指定termSn");
		}
		createScheduleTask(termSn, source, DevCtrlEnum.GET_PARAM, NULL_CONTENT);
	}

	@Override
	public void reset(String termSn, String source) {
		createScheduleTask(termSn, source, DevCtrlEnum.RESET, NULL_CONTENT);
	}

	@Override
	public void setClock(String termSn, String source, String content) {
		try {
			//先校验content 为日期格式
			createScheduleTask(termSn, source, DevCtrlEnum.SET_CLOCK, content);
		} catch (Exception e) {
			log.error("参数不合法", e);
		}
	}

	@Override
	public void getCurrent(String termSn, String source) {
		createScheduleTask(termSn, source, DevCtrlEnum.GET_CURRENT, NULL_CONTENT);
	}

	@Override
	public void format(String termSn, String source) {
		createScheduleTask(termSn, source, DevCtrlEnum.FORMAT, NULL_CONTENT);
	}

	@Override
	public void startMainUpgrade(String termSn, String source, String content) {
		createScheduleTask(termSn, source, DevCtrlEnum.MAIN_UPGRADE, content);
	}

	@Override
	public void getEvent(String termSn, String source, String beginTime, String endTime) {
		GetEventMqttEntity entity = new GetEventMqttEntity(TimeUtil.convertStringToDate(beginTime, TimeUtil.YMDHMS), TimeUtil.convertStringToDate(endTime, TimeUtil.YMDHMS));
		createScheduleTask(termSn, source, DevCtrlEnum.GET_EVENT, JSONUtils.writeValueAsString(entity));
	}

	@Override
	public void playWarn(String termSn, String source, String content, String gateNum, String title, String level, String playNum, String createPerson, String gates, String alarmType) {
		try {
//            WarnPubMqttEntity entity = (WarnPubMqttEntity) JSONUtils.readValue(content, WarnPubMqttEntity.class);
			String[] strs = termSn.split(",");
			for (int i = 0; i < strs.length; i++) {
				//入库预警
				FloodVoxAlarm alarm = new FloodVoxAlarm();
				alarm.setSendType("gprs");
				if (!CommUtils.isNull(gateNum)) {
					alarm.setGateNum(Integer.parseInt(gateNum));
				}
				alarm.setCreateTime(new Date());
				alarm.setCreatePerson(createPerson);
				alarm.setPubTime(new Date());
				alarm.setPubStatus(AlarmPubStatusEnum.alarm_new.getPubStatus());
				if (!CommUtils.isNull(title)) {
					alarm.setTitle(title);
				}
				if (!CommUtils.isNull(alarmType)) {
					if ("alarmPub".equals(alarmType.trim())) {
						alarm.setAlarmType("预警发布");
					}
				}
				if (!CommUtils.isNull(level)) {
					alarm.setLevel(Integer.parseInt(level));
				}
				if (!CommUtils.isNull(content)) {
					alarm.setContent(content);
				}
				if (!CommUtils.isNull(playNum)) {
					alarm.setPlayNum(Integer.parseInt(playNum));
				}
				alarmService.insert(alarm);//添加预警记录
				//添加被通知终端记录
				FloodVoxRel voxRel = new FloodVoxRel();
				voxRel.setId(alarm.getId());
				voxRel.setGate(strs[i]);
				voxRelService.insertFloodVoxRel(voxRel);

				WarnPubMqttEntity entity = new WarnPubMqttEntity("gprs", String.valueOf(alarm.getId()), playNum, "3", content);//playInterval 默认 1分钟，后面要在 页面上传入字段
				int taskId = createScheduleTask(strs[i], source, DevCtrlEnum.PLAY_WARN, JSONUtils.writeValueAsString(entity));
				//更新 taskId 到 alarm
				alarm.setTaskId(taskId);
				alarmService.updateById(alarm);
			}
		} catch (Exception e) {
			log.error("playWarn 指令 参数不合法", e);
		}
	}

	@Override
	public void playMp3(String termSn, String source, String content, String gateNum, String title, String level, String playNum, String createPerson, String fileType, String gates, String alarmType) {
		//发布预置任务  这里应该采用content进行组装，后期再改
//        PlayMp3MqttEntity entity = (PlayMp3MqttEntity) JSONUtils.readValue(content, PlayMp3MqttEntity.class);
		String[] strs = termSn.split(",");
		for (int i = 0; i < strs.length; i++) {
			//入库预警
			FloodVoxAlarm alarm = new FloodVoxAlarm();
			alarm.setSendType("gprs");
			if (!CommUtils.isNull(gateNum)) {
				alarm.setGateNum(Integer.parseInt(gateNum));
			}
			alarm.setCreateTime(new Date());
			alarm.setCreatePerson(createPerson);
			alarm.setPubTime(new Date());
			alarm.setPubStatus(AlarmPubStatusEnum.alarm_new.getPubStatus());
			if (!CommUtils.isNull(title)) {
				alarm.setTitle(title);
			}
			if (!CommUtils.isNull(level)) {
				alarm.setLevel(Integer.parseInt(level));
			}
			if (!CommUtils.isNull(content)) {
				alarm.setContent(content);
			}
			if (!CommUtils.isNull(alarmType)) {
				if ("mp3".equals(alarmType.trim())) {
					alarm.setAlarmType("MP3点播");
				}
			}
			if (!CommUtils.isNull(playNum)) {
				alarm.setPlayNum(Integer.parseInt(playNum));
			}
			if (!CommUtils.isNull(fileType)) {
				alarm.setFileType(fileType);
			}
			//添加预警记录
			alarmService.insert(alarm);
			//添加被通知终端记录
			FloodVoxRel voxRel = new FloodVoxRel();
			voxRel.setId(alarm.getId());
			voxRel.setGate(strs[i]);
			voxRelService.insertFloodVoxRel(voxRel);

			PlayMp3MqttEntity entity = new PlayMp3MqttEntity("gprs", String.valueOf(alarm.getId()), fileType);
			int taskId = createScheduleTask(strs[i], source, DevCtrlEnum.PLAY_MP3, JSONUtils.writeValueAsString(entity));
			//更新 taskId 到 alarm
			alarm.setTaskId(taskId);
			alarmService.updateById(alarm);
		}
	}

	@Override
	public void upgradeMp3(String termSn, String source, String content) {
		createScheduleTask(termSn, source, DevCtrlEnum.UPGRADE_MP3, content);
	}

	private Integer createScheduleTask(String termSn, String source, DevCtrlEnum devCtrlEnum, String content) {
		ScheduleTask task = new ScheduleTask();
		task.setSource(source);
		task.setCtrlName(devCtrlEnum.getCtrlName());
		task.setCtrlDescription(devCtrlEnum.getCtrlDescription());
		task.setTermSn(termSn);
		task.setStatus(ScheduleTaskStatusEnum.NON_EXEC.getStatus());
		task.setContent(content);

		//将预置任务更新到缓存
		try {
			taskService.insert(task);
			SystemConstants.SCHEDULE_TASK_CACHE.put(task.getId(), task);
		} catch (Exception ex) {
			log.error(null, ex);
		}
		return task.getId();
	}

	@Override
	public void deleteAlarm(String id, String gate) {
		int ids = Integer.parseInt(id);
		FloodVoxAlarm alarm = alarmService.selectById(ids);
		//删除任务
		taskService.deleteById(alarm.getTaskId());
		HashMap<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", ids);
		queryMap.put("gate", gate);
		voxRelService.deletefloodVoxRelByIdAndGate(queryMap);
		alarmService.deleteById(ids);
	}

}
