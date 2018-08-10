/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.sys.auth.dto;

import com.hongdian.sys.auth.entity.SysRole;

/**
 * @date 2018-05-12 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
public class RequestParamAddRole {

    private SysRole role;
    private String menuIds;

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    public String getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }
    
    
}
