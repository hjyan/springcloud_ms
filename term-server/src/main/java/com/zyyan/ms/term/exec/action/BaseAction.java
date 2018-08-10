/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zyyan.ms.term.exec.action;

import com.zyyan.ms.term.mqttmodel.base.FepUpMqttEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**   
 * @date 2018-05-17 00:00:00
 * @Description: 
 * @author zyyan
 * Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
public abstract class BaseAction {
	
	public final Logger log = LoggerFactory.getLogger(this.getClass());

	public abstract void excute(FepUpMqttEntity fep);
}
