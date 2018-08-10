/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.exec.action;

import com.hongdian.plat.term.mqttmodel.up.DevEventMqttEntity;
import com.hongdian.plat.term.mqttmodel.up.WarnStatusMqttEntity;
import com.hongdian.plat.term.mqttmodel.base.FepUpMqttEntity;
import com.hongdian.plat.term.util.JSONUtils;
import org.springframework.stereotype.Component;

/**
 * @date 2018-05-30 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
@Component
public class DevEventAction extends BaseAction {

	@Override
	public void excute(FepUpMqttEntity fep) {
		String content = fep.getContent();
		DevEventMqttEntity entity = (DevEventMqttEntity) JSONUtils.readValue(content, DevEventMqttEntity.class);
		System.out.println(entity.toString());
		//入库
	}

}
