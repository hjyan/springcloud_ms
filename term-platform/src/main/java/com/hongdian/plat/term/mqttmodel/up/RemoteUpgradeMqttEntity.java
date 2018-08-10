package com.hongdian.plat.term.mqttmodel.up;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.hongdian.plat.term.mqttmodel.base.BaseMqttEntity;

@Component("start_main_upgrade")
public class RemoteUpgradeMqttEntity extends BaseMqttEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String fileName;
	private String version;

	public RemoteUpgradeMqttEntity() {
		super();
	}

	public RemoteUpgradeMqttEntity(String name, String fileName, String version) {
		super();
		this.name = name;
		this.fileName = fileName;
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
