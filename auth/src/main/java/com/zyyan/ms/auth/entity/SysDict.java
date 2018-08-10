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
@TableName("SYS_DICT")
public class SysDict extends Model<SysDict> {

    private static final long serialVersionUID = 1L;

    @TableField("PID")
    private Long pid;
    @TableField("ODR")
    private Integer odr;
    @TableField("TYPE_ID")
    private String typeId;
    @TableField("LABEL")
    private String label;
    @TableField("VAL")
    private String val;
    @TableId("ID")
    private String id;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getOdr() {
        return odr;
    }

    public void setOdr(Integer odr) {
        this.odr = odr;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
