/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.exec.devenum;

/**
 * @Title: ScheduleTaskStatusEnum.class
 * @author zyyan
 * @date 2018-7-3 9:43:48
 * @version V1.0
 * @Description:预置任务状态
 */
public enum ScheduleTaskStatusEnum {

    NON_EXEC(0, "未执行"),
    SENDED(1, "已发送"),
    EXECING(11, "正在执行"),
    FAILURE(2, "失败"),
    CANCEL(3, "取消"),
    SUCCESS(4, "成功"),
    DELETE(5, "删除"),
    EXCEPTION(6, "异常");
    private int status;
    private String description;

    private ScheduleTaskStatusEnum(int status, String description) {
        this.status = status;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
