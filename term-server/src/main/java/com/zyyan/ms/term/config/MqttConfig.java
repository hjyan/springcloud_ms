package com.zyyan.ms.term.config;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.springframework.stereotype.Component;

import com.zyyan.ms.term.mqttmodel.base.FepDownMqttEntity;
import com.zyyan.ms.term.util.JSONUtils;
import org.springframework.beans.factory.annotation.Value;

@Deprecated
//@Component
public class MqttConfig {
	
	@Value("${sys.mqtt.url}")
	private String url;
	@Value("${sys.mqtt.port}")
	private String port;
	@Value("${sys.mqtt.clientId}")
	private String clientId;
	@Value("${sys.mqtt.willClientId}")
	private String willClientId;
	@Value("${sys.platName}")
	private String platName;

//	@PostConstruct
	public void login() throws MqttException {
		try {
			MqttClient client = new MqttClient(String.format("%s:%s", url, port), clientId);
			MqttConnectOptions options = new MqttConnectOptions();
			options.setUserName("admin");
			options.setPassword("123".toCharArray());
			FepDownMqttEntity fep = new FepDownMqttEntity(String.format("plat/%s/status", platName), "plat.login", "", "", new Date(), "1.0", "", "1", "json", "");
			client.connect(options);

			MqttMessage msg = new MqttMessage(JSONUtils.writeValueAsString(fep).getBytes());
			client.publish(fep.getTopic(), msg);
			logout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logout() throws MqttSecurityException, MqttException {
		try {
			MqttClient client = new MqttClient(String.format("%s:%s", url, port), willClientId);
			MqttConnectOptions options = new MqttConnectOptions();
			options.setUserName("admin");
			options.setPassword("123".toCharArray());
			FepDownMqttEntity fep = new FepDownMqttEntity(String.format("plat/%s/status", platName), "plat.logout", "", "", new Date(), "1.0", "", "1", "json", "");
			options.setWill(fep.getTopic(), JSONUtils.writeValueAsString(fep).getBytes(), 1, false);
			client.connect(options);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
