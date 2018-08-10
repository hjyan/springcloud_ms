/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zyyan.ms.auth.dto;

import com.zyyan.ms.auth.entity.SysUser;

/**   
 * @date 2018-05-11 00:00:00
 * @Description: 
 * @author zyyan
 * Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
public class RequestParamAddUser{
    private String roleIds;
    private SysUser user;

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    
}
