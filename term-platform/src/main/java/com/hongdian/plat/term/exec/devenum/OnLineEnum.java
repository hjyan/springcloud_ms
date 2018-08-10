/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.exec.devenum;

/**
 * @Title: OnLineEnum.class
 * @author zyyan	
 * @date 2018-7-3 19:49:59
 * @version V1.0
 * @Description:在线状态枚举
 */
public enum OnLineEnum {

    OFF_LINE(0, "离线"),
    ON_LINE(1, "在线");
    private int status;
    private String name;

    private OnLineEnum(int status, String name) {
        this.status = status;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
