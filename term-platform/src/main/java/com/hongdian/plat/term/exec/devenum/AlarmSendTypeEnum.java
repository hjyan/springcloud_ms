/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.exec.devenum;

/**
 * @Title: AlarmPubStatusEnum.class
 * @author：xlhua
 * @date 2018-7-19 14:49
 * @Description:预警发布 发送方式类型枚举
 */
public enum AlarmSendTypeEnum {
    
    alarmSendType_gprs(0,"gprs"),
    alarmSendType_message(1,"message");
    
    private int sendType;
    private String sendTypeName;

    private AlarmSendTypeEnum(int sendType, String sendTypeName) {
        this.sendType = sendType;
        this.sendTypeName = sendTypeName;
    }

    public int getSendType() {
        return sendType;
    }

    public String getSendTypeName() {
        return sendTypeName;
    }

    public void setSendType(int sendType) {
        this.sendType = sendType;
    }

    public void setSendTypeName(String sendTypeName) {
        this.sendTypeName = sendTypeName;
    }
    
}
