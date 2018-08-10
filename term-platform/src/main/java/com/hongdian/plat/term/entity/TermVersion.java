package com.hongdian.plat.term.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 设备版本表
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@TableName("TERM_VERSION")
public class TermVersion extends Model<TermVersion> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId("ID")
    private Integer id;
    /**
     * 名称
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
        return "TermVersion{" +
        ", id=" + id +
        ", name=" + name +
        ", description=" + description +
        ", remark=" + remark +
        "}";
    }
}
