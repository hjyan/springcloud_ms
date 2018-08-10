package com.zyyan.ms.term.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 泄洪预警播放状态实时表
 * </p>
 *
 * @author xlhua
 * @since 2018-07-19
 */
@TableName("flood_playsta_cur")
public class FloodPlaystaCur extends Model<FloodPlaystaCur> {

    private static final long serialVersionUID = 1L;

    /**
     * 设备编码
     */
    @TableId("TERM_SN")
    private String termSn;
    /**
     * 播放随机数
     */
    @TableField("ALARM_INFO_ID")
    private String alarmInfoId;
    /**
     * 网关编码（先不用）
     */
    @TableField("GATE")
    private String gate;
    /**
     * 上报时间
     */
    @TableField("DATA_TM")
    private Date dataTm;
    /**
     * 播放状态
     */
    @TableField("PLAY_STATUS")
    private int playStatus;
    /**
     * 播放内容
     */
    @TableField("CONTENT")
    private String content;

    public int getPlayStatus() {
        return playStatus;
    }

    public void setPlayStatus(int playStatus) {
        this.playStatus = playStatus;
    }

    public String getTermSn() {
        return termSn;
    }

    public void setTermSn(String termSn) {
        this.termSn = termSn;
    }

    public String getAlarmInfoId() {
        return alarmInfoId;
    }

    public void setAlarmInfoId(String alarmInfoId) {
        this.alarmInfoId = alarmInfoId;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public Date getDataTm() {
        return dataTm;
    }

    public void setDataTm(Date dataTm) {
        this.dataTm = dataTm;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    protected Serializable pkVal() {
        return this.termSn;
    }

    @Override
    public String toString() {
        return "FloodPlaystaCur{" +
        "termSn=" + termSn +
        ", alarmInfoId=" + alarmInfoId +
        ", gate=" + gate +
        ", dataTm=" + dataTm +
        ", playStatus=" + playStatus +
        ", content=" + content +
        "}";
    }
}
