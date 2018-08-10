package com.zyyan.ms.term.entity;

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
@TableName("TERM_PARAM_INFO")
public class TermParamInfo extends Model<TermParamInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId("ID")
    private Long id;
    /**
     * 标签
     */
    @TableField("TAG")
    private Long tag;
    /**
     * 参数分组ID
     */
    @TableField("GROUP_ID")
    private Long groupId;
    /**
     * 名称
     */
    @TableField("NAME")
    private String name;
    /**
     * 简称
     */
    @TableField("SHORT_NAME")
    private String shortName;
    /**
     * 最小长度
     */
    @TableField("MIN_LEN")
    private Long minLen;
    /**
     * 最大长度
     */
    @TableField("MAX_LEN")
    private Long maxLen;
    /**
     * 最小值
     */
    @TableField("MIN_VALUE")
    private String minValue;
    /**
     * 最大值
     */
    @TableField("MAX_VALUE")
    private String maxValue;
    /**
     * 值类型
     */
    @TableField("VALUE_TYPE")
    private String valueType;
    /**
     * 复合值数据内容
     */
    @TableField("COMBO_VALUE")
    private String comboValue;
    /**
     * 默认值
     */
    @TableField("DEFAULT_VALUE")
    private String defaultValue;
    /**
     * 是否只读
     */
    @TableField("READONLY")
    private Long readonly;
    /**
     * 允许为空
     */
    @TableField("ALLOW_NULL")
    private Long allowNull;
    /**
     * 是否可见
     */
    @TableField("IS_VISIBLE")
    private Long isVisible;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Long getMinLen() {
        return minLen;
    }

    public void setMinLen(Long minLen) {
        this.minLen = minLen;
    }

    public Long getMaxLen() {
        return maxLen;
    }

    public void setMaxLen(Long maxLen) {
        this.maxLen = maxLen;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getComboValue() {
        return comboValue;
    }

    public void setComboValue(String comboValue) {
        this.comboValue = comboValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Long getReadonly() {
        return readonly;
    }

    public void setReadonly(Long readonly) {
        this.readonly = readonly;
    }

    public Long getAllowNull() {
        return allowNull;
    }

    public void setAllowNull(Long allowNull) {
        this.allowNull = allowNull;
    }

    public Long getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Long isVisible) {
        this.isVisible = isVisible;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TermParamInfo{" +
        ", id=" + id +
        ", tag=" + tag +
        ", groupId=" + groupId +
        ", name=" + name +
        ", shortName=" + shortName +
        ", minLen=" + minLen +
        ", maxLen=" + maxLen +
        ", minValue=" + minValue +
        ", maxValue=" + maxValue +
        ", valueType=" + valueType +
        ", comboValue=" + comboValue +
        ", defaultValue=" + defaultValue +
        ", readonly=" + readonly +
        ", allowNull=" + allowNull +
        ", isVisible=" + isVisible +
        "}";
    }
}
