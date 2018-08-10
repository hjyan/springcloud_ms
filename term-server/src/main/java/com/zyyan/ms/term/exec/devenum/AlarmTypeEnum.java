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
 * @Description:预警发布 预警类型枚举
 */
public enum AlarmTypeEnum {
    alarmType_pub(0,"预警发布"),
    alarmType_mp3(1,"MP3点播");
    
    private int type;
    private String typeName;

    public int getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    private AlarmTypeEnum(int type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

}
