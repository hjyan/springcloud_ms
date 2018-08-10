package com.hongdian.plat.term.entity;

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
@TableName("TERM_STATUS_H")
public class TermStatusH extends Model<TermStatusH> {

    private static final long serialVersionUID = 1L;

    /**
     * 设备序列号
     */
    @TableId("STATUS_FORM_ID")
    private Long statusFormId;
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

	public TermStatusH(Long statusFormId, Integer statusId, String statusValue) {
		this.statusFormId = statusFormId;
		this.statusId = statusId;
		this.statusValue = statusValue;
	}

    public Long getStatusFormId() {
        return statusFormId;
    }

    public void setStatusFormId(Long statusFormId) {
        this.statusFormId = statusFormId;
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
        return this.statusFormId;
    }

    @Override
    public String toString() {
        return "TermStatusH{" +
        ", statusFormId=" + statusFormId +
        ", statusId=" + statusId +
        ", statusValue=" + statusValue +
        ", ext=" + ext +
        "}";
    }
}
