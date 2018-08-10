package com.hongdian.plat.term.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 参数分组
 * </p>
 *
 * @author zyyan
 * @since 2018-07-30
 */
@TableName("term_param_group")
public class TermParamGroup extends Model<TermParamGroup> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(value = "ID", type = IdType.AUTO)
	private String id;
	/**
	 * 参数分组编号
	 */
	@TableField("GROUP_ID")
	private String groupId;
	/**
	 * 名称
	 */
	@TableField("NAME")
	private String name;
	/**
	 * 终端类型
	 */
	@TableField("TERM_TYPE_ID")
	private String termTypeId;
	/**
	 * 终端型号
	 */
	@TableField("TERM_MODEL_ID")
	private String termModelId;
	/**
	 * 终端版本
	 */
	@TableField("TERM_VER_ID")
	private String termVerId;
	/**
	 * 所属分组
	 */
	@TableField("PID")
	private String pid;
	/**
	 * 使用状态：0、停用，1、使用（默认）
	 */
	@TableField("STATUS")
	private String status;
	/**
	 * 扩展字段
	 */
	@TableField("EXPAND")
	private String expand;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTermTypeId() {
		return termTypeId;
	}

	public void setTermTypeId(String termTypeId) {
		this.termTypeId = termTypeId;
	}

	public String getTermModelId() {
		return termModelId;
	}

	public void setTermModelId(String termModelId) {
		this.termModelId = termModelId;
	}

	public String getTermVerId() {
		return termVerId;
	}

	public void setTermVerId(String termVerId) {
		this.termVerId = termVerId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExpand() {
		return expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TermParamGroup{"
				+ ", id=" + id
				+ ", groupId=" + groupId
				+ ", name=" + name
				+ ", termTypeId=" + termTypeId
				+ ", termModelId=" + termModelId
				+ ", termVerId=" + termVerId
				+ ", pid=" + pid
				+ ", status=" + status
				+ ", expand=" + expand
				+ "}";
	}
}
