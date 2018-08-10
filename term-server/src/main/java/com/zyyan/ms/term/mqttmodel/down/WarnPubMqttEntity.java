/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zyyan.ms.term.mqttmodel.down;

import com.zyyan.ms.term.mqttmodel.base.FepDownMqttEntity;

/**   
 * @date 2018-05-30 00:00:00
 * @Description: 
 * @author zyyan
 * Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
public class WarnPubMqttEntity {
	
	private String sendType;//发送类型
	private String playRandom;//播放随机数
	private String playNum;//播放次数
	private String playInterval;//播放时间间隔
//	private String playIntervalUnit;//时间单位
	private String playContent;//播放内容

	public WarnPubMqttEntity() {
	}

	public WarnPubMqttEntity(String sendType, String playRandom, String playNum, String playInterval, String playContent) {
		this.sendType = sendType;
		this.playRandom = playRandom;
		this.playNum = playNum;
		this.playInterval = playInterval;
		this.playContent = playContent;
	}
	
	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getPlayRandom() {
		return playRandom;
	}

	public void setPlayRandom(String playRandom) {
		this.playRandom = playRandom;
	}

	public String getPlayNum() {
		return playNum;
	}

	public void setPlayNum(String playNum) {
		this.playNum = playNum;
	}

	public String getPlayInterval() {
		return playInterval;
	}

	public void setPlayInterval(String playInterval) {
		this.playInterval = playInterval;
	}

	public String getPlayContent() {
		return playContent;
	}

	public void setPlayContent(String playContent) {
		this.playContent = playContent;
	}
	
}
