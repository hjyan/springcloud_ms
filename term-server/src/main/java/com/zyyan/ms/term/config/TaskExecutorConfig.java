package com.zyyan.ms.term.config;

import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @date 2018-05-17 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
@Configuration
public class TaskExecutorConfig {

	public class BeanConfig {

		@Bean
		public TaskExecutor taskExecutor() {
			ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
			// 设置核心线程数
			executor.setCorePoolSize(5);
			// 设置最大线程数
			executor.setMaxPoolSize(10);
			// 设置队列容量
			executor.setQueueCapacity(20);
			// 设置线程活跃时间（秒）
			executor.setKeepAliveSeconds(60);
			// 设置拒绝策略
			executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
			// 等待所有任务结束后再关闭线程池
			executor.setWaitForTasksToCompleteOnShutdown(true);
			return executor;
		}
	}
}
