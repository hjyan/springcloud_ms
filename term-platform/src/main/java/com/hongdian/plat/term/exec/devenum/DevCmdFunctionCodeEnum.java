/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.exec.devenum;

/**
 *
 * @author Administrator
 */
public enum DevCmdFunctionCodeEnum {

    //查询预置任务
    FIND_TASK("find_task_page"),
    //删除预置任务
    DELETE_TASK("delete_task"),
    //设备控制
    DEV_CONTROL("dev_ctrl"),
    //重新预置任务
    RESET_SEND_TASK("reset_send_task");
    private String functionName;

    private DevCmdFunctionCodeEnum(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public static DevCmdFunctionCodeEnum findByFunctionName(String functionName) throws RuntimeException {
        DevCmdFunctionCodeEnum result = null;
        DevCmdFunctionCodeEnum[] fcs = DevCmdFunctionCodeEnum.values();
        for (DevCmdFunctionCodeEnum fe : fcs) {
            if (functionName.equals(fe.getFunctionName())) {
                result = fe;
                break;
            }
        }
        return result;
    }
}
