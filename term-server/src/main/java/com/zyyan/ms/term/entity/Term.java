package com.zyyan.ms.term.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;

/**
 * <p>
 * 设备基本信息表
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@TableName("TERM")
public class Term extends Model<Term> {

    private static final long serialVersionUID = 1L;

    /**
     * 站址
     */
    @TableId(value = "SN", type = IdType.INPUT)
    private String sn;
    /**
     * 设备名称
     */
    @TableField("NAME")
    private String name;
    /**
     * 别名
     */
    @TableField("ALIAS")
    private String alias;
    /**
     * 父设备ID
     */
    @TableField("PID")
    private String pid;
    /**
     * 供应商
     */
    @TableField("SUPPLIER")
    private String supplier;
    /**
     * App版本
     */
    @TableField("APP_VERSION")
    private String appVersion;
    /**
     * 软件版本
     */
    @TableField("SOFT_VERSION")
    private String softVersion;
    /**
     * 硬件版本
     */
    @TableField("HARD_VERSION")
    private String hardVersion;
    /**
     * 最后使用时间
     */
    @TableField("USE_TIME")
    private Date useTime;
    /**
     * 类型ID
     */
    @TableField("TERM_TYPE_ID")
    private String termTypeId;
    /**
     * 设备规约ID
     */
    @TableField("TERM_STD_ID")
    private String termStdId;
    /**
     * 型号
     */
    @TableField("TERM_MODEL")
    private String termModel;
    /**
     * 版本
     */
    @TableField("TERM_VERSION")
    private String termVersion;
    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;
    @TableField("STATION_ID")
    private String stationId;
    @TableField("EXT_TWO")
    private String extTwo;

	public Term() {
	}

	public Term(String sn, String name, String softVersion, String hardVersion, Date useTime, String termTypeId, String termModel) {
		this.sn = sn;
		this.name = name;
		this.softVersion = softVersion;
		this.hardVersion = hardVersion;
		this.useTime = useTime;
		this.termTypeId = termTypeId;
		this.termModel = termModel;
	}

	public Term(String sn, String name, String alias, String pid, String supplier, String appVersion, String softVersion, String hardVersion, Date useTime, String termTypeId, String termStdId, String termModel, String termVersion, String remark, String stationId, String extTwo) {
		this.sn = sn;
		this.name = name;
		this.alias = alias;
		this.pid = pid;
		this.supplier = supplier;
		this.appVersion = appVersion;
		this.softVersion = softVersion;
		this.hardVersion = hardVersion;
		this.useTime = useTime;
		this.termTypeId = termTypeId;
		this.termStdId = termStdId;
		this.termModel = termModel;
		this.termVersion = termVersion;
		this.remark = remark;
		this.stationId = stationId;
		this.extTwo = extTwo;
	}
	
//	public Term getTerm(String sn, String name, String softVersion, String hardVersion, Date useTime, String termTypeId, String termModel){
//		return new Term(sn, name, null, null, "hongdian", null, softVersion, hardVersion,  useTime,  termTypeId, null		, termModel, null
//		, null, null
//		,null)
//	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getTermTypeId() {
		return termTypeId;
	}

	public void setTermTypeId(String termTypeId) {
		this.termTypeId = termTypeId;
	}

	public String getTermStdId() {
		return termStdId;
	}

	public void setTermStdId(String termStdId) {
		this.termStdId = termStdId;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getSoftVersion() {
        return softVersion;
    }

    public void setSoftVersion(String softVersion) {
        this.softVersion = softVersion;
    }

    public String getHardVersion() {
        return hardVersion;
    }

    public void setHardVersion(String hardVersion) {
        this.hardVersion = hardVersion;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public String getTermModel() {
        return termModel;
    }

    public void setTermModel(String termModel) {
        this.termModel = termModel;
    }

    public String getTermVersion() {
        return termVersion;
    }

    public void setTermVersion(String termVersion) {
        this.termVersion = termVersion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

    public String getExtTwo() {
        return extTwo;
    }

    public void setExtTwo(String extTwo) {
        this.extTwo = extTwo;
    }

    @Override
    protected Serializable pkVal() {
        return this.sn;
    }

    @Override
    public String toString() {
        return "Term{" +
        ", sn=" + sn +
        ", name=" + name +
        ", alias=" + alias +
        ", pid=" + pid +
        ", supplier=" + supplier +
        ", appVersion=" + appVersion +
        ", softVersion=" + softVersion +
        ", hardVersion=" + hardVersion +
        ", useTime=" + useTime +
        ", termTypeId=" + termTypeId +
        ", termStdId=" + termStdId +
        ", termModel=" + termModel +
        ", termVersion=" + termVersion +
        ", remark=" + remark +
        ", stationId=" + stationId +
        ", extTwo=" + extTwo +
        "}";
    }
}
