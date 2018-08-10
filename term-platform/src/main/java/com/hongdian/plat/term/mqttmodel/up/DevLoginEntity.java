package com.hongdian.plat.term.mqttmodel.up;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.hongdian.plat.term.mqttmodel.base.BaseMqttEntity;

@Component(value = "dev.login")
public class DevLoginEntity extends BaseMqttEntity {
	private static final long serialVersionUID = 1L;
	private String xxx;
	private Date time;
	private String model;
	private String vendor;
	private String ip;
	private String port;
	private String paramVer;
	private String softVer;
	private String hardVer;
	private String sim;
	private String flag;
	private String termTypeId;
	private String termModelId;

	public DevLoginEntity() {
		super();
	}

	public DevLoginEntity(Date time, String model, String vendor, String ip, String port, String paramVer,
			String softVer, String hardVer, String sim, String flag, String termTypeId, String termModelId) {
		super();
		this.time = time;
		this.model = model;
		this.vendor = vendor;
		this.ip = ip;
		this.port = port;
		this.paramVer = paramVer;
		this.softVer = softVer;
		this.hardVer = hardVer;
		this.sim = sim;
		this.flag = flag;
		this.termTypeId = termTypeId;
		this.termModelId = termModelId;
	}

	public String getXxx() {
		return xxx;
	}

	public void setXxx(String xxx) {
		this.xxx = xxx;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getParamVer() {
		return paramVer;
	}

	public void setParamVer(String paramVer) {
		this.paramVer = paramVer;
	}

	public String getSoftVer() {
		return softVer;
	}

	public void setSoftVer(String softVer) {
		this.softVer = softVer;
	}

	public String getHardVer() {
		return hardVer;
	}

	public void setHardVer(String hardVer) {
		this.hardVer = hardVer;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getTermTypeId() {
		return termTypeId;
	}

	public void setTermTypeId(String termTypeId) {
		this.termTypeId = termTypeId;
	}

	public String getTermModelId() {
		return termModelId;
	}

	public void setTermModelId(String termModelId) {
		this.termModelId = termModelId;
	}

}
