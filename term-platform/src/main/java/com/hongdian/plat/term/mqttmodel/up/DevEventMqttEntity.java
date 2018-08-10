/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.mqttmodel.up;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @date 2018-05-30 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
public class DevEventMqttEntity implements Serializable {

	private String style;
	private String report;
	private String send;
	private List<EventItem> data;

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getSend() {
		return send;
	}

	public void setSend(String send) {
		this.send = send;
	}

	public List<EventItem> getData() {
		return data;
	}

	public void setData(List<EventItem> data) {
		this.data = data;
	}

}
