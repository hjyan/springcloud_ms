package com.hongdian.plat.term.exec;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hongdian.plat.term.constant.SystemConstants;
import com.hongdian.plat.term.mqttmodel.base.BaseMqttEntity;
import com.hongdian.plat.term.service.ITermLogService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.TopicExchange;

@Deprecated
//@Component
public class MessageController {

	public static int count = 1;

	public final Logger log = LoggerFactory.getLogger(MessageExecutor.class);

	@Autowired
	private RabbitTemplate template;
	@Autowired
	private TopicExchange topicExchange;

	@Resource
	ITermLogService logService;

	@Autowired(required = true)
	Map<String, BaseMqttEntity> entityMap;

	@Value("${sys.platName}")
	String platName;

//	@RabbitListener(queues = "${sys.message.queue}")
	public void receive(Message msg, Channel channel) {
		log.info("平台接收新消息 ......" + new String(msg.getBody()));
//		System.out.println("handleCount: " + count);
		try {
			ExecMessage execMessage = new ExecMessage(msg.getBody());
			SystemConstants.TOPIC_PROCESS_QUEUE.offer(execMessage);//把消息丢到 queue 里面缓存处理
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//发布消息
	public void sendMsg(String topic, String body) {
		Message msg = new Message(body.getBytes(), new MessageProperties());
		template.send(topicExchange.getName(), topic, msg);
	}

}
