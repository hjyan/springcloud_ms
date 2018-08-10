package com.hongdian.plat.term.mqttmodel.up;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.hongdian.plat.term.mqttmodel.base.BaseMqttEntity;

@Component(value = "dev.logout")
public class LogoutMqttEntity extends BaseMqttEntity {
	private static final long serialVersionUID = 1L;
	private Date time;
	private String total_flow;
	private String flag;
	
	public LogoutMqttEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LogoutMqttEntity(Date time, String total_flow, String flag) {
		super();
		this.time = time;
		this.total_flow = total_flow;
		this.flag = flag;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getTotal_flow() {
		return total_flow;
	}

	public void setTotal_flow(String total_flow) {
		this.total_flow = total_flow;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
