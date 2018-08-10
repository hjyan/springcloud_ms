/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.exec.devenum;

/**
 * @Title: DevCtrlEnum.class
 * @author zyyan
 * @date 2018-5-30 10:30:59
 * @version V1.0
 * @Description:
 */
public enum DevCtrlEnum {
    //公共部分
    GET_CURRENT("get_current", "召测数据"),
    GET_DEV_STATUS("get_dev_status", "获取设备状态"),
    FORMAT("format", "格式化FLASH"),
    MAIN_UPGRADE("start_main_upgrade", "主设备升级"),
    RESET("reset", "远程重启"),
    GET_PARAM("get_param", "同步终端参数"),
    SET_PARAM("set_param", "设置终端参数"),
    SET_CLOCK("set_clock", "设置设备时钟"),
    GET_EVENT("get_event", "提取设备事件"),
    //业务协议
    PLAY_WARN("yjgb_play_warn", "发布洪水预警"),
    PLAY_MP3("yjgb_play_mp3", "点播mp3"),
    UPGRADE_MP3("yjgb_upgrade_mp3", "升级mp3"),
    CANCEL_UPGRADE_MP3("yjgb_upgrade_mp3_cancel", "取消升级mp3"),
    //之前的部分
    LIST_DEVICE("list_device", "同步终端状态"),
    CLEAN_LOCAL_UPGRADE("stop_main_upgrade", "取消主设备升级"),
    CLEAN_FTP_UPGRADE("stop_misc_upgrade", "取消配套设备升级");

    private String ctrlName;
    private String ctrlDescription;

    private DevCtrlEnum(String ctrlName, String ctrlDescription) {
        this.ctrlName = ctrlName;
        this.ctrlDescription = ctrlDescription;
    }

    public String getCtrlName() {
        return ctrlName;
    }

    public String getCtrlDescription() {
        return ctrlDescription;
    }

    public void setCtrlDescription(String ctrlDescription) {
        this.ctrlDescription = ctrlDescription;
    }

    /**
     * 根据控制名称查询枚举
     *
     * @param ctrlName
     * @return
     * @throws RuntimeException
     */
    public static DevCtrlEnum findByCtrlName(String ctrlName) throws RuntimeException {
        DevCtrlEnum result = null;
        DevCtrlEnum[] fcs = DevCtrlEnum.values();
        for (DevCtrlEnum fe : fcs) {
            if (ctrlName.equals(fe.getCtrlName())) {
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
    public static String findCtrlNameByDescription(String description) throws RuntimeException {
        String result = null;
        DevCtrlEnum[] fcs = DevCtrlEnum.values();
        for (DevCtrlEnum fe : fcs) {
            if (description.equals(fe.getCtrlDescription())) {
                result = fe.getCtrlName();
                break;
            }
        }
        return result;
    }

    /**
     * 根据描述查询控制名称
     *
     * @param ctrlName
     * @return
     * @throws RuntimeException
     */
    public static String findDescriptionByName(String ctrlName) throws RuntimeException {
        String result = null;
        DevCtrlEnum[] fcs = DevCtrlEnum.values();
        for (DevCtrlEnum fe : fcs) {
            if (ctrlName.equals(fe.getCtrlName())) {
                result = fe.getCtrlDescription();
                break;
            }
        }
        return result;
    }
}
