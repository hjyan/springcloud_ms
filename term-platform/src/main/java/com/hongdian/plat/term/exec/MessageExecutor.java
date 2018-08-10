/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.exec;

import com.hongdian.plat.term.config.SpringContextUtil;
import com.hongdian.plat.term.exec.action.AlarmStatusAction;
import com.hongdian.plat.term.exec.action.BaseAction;
import com.hongdian.plat.term.exec.action.DevEventAction;
import com.hongdian.plat.term.exec.action.DevLoginAction;
import com.hongdian.plat.term.exec.action.DevLogoutAction;
import com.hongdian.plat.term.exec.action.DevParamAction;
import com.hongdian.plat.term.exec.action.DevRegisterAction;
import com.hongdian.plat.term.exec.action.DevStatusAction;
import com.hongdian.plat.term.exec.action.FepLoginAction;
import com.hongdian.plat.term.exec.action.FepLogoutAction;
import com.hongdian.plat.term.exec.action.FepTaskAckAction;
import com.hongdian.plat.term.exec.action.FepTaskAction;
import com.hongdian.plat.term.exec.devenum.DevTopicEnum;
import com.hongdian.plat.term.mqttmodel.base.FepDownMqttEntity;
import com.hongdian.plat.term.mqttmodel.base.FepUpMqttEntity;
import com.hongdian.plat.term.util.JSONUtils;
import com.hongdian.sys.common.util.CommUtils;
import org.springframework.stereotype.Component;

/**
 *
 * @author win 10
 */
@Component
public class MessageExecutor {

	public static int handleCount = 1;

	public void handleMsg(FepUpMqttEntity fep) {
		BaseAction action = null;
		if (DevTopicEnum.FEP_ACK.getAction().equals(fep.getAction())) {//fep 收到消息确认
			String contentFep = fep.getContent();
			try {
				FepDownMqttEntity innerFep = JSONUtils.readValue(contentFep, FepDownMqttEntity.class);
				if (CommUtils.notNull(innerFep.getCmdId())) {
					//更新预置任务状态
					FepTaskAckAction ackAction = (FepTaskAckAction) SpringContextUtil.getBean(FepTaskAckAction.class);
					ackAction.excute(innerFep);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (CommUtils.notNull(fep.getCmdId())) {//判断是否为预置任务回复
			action = (FepTaskAction) SpringContextUtil.getBean(FepTaskAction.class);
			if (action != null) {
				action.excute(fep);
			}
		} else if (CommUtils.notNull(fep.getAction())) {
			DevTopicEnum topicEnum = DevTopicEnum.findByAction(fep.getAction());
			if (topicEnum != null) {
				switch (topicEnum) {
					case FEP_LOGIN:
						action = (FepLoginAction) SpringContextUtil.getBean(FepLoginAction.class);
						break;
					case FEP_LOGOUT:
						action = (FepLogoutAction) SpringContextUtil.getBean(FepLogoutAction.class);
						break;
					case DEV_REGISTER:
					case YJGB_DEV_REGISTER:
						action = (DevRegisterAction) SpringContextUtil.getBean(DevRegisterAction.class);
						break;
					case DEV_LOGIN:
					case YJGB_DEV_LOGIN:
						action = (DevLoginAction) SpringContextUtil.getBean(DevLoginAction.class);
						break;
					case DEV_LOGOUT:
					case YJGB_DEV_LOGOUT:
						action = (DevLogoutAction) SpringContextUtil.getBean(DevLogoutAction.class);
						break;
					case DEV_TEST:
					case DEV_TIMER:
					case DEV_ALARM:
					case DEV_STATUS:
					case YJGB_DEV_TIMER:
						action = (DevStatusAction) SpringContextUtil.getBean(DevStatusAction.class);
						break;
					case DEV_PARAM:
						action = (DevParamAction) SpringContextUtil.getBean(DevParamAction.class);
						break;
					case DEV_EVENT:
						action = (DevEventAction) SpringContextUtil.getBean(DevEventAction.class);
						break;
//                    case WARN_STATUS:
//                        action = (WarnStatusAction) SpringContextUtil.getBean(WarnStatusAction.class);
//                        break;
//                    case MP3_STATUS:
//                        action = (AlarmStatusAction) SpringContextUtil.getBean(AlarmStatusAction.class);
					case ALARM_STATUS:
						action = (AlarmStatusAction) SpringContextUtil.getBean(AlarmStatusAction.class);
						break;
				}
			}

			if (action != null) {
				action.excute(fep);
			}
		}
	}
}
