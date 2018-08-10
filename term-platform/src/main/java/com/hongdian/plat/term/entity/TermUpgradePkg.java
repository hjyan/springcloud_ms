package com.hongdian.plat.term.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 设备升级包表
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@TableName("TERM_UPGRADE_PKG")
public class TermUpgradePkg extends Model<TermUpgradePkg> {

	private static final long serialVersionUID = 1L;

	/**
	 * 自增ID
	 */
	@TableId(value = "ID", type = IdType.AUTO)
	private Integer id;
	/**
	 * 版本
	 */
	@TableField("VERSION")
	private String version;
	/**
	 * 升级包名称
	 */
	@TableField("NAME")
	private String name;
	/**
	 * 文件名
	 */
	@TableField("PKG_FILE_NAME")
	private String pkgFileName;
	/**
	 * 升级包路径
	 */
	@TableField("PKG_PATH")
	private String pkgPath;
	/**
	 * 大小
	 */
	@TableField("PKG_SIZE")
	private Long pkgSize;
	/**
	 * 设备类型ID
	 */
	@TableField("TERM_TYPE_ID")
	private Long termTypeId;
	/**
	 * 设备型号ID
	 */
	@TableField("TERM_MODEL_ID")
	private Long termModelId;
	/**
	 * CRC16
	 */
	@TableField("CRC16")
	private Long crc16;
	/**
	 * 备注
	 */
	@TableField("REMARK")
	private String remark;

	@TableField("TIME")
	private Date time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPkgFileName() {
		return pkgFileName;
	}

	public void setPkgFileName(String pkgFileName) {
		this.pkgFileName = pkgFileName;
	}

	public String getPkgPath() {
		return pkgPath;
	}

	public void setPkgPath(String pkgPath) {
		this.pkgPath = pkgPath;
	}

	public Long getPkgSize() {
		return pkgSize;
	}

	public void setPkgSize(Long pkgSize) {
		this.pkgSize = pkgSize;
	}

	public Long getTermTypeId() {
		return termTypeId;
	}

	public void setTermTypeId(Long termTypeId) {
		this.termTypeId = termTypeId;
	}

	public Long getTermModelId() {
		return termModelId;
	}

	public void setTermModelId(Long termModelId) {
		this.termModelId = termModelId;
	}

	public Long getCrc16() {
		return crc16;
	}

	public void setCrc16(Long crc16) {
		this.crc16 = crc16;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TermUpgradePkg{"
				+ ", id=" + id
				+ ", version=" + version
				+ ", name=" + name
				+ ", pkgFileName=" + pkgFileName
				+ ", pkgPath=" + pkgPath
				+ ", pkgSize=" + pkgSize
				+ ", termTypeId=" + termTypeId
				+ ", termModelId=" + termModelId
				+ ", crc16=" + crc16
				+ ", remark=" + remark
				+ "}";
	}
}
