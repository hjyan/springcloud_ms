/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.exec.devenum;

/**
 * @Title: ConnectEnum.class
 * @author：mlu
 * @date 2013-8-20 19:49:59
 * @version V1.0
 * @Description:在线模式枚举
 */
public enum ConnectEnum {
    
    SHORT_CONNECT(0, "短连接"),
    LONG_CONNECT(1, "长连接");
    
    private int status;
    private String name;

    private ConnectEnum(int status, String name) {
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
