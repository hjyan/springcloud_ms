/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.mqttmodel.up;

import com.zyyan.ms.term.mqttmodel.base.FepUpMqttEntity;
import java.io.Serializable;
import java.util.Date;

/**
 * @date 2018-05-30 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
public class Mp3StatusMqttEntity extends FepUpMqttEntity {

	private Date time;
	private Integer playRandom;
	private String fileType;
	private String sendType;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getPlayRandom() {
		return playRandom;
	}

	public void setPlayRandom(Integer playRandom) {
		this.playRandom = playRandom;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

}
