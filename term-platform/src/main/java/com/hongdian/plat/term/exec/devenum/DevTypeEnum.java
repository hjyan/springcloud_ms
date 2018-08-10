/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.exec.devenum;

/**
 * @Title: DevTypeEnum.class
 * @author 吴辉
 * @date 2013-6-15 11:28:51
 * @version V1.0
 * @Description:
 */
public enum DevTypeEnum {

    TERM_TYPE_RTU("rtu"),
    /**
     * dtu设备
     */
    TERM_TYPE_DTU("dtu"),
    /**
     * dvs设备
     */
    TERM_TYPE_DVS("dvs"),
    /**
     * router设备
     */
    TERM_TYPE_ROUTER("router");
    private String typeName;

    private DevTypeEnum(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public static DevTypeEnum findByTypeName(String typeName) throws RuntimeException {
        DevTypeEnum result = null;
        DevTypeEnum[] fcs = DevTypeEnum.values();
        for (DevTypeEnum fe : fcs) {
            if (typeName.equals(fe.getTypeName())) {
                result = fe;
                break;
            }
        }
        return result;
    }
}
