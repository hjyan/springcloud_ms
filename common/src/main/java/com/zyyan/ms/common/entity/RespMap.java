/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zyyan.ms.common.entity;

/**   
 * @date 2016-12-13
 * @Description: 
 * @author zyyan
 * Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
public class RespMap {
    
    private Boolean flag;
    private String msg;
    
    public RespMap(){
        
    }
    public RespMap(Boolean flag){
        this.flag = flag;
    }
    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
