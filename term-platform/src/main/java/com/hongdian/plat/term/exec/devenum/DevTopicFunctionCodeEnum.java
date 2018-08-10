/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.exec.devenum;

/**
 * @Title: DevTopicFunctionCodeEnum.class
 * @author 吴辉
 * @date 2013-6-9 17:45:33
 * @version V1.0
 * @Description:
 */
public enum DevTopicFunctionCodeEnum {

    //设备删除（广播）
    DEVICE_MODIFY("app.update.dev"),
    //设备更新（广播）
    DEVICE_DELETE("app.delete.dev"),
    //上报升级进度（广播）
    UPGRADE_PROGRESS("fep.upgrade"),
    //终端登录 gps（广播）
    GPS_LOGIN("fep.gpsdev.login"),
    //终端登出 gps（广播）
    GPS_LOGOUT("fep.gpsdev.logout"),
    //上报终端实时数据
    REPORT_DATA("fep.trap"),
    //发布路由器信息主题名称
    ROUTER_INFO("topic.router.data"),
    //终端参数上报
    TERM_PARAM("fep.param"),
    //终端软件版本上报
    SYNC_SOFTWARE_VER("fep.software.ver");
    private String functionName;

    private DevTopicFunctionCodeEnum(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public static DevTopicFunctionCodeEnum findByFunctionName(String functionName) throws RuntimeException {
        DevTopicFunctionCodeEnum result = null;
        DevTopicFunctionCodeEnum[] fcs = DevTopicFunctionCodeEnum.values();
        for (DevTopicFunctionCodeEnum fe : fcs) {
            if (functionName.equals(fe.getFunctionName())) {
                result = fe;
                break;
            }
        }
        return result;
    }
}
