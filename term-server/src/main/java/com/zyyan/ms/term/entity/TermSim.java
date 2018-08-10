package com.zyyan.ms.term.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 设备SIM表
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@TableName("TERM_SIM")
public class TermSim extends Model<TermSim> {

    private static final long serialVersionUID = 1L;

    /**
     * 站址
     */
    @TableId("TERM_SN")
    private String termSn;
    /**
     * SIM
     */
    @TableField("SIM")
    private String sim;
    /**
     * SIM类型
     */
    @TableField("SIM_TYPE")
    private String simType;
    /**
     * SIM网络类型
     */
    @TableField("SIM_NETWORK")
    private String simNetwork;
    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;


    public String getTermSn() {
        return termSn;
    }

    public void setTermSn(String termSn) {
        this.termSn = termSn;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getSimType() {
        return simType;
    }

    public void setSimType(String simType) {
        this.simType = simType;
    }

    public String getSimNetwork() {
        return simNetwork;
    }

    public void setSimNetwork(String simNetwork) {
        this.simNetwork = simNetwork;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.termSn;
    }

    @Override
    public String toString() {
        return "TermSim{" +
        ", termSn=" + termSn +
        ", sim=" + sim +
        ", simType=" + simType +
        ", simNetwork=" + simNetwork +
        ", remark=" + remark +
        "}";
    }
}
