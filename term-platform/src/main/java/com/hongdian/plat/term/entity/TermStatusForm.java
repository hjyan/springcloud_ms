package com.hongdian.plat.term.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author cli
 * @since 2018-05-28
 */
@TableName("TERM_STATUS_FORM")
//@KeySequence(value = "TERM_STATUS_FORM_ID", clazz = Long.class)

public class TermStatusForm extends Model<TermStatusForm> {

	private static final long serialVersionUID = 1L;

	/**
	 * 表单ID
	 */
	@TableId(value = "ID", type = IdType.AUTO)
	private Long id;
	/**
	 * 设备编码
	 */
	@TableField("TERM_SN")
	private String termSn;
	/**
	 * 时间
	 */
	@TableField("TM")
	private Date tm;
	/**
	 * 设备状态报文类型
	 */
	@TableField("TYPE")
	private String type;
	/**
	 * 扩展字段
	 */
	@TableField("EXT")
	private String ext;

	public TermStatusForm(String termSn, Date tm, String type) {
		this.termSn = termSn;
		this.tm = tm;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTermSn() {
		return termSn;
	}

	public void setTermSn(String termSn) {
		this.termSn = termSn;
	}

	public Date getTm() {
		return tm;
	}

	public void setTm(Date tm) {
		this.tm = tm;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TermStatusForm{"
				+ ", id=" + id
				+ ", termSn=" + termSn
				+ ", tm=" + tm
				+ ", type=" + type
				+ ", ext=" + ext
				+ "}";
	}
}
