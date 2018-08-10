/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.config;

import com.zyyan.ms.term.exec.ExecMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 * @date 2018-05-31 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
@Deprecated
//@Component
public class FepMqttClient {
    
//    public static void main(String[] args) throws Exception{
//        MqttClient client1 = new MqttClient("tcp://172.16.8.60:30600", "FepMqttReceiveClient");
//        MqttConnectOptions options = new MqttConnectOptions();
//        options.setUserName("admin");
//        options.setPassword("123".toCharArray());
//        client1.connect(options);
//        client1.subscribe("plat/10001/#");
//        System.out.println("mqtt connect success");
//    }

	public static final ConcurrentLinkedQueue<String> FEP_TASK_RESP = new ConcurrentLinkedQueue<String>();
	MqttClient client;

//	@PostConstruct
	public void registerClient() {
		try {
			client = new MqttClient("tcp://121.37.24.178:30600", "FepMqttReceiveClient");
			MqttConnectOptions options = new MqttConnectOptions();
			options.setUserName("admin");
			options.setPassword("123".toCharArray());
			client.connect(options);
			client.subscribe("plat/10001/#");
			client.setCallback(new MqttCallback() {
				@Override
				public void connectionLost(Throwable thrwbl) {
					System.out.println("fep mqtt 客户端连接中断。。。。。。");
				}

				@Override
				public void messageArrived(String string, MqttMessage mm) throws Exception {
					System.out.println("fep mqtt  客户端接收到平台消息。。。。。。");
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

		//启动线程用于发布消息
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						while (!FEP_TASK_RESP.isEmpty()) {
							String content = FEP_TASK_RESP.poll();
							JSONObject json = new JSONObject(content);
							String action = json.getString("action");
							String cmdId = json.getString("cmdId");
							System.out.println("action: " + action + "  ,cmdId: " + cmdId);
							try {
								if ("plat.login".equals(action)) {
									System.out.println("plat is login");
								} else if ("plat.logout".equals(action)) {
									System.out.println("plat is logout........., pause sending msg to plat");
								} else if ("fep.login".equals(action)) {
									System.out.println("receive plat acking to fep login");
								} else if ("format".equals(action)) {
									responseTask(client, cmdId);
								} else if ("get_current".equals(action)) {
									responseTask(client, cmdId);
								} else if ("get_event".equals(action)) {
									responseTask(client, cmdId);
									pubMsg(client, "devEvent.json", "fep");
								} else if ("get_param".equals(action)) {
									responseTask(client, cmdId);
									pubMsg(client, "getParam.json", "fep");
								} else if ("yjgb_play_mp3".equals(action)) {
									responseTask(client, cmdId);
									pubMsg(client, "mp3Status.json", "fep");
								} else if ("yjgb_play_warn".equals(action)) {
									responseTask(client, cmdId);
									pubMsg(client, "warnStatus.json", "fep");
								} else if ("set_clock".equals(action)) {
									responseTask(client, cmdId);
								} else if ("reset".equals(action)) {
									responseTask(client, cmdId);
								} else if ("set_param".equals(action)) {
									responseTask(client, cmdId);
								} else if ("start_main_upgrade".equals(action)) {
									responseTask(client, cmdId);
								} else if ("yjgb_upgrade_mp3".equals(action)) {
									responseTask(client, cmdId);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						Thread.sleep(2000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
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
		JSONObject json = new JSONObject(content);
		json.put("cmdId", cmdId);
		MqttMessage msg = new MqttMessage(json.toString().getBytes());
		String topic = "fep/10001/fep_itss_comm";
		client.publish(topic, msg);
		System.out.println("预置任务响应。。。cmdId： " + cmdId + ",  action: " + json.getString("action"));
	}

	public static void pubMsg(MqttClient client, String jsonFileName, String cmdId) throws Exception {
		String topic = "plat/10001/fep_itss_comm";
		String fepPpath = "F:\\swic-svn\\H7760\\src\\back-end\\term-client\\src\\test\\java\\com\\hongdian\\plat\\term\\fep\\";
		String filename = fepPpath + jsonFileName;
		File file = new File(filename);
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		InputStream is = new FileInputStream(filename);
		is.read(filecontent);
		is.close();
		MqttMessage msg = new MqttMessage(filecontent);
		client.publish(topic, msg);

		JSONObject json = new JSONObject(new String(filecontent));
		String action = json.getString("action");
		System.out.println("fep/10001/fep_itss_comm 主题*  " + action + "  *消息发布完成。。。。。。");
	}

}
