package com.hongdian.plat.term.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;

/**
 * <p>
 * 状态详情表
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@TableName("TERM_STATUS_DESC")
public class TermStatusDesc extends Model<TermStatusDesc> {

	private static final long serialVersionUID = 1L;

	/**
	 * 自增ID
	 */
	@TableId(value = "ID", type = IdType.AUTO)
	private Integer id;
	/**
	 * 状态组ID
	 */
	@TableField("GROUP_ID")
	private Long groupId;
	/**
	 * 状态名称
	 */
	@TableField("NAME")
	private String name;
	/**
	 * 描述
	 */
	@TableField("DESCRIPTION")
	private String description;
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

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return "TermStatusDesc{"
				+ ", id=" + id
				+ ", groupId=" + groupId
				+ ", name=" + name
				+ ", description=" + description
				+ ", remark=" + remark
				+ "}";
	}
}
