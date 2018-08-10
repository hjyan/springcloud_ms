package com.hongdian.plat.term.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cli
 * @since 2018-05-28
 */
@TableName("TERM_PARAM_VALUE")
public class TermParamValue extends Model<TermParamValue> {

    private static final long serialVersionUID = 1L;

    @TableId("TERM_SN")
    private String termSn;
    @TableField("TAG")
    private Long tag;
    @TableField("GROUP_ID")
    private Long groupId;
    @TableField("VALUE")
    private String value;


    public String getTermSn() {
        return termSn;
    }

    public void setTermSn(String termSn) {
        this.termSn = termSn;
    }

    public Long getTag() {
        return tag;
    }

    public void setTag(Long tag) {
        this.tag = tag;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    protected Serializable pkVal() {
        return this.termSn;
    }

    @Override
    public String toString() {
        return "TermParamValue{" +
        ", termSn=" + termSn +
        ", tag=" + tag +
        ", groupId=" + groupId +
        ", value=" + value +
        "}";
    }
}
