package com.zyyan.ms.auth.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author cli
 * @since 2018-05-08
 */
@TableName("SYS_USER")

public class SysUser extends Model<SysUser> {
    
    //用户状态
    public static int LOCK_STATUS = 1;
    public static int UNLOCK_STATUS = 0;
    public static int EOF_STATUS = 2;

    private static final long serialVersionUID = 1L;

    public static final String REDIS_KEY_PREFIX = "HONGDIAN#AUTH#";

    @TableField("DEPT_ID")
    private Long deptId;
    @TableField("ADCD")
    private String adcd;
    @TableField("EMAIL")
    private String email;
    /**
     * 角色编号
     */
    @TableField("ROLE_ID")
    private Long roleId;
    /**
     * 启用状态 0：关闭 1：启用
     */
    @TableField("IS_ENABLED")
    private Integer isEnabled;
    /**
     * 手机号码
     */
    @TableField("PHONE")
    private String phone;
    /**
     * 有效结束时间
     */
    @TableField("ACTIVE_END_DATE")
    private Date activeEndDate;
    /**
     * 有效开始时间
     */
    @TableField("ACTIVE_BEGIN_DATE")
    private Date activeBeginDate;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;
    /**
     * 创建者
     */
    @TableField("CREATOR")
    private String creator;
    /**
     * 密码
     */
    @TableField("PWD")
    private String pwd;
    /**
     * 用户名
     */
    @TableField("NAME")
    private String name;
    /**
     * 用户登录名
     */
    @TableId("ID")
    private String id;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getAdcd() {
        return adcd;
    }

    public void setAdcd(String adcd) {
        this.adcd = adcd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Integer getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getActiveEndDate() {
        return activeEndDate;
    }

    public void setActiveEndDate(Date activeEndDate) {
        this.activeEndDate = activeEndDate;
    }

    public Date getActiveBeginDate() {
        return activeBeginDate;
    }

    public void setActiveBeginDate(Date activeBeginDate) {
        this.activeBeginDate = activeBeginDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRedisKey() {
        return REDIS_KEY_PREFIX + this.getId();
    }

}
