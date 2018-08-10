package com.zyyan.ms.auth.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * ${table.comment}
 * </p>
 *
 * @author cli
 * @since 2018-05-08
 */
@TableName("SYS_DEPT")
public class SysDept extends Model<SysDept> {

    private static final long serialVersionUID = 1L;

    /**
     * 区划编码
     */
    @TableField("ADCD")
    private String adcd;
    /**
     * 上级部门编号
     */
    @TableField("PCODE")
    private String pcode;
    /**
     * 部门级别
     */
    @TableField("LEVEL")
    private String level;
    /**
     * 部门名称
     */
    @TableField("NAME")
    private String name;
    /**
     * 部门编号
     */
    @TableId("CODE")
    private String code;

    public String getAdcd() {
        return adcd;
    }

    public void setAdcd(String adcd) {
        this.adcd = adcd;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    protected Serializable pkVal() {
        return this.code;
    }

}
