/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term;

import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @date 2018-05-31 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
public class FepMqttClient {

	public static final ConcurrentLinkedQueue<String> FEP_TASK_RESP = new ConcurrentLinkedQueue<String>();
	MqttClient client;

	public static void main(String[] args) throws Exception {
//		new FepMqttClient().registerClient();
		int[] arr = new int[]{1, 52, 4, 89, 41, 22, 3, 78, 99, 31, 5};
		int n = arr.length;
		for (int j = 0; j < n; j++) {
			int temp = 0;
			for (int i = 0; i < n - j - 1; i++) {
				if (arr[i] > arr[i + 1]) {
					temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
				}
			}
		}
		System.out.print("result: " + Arrays.toString(arr));
		
		LinkedList<String> alist = new LinkedList<String>();
	}

	public void registerClient() throws Exception {
		try {
			client = new MqttClient("tcp://172.16.22.249:2883", "FepMqttReceiveClient");
			MqttConnectOptions options = new MqttConnectOptions();
			options.setUserName("admin");
			options.setPassword("123".toCharArray());
			client.connect(options);
			client.subscribe("plat/10003/#");
//			client.setCallback(new MqttCallback() {
//				@Override
//				public void connectionLost(Throwable thrwbl) {
//					System.out.println("fep mqtt 客户端连接中断。。。。。。");
//				}
//
//				@Override
//				public void messageArrived(String string, MqttMessage mm) throws Exception {
//					System.out.println("fep mqtt  客户端接收到平台消息。。。。。。" + new String(mm.getPayload()));
//					FEP_TASK_RESP.offer(new String(mm.getPayload()));
//				}
//
//				@Override
//				public void deliveryComplete(IMqttDeliveryToken imdt) {
////					System.out.println("fep mqtt deliveryComplete。。。。。。");
//				}
//			});
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("fep mqtt 模拟客户端启动完成 。。。。。。");

//		simulatePubMessage("fepLogout.json");
		System.out.println("fep mqtt 模拟客户端发布消息完成 。。。。。。");

//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (true) {
//					try {
//						while (!FEP_TASK_RESP.isEmpty()) {
//							String content = FEP_TASK_RESP.poll();
//							JSONObject json = JSONObject.parseObject(content);
//							String action = json.getString("action");
//							String cmdId = json.getString("cmdId");
//							try {
//								if ("plat.login".equals(action)) {
//									System.out.println("plat is login");
//								} else if ("plat.logout".equals(action)) {
//									System.out.println("plat is logout........., pause sending msg to plat");
//								} else if ("fep.login".equals(action)) {
//									System.out.println("receive plat acking to fep login");
//								} else if ("format".equals(action)) {
//									responseTask(client, cmdId);
//								} else if ("get_current".equals(action)) {
//									responseTask(client, cmdId);
//								} else if ("get_event".equals(action)) {
//									responseTask(client, cmdId);
//									pubMsg(client, "devEvent.json", "fep");
//								} else if ("get_param".equals(action)) {
//									responseTask(client, cmdId);
//									pubMsg(client, "devParam.json", "fep");
//								} else if ("yjgb_play_mp3".equals(action)) {
//									responseTask(client, cmdId);
//									pubMsg(client, "mp3Status.json", "fep");
//								} else if ("yjgb_play_warn".equals(action)) {
//									responseTask(client, cmdId);
//									pubMsg(client, "warnStatus.json", "fep");
//								} else if ("set_clock".equals(action)) {
//									responseTask(client, cmdId);
//								} else if ("reset".equals(action)) {
//									responseTask(client, cmdId);
//								} else if ("set_param".equals(action)) {
//									responseTask(client, cmdId);
//								} else if ("start_main_upgrade".equals(action)) {
//									responseTask(client, cmdId);
//								} else if ("yjgb_upgrade_mp3".equals(action)) {
//									responseTask(client, cmdId);
//								}
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//						}
//						Thread.sleep(2000);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}).start();
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
	}

}
