/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.exec.devenum;

/**
 * @Title: NewClass.class
 * @author 吴辉
 * @date 2013-4-2 9:45:01
 * @version V1.0
 * @Description:
 */
public enum DeviceLogEnum {

    ERC1("历史数据初始化记录"),
    ERC2("参数变更记录"),
    ERC3("状态量变位记录"),
    ERC4("传感器及仪表故障记录"),
    ERC5("密码修改记录"),
    ERC6("终端故障记录"),
    ERC7("交流失电记录"),
    ERC8("蓄电池电压低告警记录"),
    ERC9("终端箱非法打开记录"),
    ERC10("水泵故障记录"),
    ERC11("剩余水量超限告警记录"),
    ERC12("水位超限告警记录"),
    ERC13("水压超限告警记录"),
    ERC14("水质参数超限告警记录"),
    ERC15("数据出错记录"),
    ERC16("发报文记录"),
    ERC17("收报文记录");
    private String value;

    private DeviceLogEnum(String value) {
        this.value = value;
    }

    public static String getDeviceLogDescription(String name) {
        try {
            DeviceLogEnum de = DeviceLogEnum.valueOf(name);
            return de.value;
        } catch (Exception e) {
            return "";
        }
    }
}