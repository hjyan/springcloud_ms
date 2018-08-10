/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.mqttmodel.down;

import com.zyyan.ms.term.mqttmodel.base.BaseMqttEntity;
import com.zyyan.ms.term.mqttmodel.base.FepDownMqttEntity;

/**
 * @date 2018-05-30 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
public class PlayMp3MqttEntity {

    private String sendType;
    private String random;
    private String fileType;

	public PlayMp3MqttEntity() {
	}

	public PlayMp3MqttEntity(String sendType, String random, String fileType) {
		this.sendType = sendType;
		this.random = random;
		this.fileType = fileType;
	}
	
    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

}
