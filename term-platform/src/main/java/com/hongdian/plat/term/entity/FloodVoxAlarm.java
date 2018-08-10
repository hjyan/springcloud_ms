package com.hongdian.plat.term.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 泄洪预警手动语音报警表
 * </p>
 *
 * @author xlhua
 * @since 2018-07-19
 */
@TableName("flood_vox_alarm")
public class FloodVoxAlarm extends Model<FloodVoxAlarm> {

    private static final long serialVersionUID = 1L;

    /**
     * 播放随机数
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 发送类型（gprs、message）
     */
    @TableField("SEND_TYPE")
    private String sendType;
    /**
     * 预警类型（预警发布/MP3点播）
     */
    @TableField("ALARM_TYPE")
    private String alarmType;
    /**
     * 网关数
     */
    @TableField("GATE_NUM")
    private Integer gateNum;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;
    /**
     * 创建人
     */
    @TableField("CREATE_PERSON")
    private String createPerson;
    /**
     * 发布时间
     */
    @TableField("PUB_TIME")
    private Date pubTime;
    /**
     * 发布状态
     */
    @TableField("PUB_STATUS")
    private Integer pubStatus;
    /**
     * 预警标题
     */
    @TableField("TITLE")
    private String title;
    /**
     * 预警等级
     */
    @TableField("LEVEL")
    private Integer level;
    /**
     * 播放内容
     */
    @TableField("CONTENT")
    private String content;
    /**
     * 播放次数
     */
    @TableField("PLAY_NUM")
    private Integer playNum;
    /**
     * mp3文件类型
     */
    @TableField("FILE_TYPE")
    private String fileType;
    /**
     * LORA终端数
     */
    @TableField("SN_NUM")
    private Integer snNum;
    /**
     * 任务id
     */
    @TableField("TASK_ID")
    private Integer taskId;

    public FloodVoxAlarm() {
    }

    public FloodVoxAlarm(Integer id, String sendType, String alarmType, Integer gateNum, Date createTime, String createPerson, Date pubTime, Integer pubStatus, String title, Integer level, String content, Integer playNum, String fileType, Integer snNum, Integer taskId) {
        this.id = id;
        this.sendType = sendType;
        this.alarmType = alarmType;
        this.gateNum = gateNum;
        this.createTime = createTime;
        this.createPerson = createPerson;
        this.pubTime = pubTime;
        this.pubStatus = pubStatus;
        this.title = title;
        this.level = level;
        this.content = content;
        this.playNum = playNum;
        this.fileType = fileType;
        this.snNum = snNum;
        this.taskId = taskId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public String getSendType() {
        return sendType;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public Integer getGateNum() {
        return gateNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public Integer getPubStatus() {
        return pubStatus;
    }

    public String getTitle() {
        return title;
    }

    public Integer getLevel() {
        return level;
    }

    public String getContent() {
        return content;
    }

    public Integer getPlayNum() {
        return playNum;
    }

    public String getFileType() {
        return fileType;
    }

    public Integer getSnNum() {
        return snNum;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public void setGateNum(Integer gateNum) {
        this.gateNum = gateNum;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public void setPubStatus(Integer pubStatus) {
        this.pubStatus = pubStatus;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPlayNum(Integer playNum) {
        this.playNum = playNum;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setSnNum(Integer snNum) {
        this.snNum = snNum;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
    
    @Override
    public String toString() {
        return "FloodVoxAlarm{" +
        "id=" + id +
        ", sendType=" + sendType +
        ", alarmType=" + alarmType +
        ", gateNum=" + gateNum +
        ", createTime=" + createTime +
        ", createPerson=" + createPerson +
        ", pubTime=" + pubTime +
        ", pubStatus=" + pubStatus +
        ", title=" + title +
        ", level=" + level +
        ", content=" + content +
        ", playNum=" + playNum +
        ", fileType=" + fileType +
        ", snNum=" + snNum +
        ", taskId=" + taskId +
        "}";
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
