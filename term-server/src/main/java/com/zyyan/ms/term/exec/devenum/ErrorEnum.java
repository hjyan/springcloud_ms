/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.exec.devenum;

/**
 * @Title: ErrorEnum.class
 * @author zyyan
 * @date 2018-6-2 9:21:40
 * @version V1.0
 * @Description:
 */
public enum ErrorEnum {

    SUCCEESS("0", "任务下发成功"),
    TASK_TIMEOUT("-1", "预置任务执行超时"),
    OFF_LINE("-2", "设备不在线"),
    PARA_ERROR("-3", "升级参数错误"),
    UPGRADEDING("-4", "设备正在升级"),
    PKG_NOT_EXIST("-5", "升级包不存在"),
    READE_PKG_ERROR("-6", "读取升级包错误"),
    TIMEOUT("-7", "设备数据超时"),
    IN_PROC("-8", "设备正在处理事务中"),
    UPGRADE_FAIL("-9", "取消设备升级失败"),
    RESUFE_OPERATE("-10", "设备拒绝执行该操作"),
    OPERATE_FAILED("-11", "设备执行该操作失败"),
    SEND_FAILED("-12", "向设备发送消息失败"),
    CMD_ERROR("-13", "前台发送命令错误"),
    UPGRADE_VERSION_SAME("-14", "升级版本号相同"),
    UPGRADE__FAILE("-15", "升级失败");
    private String errorCode;
    private String errorMsg;

    private ErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static String findErrorMsgByCode(String errorCode) {
        String result = null;
        ErrorEnum[] fcs = ErrorEnum.values();
        for (ErrorEnum fe : fcs) {
            if (fe.getErrorCode().equals(errorCode)) {
                result = fe.getErrorMsg();
                break;
            }
        }
        return result;
    }
}
