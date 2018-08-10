/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.constant;

import com.hongdian.plat.term.mqttmodel.base.FepUpMqttEntity;
import com.hongdian.plat.term.entity.ScheduleTask;
import com.hongdian.plat.term.entity.TermHeader;
import com.hongdian.plat.term.entity.TermStatusDesc;
import com.hongdian.plat.term.exec.ExecMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

/**
 * @date 2018-05-17 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
public class SystemConstants {

//	task constants
	public static final ConcurrentMap<Integer, ScheduleTask> SCHEDULE_TASK_CACHE = new ConcurrentHashMap<Integer, ScheduleTask>();
	public static final ConcurrentMap<String, TermHeader> TERM_HEADER_CACHE = new ConcurrentHashMap<String, TermHeader>();
	public static boolean SCHEDULE_IS_RUN = true;
	public static long TASK_EXECUTE_TIMEOUT = 30 * 60 * 1000L;
	public static long OUTPUT_COUNT = 0;

	//设备状态
	public static final List<TermStatusDesc> TERM_STATUS_DESC_LIST = new ArrayList<TermStatusDesc>();

	//处理主题消息 缓存队列
	public static final ConcurrentLinkedQueue<ExecMessage> TOPIC_PROCESS_QUEUE = new ConcurrentLinkedQueue<ExecMessage>();

	//设备状态
	public final static Integer STATUS_ONLINE = 0;
	public final static Integer STATUS_OFFLINE = 1;

}
