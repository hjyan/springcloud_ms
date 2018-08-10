package com.hongdian.sys.auth.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;

/**
 * <p>
 * ${table.comment}
 * </p>
 *
 * @author gliu
 * @since 2018-05-07
 */
@TableName("SYS_ROLE")
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

    @TableField("CREATER_DATE")
    private Date createrDate;
    @TableField("CREATER_NAME")
    private String createrName;
    @TableField("CREATER_ID")
    private String createrId;
    @TableField("NAME")
    private String name;
    @TableId(value = "ID")
    private Integer id;

    public Date getCreaterDate() {
        return createrDate;
    }

    public void setCreaterDate(Date createrDate) {
        this.createrDate = createrDate;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
