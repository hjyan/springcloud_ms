package com.hongdian.plat.term.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 测站类型
 * </p>
 *
 * @author xlhua
 * @since 2018-08-01
 */
@TableName("biz_station_type")
public class BizStationType extends Model<BizStationType> {

    private static final long serialVersionUID = 1L;

    /**
     * 测站类型编号
     */
    @TableId("CODE")
    private String code;
    /**
     * 测站类型名称
     */
    @TableField("NAME")
    private String name;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Serializable pkVal() {
        return this.code;
    }

    @Override
    public String toString() {
        return "BizStationType{" +
        "code=" + code +
        ", name=" + name +
        "}";
    }
}
