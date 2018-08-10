/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.plat.term;

import static com.zyyan.plat.term.FepMqttClient.FEP_TASK_RESP;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author 15927
 */
public class fepClient2 {
    public static void main(String[] args) throws MqttException {
                MqttClient client2 = new MqttClient("tcp://172.16.22.47:1883", "FepMqttReceiveClient");
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("admin");
        options.setPassword("123456".toCharArray());
        client2.connect(options);
        client2.subscribe("plat/10001/#");
        MqttMessage msg = new MqttMessage("nihao yaoo jlskdjfljs ".getBytes());
        
        client2.setCallback(new MqttCallback() {
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
//					System.out.println("fep mqtt deliveryComplete。。。。。。");
				}
			});
        
        
        client2.publish("plat/10001/abc", msg);
        
    }
}
