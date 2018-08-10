package com.hongdian.plat.term.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 设备日志表
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@TableName("TERM_LOG")
public class TermLog extends Model<TermLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 站址
     */
    @TableField("TERM_SN")
    private String termSn;
    /**
     * 主题
     */
    @TableField("TOPIC")
    private String topic;
    /**
     * 请求操作
     */
    @TableField("ACTION")
    private String action;
    /**
     * 消息内容
     */
    @TableField("PAYLOAD")
    private String payload;
    /**
     * 接收时间
     */
    @TableField("RECIVE_TIME")
    private Date reciveTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTermSn() {
        return termSn;
    }

    public void setTermSn(String termSn) {
        this.termSn = termSn;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Date getReciveTime() {
        return reciveTime;
    }

    public void setReciveTime(Date reciveTime) {
        this.reciveTime = reciveTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TermLog{" +
        ", id=" + id +
        ", termSn=" + termSn +
        ", topic=" + topic +
        ", action=" + action +
        ", payload=" + payload +
        ", reciveTime=" + reciveTime +
        "}";
    }
}
