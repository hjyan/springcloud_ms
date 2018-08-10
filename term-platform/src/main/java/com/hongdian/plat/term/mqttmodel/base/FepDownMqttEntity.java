package com.hongdian.plat.term.mqttmodel.base;

import java.util.Date;

public class FepDownMqttEntity extends BaseMqttEntity {

	private static final long serialVersionUID = 1L;

	// 1运维类：fep.itss 登录包(可能有回复，fep没启动，不是一直发)，状态定时包。
	// 2业务类：fep.xhyj.biz,fep.biz 预警定时包，雨量信息，告警信息，水位信息。
	// 3令交互重启机回复，设置参数及回复。
	// 4前置机订阅的都是自己名称的主题：fep.rtu.hcs.std,fep.yjgb.std
	private String topic;
	
	private String action; // login/logout

	private String source; // 仅登录包有，其他为空，数据发送方 fep.rtu.hcs.std，多个前置机名称相同，需要登录校验控制

	private String sn; // 备标识 如0000012345

	private Date deliveryTime; // 2018-04-24 14:30:05

	private String version; // 1.0

	private String cmd; // 操作指令

	private String cmdId; // 命令序列号

	private String contentType; // 内容类型xml or json

	private Object content; // 登录包的实体json 如登录信息，定时信息，交互内容
	
	public FepDownMqttEntity() {
		super();
	}

	public FepDownMqttEntity(String topic, String action, String source, String sn,
			Date deliveryTime, String version, String cmd, String cmdId, String contentType, Object content) {
		super();
		this.topic = topic;
		this.action = action;
		this.source = source;
		this.sn = sn;
		this.deliveryTime = deliveryTime;
		this.version = version;
		this.cmd = cmd;
		this.cmdId = cmdId;
		this.contentType = contentType;
		this.content = content;
	}
	
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getCmdId() {
		return cmdId;
	}

	public void setCmdId(String cmdId) {
		this.cmdId = cmdId;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

}