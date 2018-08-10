/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.exec.devenum;

/**
 * @Title: DevTopicEnum.class
 * @author zyyan
 * @date 2018-5-20 10:30:59
 * @version V1.0
 * @Description:
 */
public enum DevTopicEnum {
	FEP_ACK("fep.msg.ack", "fep收到消息确认"),
    //登录登出
    FEP_LOGIN("fep.login", "fep登录"),
    FEP_LOGOUT("fep.logout", "fep登出"),
    DEV_LOGIN("dev.login", "设备登录"),
    DEV_LOGOUT("dev.logout", "设备登出"),
    //设备状态
    DEV_TEST("dev.test", "安装测试"),
    DEV_TIMER("dev.timer", "定时报"),
    DEV_ALARM("dev.alarm", "告警报"),
    DEV_STATUS("dev.status", "状态查询"),
    //设备参数
    DEV_PARAM("dev.param", "设备参数"),
    //设备事件
    DEV_EVENT("dev.event", "设备事件"),
    //设备注册
    DEV_REGISTER("dev.register", "设备注册"),
    //业务协议
    WARN_STATUS("yjgb.warn.status", "预警广播状态"),
    MP3_STATUS("yjgb.mp3.status", "mp3播放状态"),
    ALARM_STATUS("yjgb.play.status", "播放状态"),
    //预警广播业务部分协议
    YJGB_DEV_LOGIN("yjgb.dev.login", "预警广播设备登录"),
    YJGB_DEV_LOGOUT("yjgb.dev.logout", "预警广播设备登出"),
    YJGB_DEV_REGISTER("yjgb.dev.register", "预警广播设备注册"),
    YJGB_DEV_TIMER("yjgb.timer", "预警广播定时报");

    private String action;
    private String description;

    private DevTopicEnum(String action, String description) {
        this.action = action;
        this.description = description;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 根据控制名称查询枚举
     *
     * @param action
     * @return
     * @throws RuntimeException
     */
    public static DevTopicEnum findByAction(String action) throws RuntimeException {
        DevTopicEnum result = null;
        DevTopicEnum[] fcs = DevTopicEnum.values();
        for (DevTopicEnum fe : fcs) {
            if (action.equals(fe.getAction())) {
                result = fe;
                break;
            }
        }
        return result;
    }

    /**
     * 根据描述查询控制名称
     *
     * @param description
     * @return
     * @throws RuntimeException
     */
    public static String findActionByDescription(String description) throws RuntimeException {
        String result = null;
        DevTopicEnum[] fcs = DevTopicEnum.values();
        for (DevTopicEnum fe : fcs) {
            if (description.equals(fe.getDescription())) {
                result = fe.getAction();
                break;
            }
        }
        return result;
    }

    /**
     * 根据描述查询控制名称
     *
     * @param action
     * @return
     * @throws RuntimeException
     */
    public static String findDescriptionByAction(String action) throws RuntimeException {
        String result = null;
        DevTopicEnum[] fcs = DevTopicEnum.values();
        for (DevTopicEnum fe : fcs) {
            if (action.equals(fe.getAction())) {
                result = fe.getDescription();
                break;
            }
        }
        return result;
    }
}
