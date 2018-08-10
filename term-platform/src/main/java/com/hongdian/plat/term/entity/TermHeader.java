package com.hongdian.plat.term.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;

/**
 * <p>
 * 设备消息头部表
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@TableName("TERM_HEADER")
public class TermHeader extends Model<TermHeader> {

    private static final long serialVersionUID = 1L;

    /**
     * 站址
     */
    @TableId(value = "TERM_SN", type = IdType.INPUT)
    private String termSn;
    /**
     * 是否在线
     */
    @TableField("ONLINE_STATE")
    private Integer onlineState;
    /**
     * IP
     */
    @TableField("IP")
    private String ip;
    /**
     * 端口
     */
    @TableField("PORT")
    private Integer port;
    /**
     * 最后登录时间
     */
    @TableField("LAST_LOGIN_TIME")
    private Date lastLoginTime;
    /**
     * 最后登出时间
     */
    @TableField("LAST_LOGOUT_TIME")
    private Date lastLogoutTime;
    /**
     * 最后心跳时间
     */
    @TableField("LAST_HEART_TIME")
    private Date lastHeartTime;
    /**
     * 业务源地址
     */
    @TableField("SOURCE_BIZ")
    private String sourceBiz;
    /**
     * 运维源地址
     */
    @TableField("SOURCE_OPS")
    private String sourceOps;
    /**
     * 参数版本
     */
    @TableField("PARAM_VERSION")
    private String paramVersion;
    /**
     * 连接类型
     */
    @TableField("CONNECT_TYPE")
    private Long connectType;
    @TableField("EXT_ONE")
    private String extOne;
    @TableField("EXT_TWO")
    private String extTwo;


    public String getTermSn() {
        return termSn;
    }

    public void setTermSn(String termSn) {
        this.termSn = termSn;
    }

	public Integer getOnlineState() {
		return onlineState;
	}

	public void setOnlineState(Integer onlineState) {
		this.onlineState = onlineState;
	}

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastLogoutTime() {
        return lastLogoutTime;
    }

    public void setLastLogoutTime(Date lastLogoutTime) {
        this.lastLogoutTime = lastLogoutTime;
    }

    public Date getLastHeartTime() {
        return lastHeartTime;
    }

    public void setLastHeartTime(Date lastHeartTime) {
        this.lastHeartTime = lastHeartTime;
    }

    public String getSourceBiz() {
        return sourceBiz;
    }

    public void setSourceBiz(String sourceBiz) {
        this.sourceBiz = sourceBiz;
    }

    public String getSourceOps() {
        return sourceOps;
    }

    public void setSourceOps(String sourceOps) {
        this.sourceOps = sourceOps;
    }

	public String getParamVersion() {
		return paramVersion;
	}

	public void setParamVersion(String paramVersion) {
		this.paramVersion = paramVersion;
	}

    public Long getConnectType() {
        return connectType;
    }

    public void setConnectType(Long connectType) {
        this.connectType = connectType;
    }

    public String getExtOne() {
        return extOne;
    }

    public void setExtOne(String extOne) {
        this.extOne = extOne;
    }

    public String getExtTwo() {
        return extTwo;
    }

    public void setExtTwo(String extTwo) {
        this.extTwo = extTwo;
    }

    @Override
    protected Serializable pkVal() {
        return this.termSn;
    }

    @Override
    public String toString() {
        return "TermHeader{" +
        ", termSn=" + termSn +
        ", ip=" + ip +
        ", port=" + port +
        ", lastLoginTime=" + lastLoginTime +
        ", lastLogoutTime=" + lastLogoutTime +
        ", lastHeartTime=" + lastHeartTime +
        ", sourceBiz=" + sourceBiz +
        ", sourceOps=" + sourceOps +
        ", paramVersion=" + paramVersion +
        ", connectType=" + connectType +
        ", extOne=" + extOne +
        ", extTwo=" + extTwo +
        "}";
    }
}
