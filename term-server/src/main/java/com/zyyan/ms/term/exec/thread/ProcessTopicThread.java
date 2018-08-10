/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.exec.thread;

import com.zyyan.ms.term.config.SpringContextUtil;
import com.zyyan.ms.term.constant.SystemConstants;
import com.zyyan.ms.term.mqttmodel.base.FepUpMqttEntity;
import com.zyyan.ms.term.entity.TermLog;
import com.zyyan.ms.term.exception.LogFepMessageFailedException;
import com.zyyan.ms.term.exec.ExecMessage;
import com.zyyan.ms.term.exec.MessageExecutor;
import com.zyyan.ms.term.service.ITermLogService;
import com.zyyan.ms.term.util.JSONUtils;
import java.util.Date;
import org.apache.commons.codec.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 接收消息，解析
 * @date 2018-05-21 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
public class ProcessTopicThread implements Runnable {

	public final Logger log = LoggerFactory.getLogger(this.getClass());

	public static final Boolean TOPIC_PROCESS_QUEUE_FLAG = true;

	private ITermLogService logService;
	private MessageExecutor executor;

	public ProcessTopicThread() {
		logService = (ITermLogService) SpringContextUtil.getBean(ITermLogService.class);
		executor = (MessageExecutor) SpringContextUtil.getBean(MessageExecutor.class);
	}

	@Override
	public void run() {
		while (TOPIC_PROCESS_QUEUE_FLAG) {
			try {
				if (SystemConstants.TOPIC_PROCESS_QUEUE.isEmpty()) {//队列是否有数据
					Thread.sleep(5000);
				}

				while (!SystemConstants.TOPIC_PROCESS_QUEUE.isEmpty()) {
					ExecMessage msg = SystemConstants.TOPIC_PROCESS_QUEUE.poll();
					FepUpMqttEntity fep = getFepEntityFromMsg(msg);//解析消息
					//日志入库
					auditFepEntity(fep);
					//处理信息
					executor.handleMsg(fep);
				}
			} catch (Exception ex) {
				log.error(null, ex);
			}
		}
	}

	private FepUpMqttEntity getFepEntityFromMsg(ExecMessage msg) {
		String payload = new String(msg.getBody(), Charsets.UTF_8);
		FepUpMqttEntity fep = JSONUtils.readValue(payload, FepUpMqttEntity.class);
		return fep;
	}

	private void auditFepEntity(FepUpMqttEntity fep) throws LogFepMessageFailedException {
		TermLog termLog = new TermLog();
		termLog.setTermSn(fep.getSn());
		termLog.setPayload(JSONUtils.writeValueAsString(fep));
		termLog.setReciveTime(new Date());
		termLog.setTopic(fep.getTopic());
		termLog.setAction(fep.getAction());
		try {
			logService.insert(termLog);
		} catch (Exception e) {
			throw new LogFepMessageFailedException("persistent FEP message into db failed !", e);
		}
	}
}
