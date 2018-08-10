package com.zyyan.ms.term.mqttmodel.up;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.zyyan.ms.term.mqttmodel.base.BaseMqttEntity;

@Component("get_param")
public class QueryParamMqttEntity extends BaseMqttEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String handler;

	public QueryParamMqttEntity() {
		super();
	}

	public QueryParamMqttEntity(String name, String handler) {
		super();
		this.name = name;
		this.handler = handler;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

}
