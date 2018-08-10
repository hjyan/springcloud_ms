/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zyyan.ms.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;

/**   
 * @date 2018-05-15 00:00:00
 * @Description: 
 * @author zyyan
 * Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
public class UserDto implements Serializable{
    private String perms; //接口权限串
    private String roles; //接口角色串

    public UserDto() {
    }
    
    public UserDto(Long deptId, String adcd, String email, Integer isEnabled, Date activeEndDate, String name, String id) {
        this.deptId = deptId;
        this.adcd = adcd;
        this.email = email;
        this.isEnabled = isEnabled;
        this.activeEndDate = activeEndDate;
        this.name = name;
        this.id = id;
    }
    
    @JsonIgnore
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public static final String REDIS_KEY_PREFIX = "HONGDIAN#USER#";
    private Long deptId;
    private String adcd;
    private String email;
    private Long roleId;
    private Integer isEnabled;
    private String phone;
    private Date activeEndDate;
    private Date createTime;
    private String creator;
    private String name;
    private String id;

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

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
    
    public String getRedisKey() {
        return REDIS_KEY_PREFIX + this.getId();
    }
    
}
