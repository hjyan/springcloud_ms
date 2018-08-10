package com.hongdian.plat.term.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;

/**
 * <p>
 * 状态组表
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@TableName("TERM_STATUS_GROUP")
public class TermStatusGroup extends Model<TermStatusGroup> {

	private static final long serialVersionUID = 1L;

	/**
	 * 自增ID
	 */
	@TableId(value = "ID", type = IdType.AUTO)
	private Integer id;
	/**
	 * 状态组名称
	 */
	@TableField("NAME")
	private String name;
	/**
	 * 父组ID
	 */
	@TableField("PID")
	private Long pid;
	/**
	 * 备注
	 */
	@TableField("REMARK")
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TermStatusGroup{"
				+ ", id=" + id
				+ ", name=" + name
				+ ", pid=" + pid
				+ ", remark=" + remark
				+ "}";
	}
}
