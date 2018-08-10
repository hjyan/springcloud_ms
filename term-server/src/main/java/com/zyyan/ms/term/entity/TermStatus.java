package com.zyyan.ms.term.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 设备状态表
 * </p>
 *
 * @author cli
 * @since 2018-05-28
 */
@TableName("TERM_STATUS")
public class TermStatus extends Model<TermStatus> {

    private static final long serialVersionUID = 1L;

    /**
     * 设备序列号
     */
    @TableId("TERM_SN")
    private String termSn;
    /**
     * 状态名称
     */
    @TableField("STATUS_ID")
    private Integer statusId;
    /**
     * 状态值
     */
    @TableField("STATUS_VALUE")
    private String statusValue;
    @TableField("EXT")
    private String ext;

	public TermStatus() {
	}

	public TermStatus(String termSn, Integer statusId, String statusValue, String ext) {
		this.termSn = termSn;
		this.statusId = statusId;
		this.statusValue = statusValue;
		this.ext = ext;
	}


    public String getTermSn() {
        return termSn;
    }

    public void setTermSn(String termSn) {
        this.termSn = termSn;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    @Override
    protected Serializable pkVal() {
        return this.termSn;
    }

    @Override
    public String toString() {
        return "TermStatus{" +
        ", termSn=" + termSn +
        ", statusId=" + statusId +
        ", statusValue=" + statusValue +
        ", ext=" + ext +
        "}";
    }
}
