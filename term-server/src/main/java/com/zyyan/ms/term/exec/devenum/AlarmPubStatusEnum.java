/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.exec.devenum;

/**
 * @Title: AlarmPubStatusEnum.class
 * @author：xlhua
 * @date 2018-7-19 14:49
 * @Description:预警发布状态枚举类型
 */
public enum AlarmPubStatusEnum {
    alarm_new(0,"新预警"),
    alarm_send(1,"已发送"),
    alarm_playing(2,"正在执行"),
    alarm_success(3,"播放成功"),
    alarm_clean(4,"预警清除"),
    alarm_delete(5,"已删除");

    private int pubStatus;
    private String msg;
    
    private AlarmPubStatusEnum(int pubStatus, String msg) {
        this.pubStatus = pubStatus;
        this.msg = msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setPubStatus(int pubStatus) {
        this.pubStatus = pubStatus;
    }

    public int getPubStatus() {
        return pubStatus;
    }
}
