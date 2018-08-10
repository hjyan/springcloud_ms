package com.zyyan.ms.term.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 设备位置表
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@TableName("TERM_LOCATION")
public class TermLocation extends Model<TermLocation> {

    private static final long serialVersionUID = 1L;

    /**
     * 站址
     */
    @TableId("TERM_SN")
    private String termSn;
    /**
     * 位置
     */
    @TableField("LOCATION")
    private String location;
    /**
     * 维度
     */
    @TableField("LONGITUDE")
    private String longitude;
    /**
     * 经度
     */
    @TableField("LATITUDE")
    private String latitude;


    public String getTermSn() {
        return termSn;
    }

    public void setTermSn(String termSn) {
        this.termSn = termSn;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    protected Serializable pkVal() {
        return this.termSn;
    }

    @Override
    public String toString() {
        return "TermLocation{" +
        ", termSn=" + termSn +
        ", location=" + location +
        ", longitude=" + longitude +
        ", latitude=" + latitude +
        "}";
    }
}
