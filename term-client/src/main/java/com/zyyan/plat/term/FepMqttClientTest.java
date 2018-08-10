/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.plat.term;

import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

/**
 * @date 2018-05-31 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
public class FepMqttClientTest {

	public static final ConcurrentLinkedQueue<String> FEP_TASK_RESP = new ConcurrentLinkedQueue<String>();
	MqttClient client;

	public static void main(String[] args) throws Exception {
		new FepMqttClientTest().registerClient();
	}

	public void registerClient() throws Exception {
		try {
			client = new MqttClient("tcp://172.16.8.60:30600", "FepMqttClientTest");
//			client = new MqttClient("tcp://localhost:1883", "FepMqttClientTest");
			MqttConnectOptions options = new MqttConnectOptions();
			options.setKeepAliveInterval(60);
			options.setUserName("admin");
			options.setPassword("123".toCharArray());
			options.setCleanSession(true);
			client.connect(options);
			

			client.subscribe("plat/10004/#");
			client.setCallback(new MqttCallback() {
				@Override
				public void connectionLost(Throwable thrwbl) {
					System.out.println("fep mqtt 客户端连接中断。。。。。。");
				}

				@Override
				public void messageArrived(String string, MqttMessage mm) throws Exception {
					System.out.println("fep mqtt  客户端接收到平台消息。。。。。。" + new String(mm.getPayload()));
					FEP_TASK_RESP.offer(new String(mm.getPayload()));
				}

				@Override
				public void deliveryComplete(IMqttDeliveryToken imdt) {
					System.out.println("fep mqtt deliveryComplete。。。。。。");
				}
			});
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("fep mqtt 模拟客户端启动完成 。。。。。。");

		simulatePubMessage("devLogout.json");
		System.out.println("fep mqtt 模拟客户端发布消息完成 。。。。。。");
	}

	//预置任务fep回应
	public static void responseTask(MqttClient client, String cmdId) throws Exception {
		String fepPpath = "F:\\swic-svn\\H7760\\src\\back-end\\term-client\\src\\test\\java\\com\\hongdian\\plat\\term\\fep\\";
		String filename = fepPpath + "cmdResp.json";
		File file = new File(filename);
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		InputStream is = new FileInputStream(filename);
		is.read(filecontent);
		is.close();

		String content = new String(filecontent);
		JSONObject json = JSONObject.parseObject(content);
		json.put("cmdId", cmdId);
		MqttMessage msg = new MqttMessage(json.toString().getBytes());
		String topic = "fep/10001/fep_itss_comm";
		client.publish(topic, msg);
		System.out.println("预置任务响应。。。cmdId： " + cmdId);
	}

	public static void pubMsg(MqttClient client, String jsonFileName, String cmdId) throws Exception {
		String topic = "fep/10003/fep_itss_comm";
		String fepPpath = "D:\\swic-svn\\H7760\\src\\back-end\\term-client\\src\\test\\java\\com\\hongdian\\plat\\term\\fep\\";
		String filename = fepPpath + jsonFileName;
		File file = new File(filename);
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		InputStream is = new FileInputStream(filename);
		is.read(filecontent);
		is.close();
		MqttMessage msg = new MqttMessage(filecontent);
		client.publish(topic, msg);

		JSONObject json = JSONObject.parseObject(new String(filecontent));
		String action = json.getString("action");
		System.out.println("fep/10001/fep_itss_comm 主题*  " + action + "  *消息发布完成。。。。。。");
	}

	public void simulatePubMessage(String jsonFileName) throws Exception {
		String topic = "fep/10004/fep_itss_comm";
		String fepPpath = "D:\\swic-svn\\H7760\\src\\back-end\\term-client\\src\\test\\java\\com\\hongdian\\plat\\term\\fep\\";
		String filename = fepPpath + jsonFileName;
		File file = new File(filename);
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		InputStream is = new FileInputStream(filename);
		is.read(filecontent);
		is.close();
		MqttMessage msg = new MqttMessage(filecontent);
		client.publish(topic, msg);
	}

}
