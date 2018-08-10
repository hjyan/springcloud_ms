/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.service;

/**
 * @date 2018-05-30 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
public interface IDevCtrlService {

	/**
	 * set_param 参数设置
	 *
	 * @param termSn 设备编码
	 * @param source fep 地址
	 * @param content 设置内容
	 */
	public void setParam(String termSn, String source, String content);

	//get_param 参数查询
	public void getParam(String termSn, String source);

	//reset 重启
	public void reset(String termSn, String source);

	//set_clock 设置时钟
	public void setClock(String termSn, String source, String content);

	//get_current 召测
	public void getCurrent(String termSn, String source);

	//format 格式化
	public void format(String termSn, String source);

	//start_main_upgrade远程升级 
	public void startMainUpgrade(String termSn, String source, String content);

	// get_event 事件提取
	public void getEvent(String termSn, String source, String beginTime, String endTime);

	/**
	 * ***********************************业务部分协议****************************************
	 */
	// "yjgb_play_warn 预警发布
	public void playWarn(String termSn, String source, String content,String gateNum,String title,String level,String playNum,String createPerson,String gates,String alarmType);

	// yjgb_play_mp3 播放mp3
	public void playMp3(String termSn, String source, String content,String gateNum,String title,String level,String playNum,String createPerson,String fileType,String gates,String alarmType);

	// yjgb_upgrade_mp3 更新mp3
	public void upgradeMp3(String termSn, String source, String content);
    
    public void deleteAlarm(String id,String gate);

}
