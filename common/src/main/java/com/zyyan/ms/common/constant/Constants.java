/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zyyan.ms.common.constant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.zyyan.ms.common.util.CommUtils;

/**
* @Description: SystemConstant.java 
* @author liuchong
* @date 2016-12-30 
* tags
* Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
*/
public class Constants {
           
    /**
     * 缓存信息
     */
    public static final String SESSION_USER = "user";
    
    public static final String SESSION_MENU_LIST = "menuList";
    
    public static final Map<String, String> LogPara_CACHE = new ConcurrentHashMap<String, String>();
    
    public static final String SUPER_USER_ID = "super";
    public static final String SUPER_USER_PWD = CommUtils.getMD5("nb945".getBytes());
    
    public static final int MSG_TYPE_SMS = 0;
    public static final int MSG_TYPE_EMAIL = 1;
    
    public static final int MSG_STATUS_WAITTING = 0;//等待处理
    public static final int MSG_STATUS_SENDING = 1;//发送中
    public static final int MSG_STATUS_SEND_FAIL = 2;//发送失败
    public static final int MSG_STATUS_SEND_SUCCESS = 3;//发送成功
    public static final int MSG_STATUS_SEND_CANCEL = 4;//已取消
    
}
