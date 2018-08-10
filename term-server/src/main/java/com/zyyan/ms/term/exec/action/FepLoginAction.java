/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.exec.action;

import com.zyyan.ms.term.config.SpringContextUtil;
import com.zyyan.ms.term.mqttmodel.base.FepDownMqttEntity;
import com.zyyan.ms.term.mqttmodel.base.FepUpMqttEntity;
import com.zyyan.ms.term.exec.MessageExecutor;
import com.zyyan.ms.term.exec.PlatMqttClient;
import com.zyyan.ms.term.util.JSONUtils;
import static java.lang.StrictMath.log;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @date 2018-05-21 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
@Component
public class FepLoginAction extends BaseAction {

    @Resource
    PlatMqttClient platMqttClient;

    @Override
    public void excute(FepUpMqttEntity fep) {
        //响应消息，平台收到fep 登录消息后回复fep 
        String responseTopic = fep.getTopic().replace("fep", "plat");
        int pos = fep.getTopic().lastIndexOf("/");
        FepDownMqttEntity down = new FepDownMqttEntity(responseTopic, fep.getAction(), "", "", new Date(), fep.getVersion(), "", "", fep.getContentType(), "");
        try {
//            msgController.sendMsg(responseTopic, JSONUtils.writeValueAsString(down));
            platMqttClient.sendMessage(JSONUtils.writeValueAsString(down), responseTopic.substring(pos + 1));
            log.info("fep login, send feedback message.");
        } catch (Exception e) {
            throw new RuntimeException("fail to send callback login msg to term ......", e);
        }
    }
}
