package com.hongdian.plat.term.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Deprecated
//@Configuration
public class AmqpConfig {

	@Value(value = "${sys.message.exchange}")
	private String exchange;
	@Value(value = "${sys.message.queue}")
	private String queueName;
	@Value(value = "${sys.message.topic}")
	private String topic;

	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(exchange);
	}

	@Bean(name = "queue")
	public Queue queue() {
		return new Queue(queueName);
	}

	@Bean
	public Binding binding(@Qualifier("queue") Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(topic);
	}
}
