package com.hongdian.plat.term.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 设备状态组关系表
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@TableName("TERM_TYPE_STATUS")
public class TermTypeStatus extends Model<TermTypeStatus> {

	private static final long serialVersionUID = 1L;

	/**
	 * 设备类型ID
	 */
	@TableField("TERM_TYPE_ID")
	private Integer termTypeId;
	/**
	 * 状态组ID
	 */
	@TableField("STATUS_ID")
	private Integer statusId;

	public Integer getTermTypeId() {
		return termTypeId;
	}

	public void setTermTypeId(Integer termTypeId) {
		this.termTypeId = termTypeId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	@Override
	public String toString() {
		return "TermTypeStatus{"
				+ ", termTypeId=" + termTypeId
				+ ", statusId=" + statusId
				+ "}";
	}

	@Override
	protected Serializable pkVal() {
		return this.termTypeId;
	}
}
