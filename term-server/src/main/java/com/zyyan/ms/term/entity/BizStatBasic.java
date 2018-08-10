package com.zyyan.ms.term.entity;

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
 *
 * </p>
 *
 * @author cli
 * @since 2018-06-02
 */
@TableName("BIZ_STAT_BASIC")
//@KeySequence(value = "STATION_ID", clazz = Integer.class)
public class BizStatBasic extends Model<BizStatBasic> {

    private static final long serialVersionUID = 1L;

    /**
     * 测站ID，7760和rtu前置无统一编码只能自增，如是水文，水资源类，另建标准表使用
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 测站编码
     */
    @TableField("STCD")
    private String stcd;
    /**
     * 测站名称
     */
    @TableField("STNM")
    private String stnm;
    /**
     * 测站类型
     */
    @TableField("STTP")
    private String sttp;
    /**
     * 行政区划代码
     */
    @TableField("ADCD")
    private String adcd;
    /**
     * 管理单位代码
     */
    @TableField("MNG_CD")
    private String mngCd;
    /**
     * 管理人
     */
    @TableField("DIRECTOR")
    private String director;
    /**
     * 电话号码
     */
    @TableField("TEL")
    private String tel;
    /**
     * 站址
     */
    @TableField("STLC")
    private String stlc;
    /**
     * 经度
     */
    @TableField("LGTD")
    private Double lgtd;
    /**
     * 维度
     */
    @TableField("LTTD")
    private Double lttd;
    /**
     * 建成日期
     */
    @TableField("BUILD_DATE")
    private Date buildDate;
    /**
     * 测站状态
     */
    @TableField("STATUS")
    private Long status;
    /**
     * 启用标志
     */
    @TableField("USFL")
    private Long usfl;
    /**
     * 基面名称
     */
    @TableField("DTMNM")
    private String dtmnm;
    /**
     * 基面高程
     */
    @TableField("DTMEL")
    private Double dtmel;
    /**
     * 基面修正值
     */
    @TableField("DTPR")
    private Double dtpr;
    /**
     * 更新时间（水文标准命名）
     */
    @TableField("MODITIME")
    private Date moditime;
    /**
     * 备注（水文标准命名）
     */
    @TableField("COMMENTS")
    private String comments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStcd() {
        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    public String getStnm() {
        return stnm;
    }

    public void setStnm(String stnm) {
        this.stnm = stnm;
    }

    public String getSttp() {
        return sttp;
    }

    public void setSttp(String sttp) {
        this.sttp = sttp;
    }

    public String getAdcd() {
        return adcd;
    }

    public void setAdcd(String adcd) {
        this.adcd = adcd;
    }

    public String getMngCd() {
        return mngCd;
    }

    public void setMngCd(String mngCd) {
        this.mngCd = mngCd;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getStlc() {
        return stlc;
    }

    public void setStlc(String stlc) {
        this.stlc = stlc;
    }

    public Double getLgtd() {
        return lgtd;
    }

    public void setLgtd(Double lgtd) {
        this.lgtd = lgtd;
    }

    public Double getLttd() {
        return lttd;
    }

    public void setLttd(Double lttd) {
        this.lttd = lttd;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getUsfl() {
        return usfl;
    }

    public void setUsfl(Long usfl) {
        this.usfl = usfl;
    }

    public String getDtmnm() {
        return dtmnm;
    }

    public void setDtmnm(String dtmnm) {
        this.dtmnm = dtmnm;
    }

    public Double getDtmel() {
        return dtmel;
    }

    public void setDtmel(Double dtmel) {
        this.dtmel = dtmel;
    }

    public Double getDtpr() {
        return dtpr;
    }

    public void setDtpr(Double dtpr) {
        this.dtpr = dtpr;
    }

    public Date getModitime() {
        return moditime;
    }

    public void setModitime(Date moditime) {
        this.moditime = moditime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BizStatBasic{"
                + ", id=" + id
                + ", stcd=" + stcd
                + ", stnm=" + stnm
                + ", sttp=" + sttp
                + ", adcd=" + adcd
                + ", mngCd=" + mngCd
                + ", director=" + director
                + ", tel=" + tel
                + ", stlc=" + stlc
                + ", lgtd=" + lgtd
                + ", lttd=" + lttd
                + ", buildDate=" + buildDate
                + ", status=" + status
                + ", usfl=" + usfl
                + ", dtmnm=" + dtmnm
                + ", dtmel=" + dtmel
                + ", dtpr=" + dtpr
                + ", moditime=" + moditime
                + ", comments=" + comments
                + "}";
    }
}
