package com.hongdian.plat.term.mqttmodel.base;

import java.io.Serializable;
import java.util.Date;

public class FepReturnMessage extends BaseMqttEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;

	private String message;

	private Date beginTime;

	private Date endTime;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
