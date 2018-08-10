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
 * @Description:预警发布 MP3文件类型枚举
 */
public enum AlarmFileTypeEnum {
    
    alarmFileType_ATTENXX(0,"提示音文件"),
    alarmFileType_ALARMXX(1,"普通文件"),
    alarmFileType_NORMALX(2,"紧急预警文件"),
    alarmFileType_ADVERTX(3,"广告文件");
    
    private int fileType;
    private String fileTypeName;

    private AlarmFileTypeEnum(int fileType, String fileTypeName) {
        this.fileType = fileType;
        this.fileTypeName = fileTypeName;
    }

    public int getFileType() {
        return fileType;
    }

    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }
    
}
