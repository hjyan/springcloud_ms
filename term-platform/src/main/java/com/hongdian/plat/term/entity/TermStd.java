package com.hongdian.plat.term.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 设备规约表
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@TableName("TERM_STD")
public class TermStd extends Model<TermStd> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId("ID")
    private Long id;
    /**
     * 规约名称
     */
    @TableField("NAME")
    private String name;
    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "TermStd{" +
        ", id=" + id +
        ", name=" + name +
        ", remark=" + remark +
        "}";
    }
}
