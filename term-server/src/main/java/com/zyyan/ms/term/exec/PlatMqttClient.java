/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.exec;

import com.zyyan.ms.term.constant.SystemConstants;
import com.zyyan.ms.term.mqttmodel.base.FepDownMqttEntity;
import com.zyyan.ms.term.util.JSONUtils;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author 15927
 */
@Component
public class PlatMqttClient {

	public final Logger logger = LoggerFactory.getLogger(MessageExecutor.class);
	private MqttClient mqttClient;

	@Value(value = "${sys.mqtt.clientId}")
	private String clientId;
	@Value(value = "${sys.platName}")
	private String platName;
	@Value(value = "${sys.mqtt.url}")
	private String url;
	@Value(value = "${sys.mqtt.port}")
	private String port;
	@Value(value = "${sys.mqtt.username}")
	private String user;
	@Value(value = "${sys.mqtt.password}")
	private String pwd;
	@Value(value = "${sys.mqtt.subTopic}")
	private String topic;
	@Value(value = "${sys.mqtt.qos}")
	private int qos;
	@Value(value = "${sys.mqtt.pubTopicPrefix}")
	private String pubTopicPrefix;

	@PostConstruct
	public void initPlatMqttClient() {
		String platLoginOutTopic = String.format("plat/%s/status", platName);
		try {
			mqttClient = new MqttClient(String.format("%s:%s", url, port), clientId);
			MqttConnectOptions conOptions = new MqttConnectOptions();
			conOptions.setCleanSession(false);
			//设置用户名密码
			conOptions.setUserName(user);
			conOptions.setPassword(pwd.toCharArray());
			conOptions.setAutomaticReconnect(true);
			conOptions.setConnectionTimeout(60000);
			conOptions.setWill(platLoginOutTopic, lastWillMsg(platLoginOutTopic), 1, false);//设置遗嘱
			mqttClient.connect(conOptions);
			//全局回调，不区分主题
			mqttClient.setCallback(new MqttCallbackExtended() {
				//链接断开回调
				@Override
				public void connectionLost(Throwable thrwbl) {
					logger.info("链接断开！！！！！");
				}

				//消息到达回调
				@Override
				public void messageArrived(String topic, MqttMessage msg) throws Exception {
					logger.info("平台接收新消息 ......" + msg.toString());
					ExecMessage execMessage = new ExecMessage(msg.toString().getBytes());
					SystemConstants.TOPIC_PROCESS_QUEUE.offer(execMessage);//把消息丢到 queue 里面缓存处理
				}

				//传输完成回调
				@Override
				public void deliveryComplete(IMqttDeliveryToken imdt) {
					logger.info("数据传输完成！！！！！");
				}

				@Override
				public void connectComplete(boolean reconnect, String serverURI) {
					if (reconnect) {
						logger.info("断线重连，重新订阅主题！！！！！");
						try {
							mqttClient.subscribe(topic, qos);
						} catch (MqttException ex) {
							logger.error(null, ex);
						}
					}
				}
			});
			//订阅(主题/质量)
			mqttClient.subscribe(topic, qos);
			login(platLoginOutTopic);//平台启动发登录包
		} catch (MqttException ex) {
			logger.error(null, ex);
		}
	}

	private void login(String topic) throws MqttException {
		FepDownMqttEntity fep = new FepDownMqttEntity(topic, "plat.login", "", "", new Date(), "1.0", "", "1", "json", "");
		MqttMessage msg = new MqttMessage(JSONUtils.writeValueAsString(fep).getBytes());
		mqttClient.publish(fep.getTopic(), msg);
	}

	public byte[] lastWillMsg(String topic) {
		FepDownMqttEntity fep = new FepDownMqttEntity(topic, "plat.logout", "", "", new Date(), "1.0", "", "1", "json", "");
		return JSONUtils.writeValueAsString(fep).getBytes();
	}

	public void sendMessage(String messageContent, String fepSource) {
		MqttMessage message = new MqttMessage(messageContent.getBytes());
		try {
			mqttClient.publish(pubTopicPrefix + fepSource, message);
            logger.info("预置任务下发，主题："+pubTopicPrefix + fepSource+"，消息：" + messageContent);
		} catch (MqttException ex) {
			logger.error(null, ex);
		}
	}
}
