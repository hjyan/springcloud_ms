/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.sys.auth.dto;

import com.hongdian.sys.auth.entity.SysMenu;
import com.hongdian.sys.auth.entity.SysRole;
import com.hongdian.sys.auth.entity.SysUser;
import java.util.List;

/**
 * @date 2018-05-10 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
public class SysUserDto extends SysUser {

    private static final long serialVersionUID = 1L;
    private String deptName;
    private String adnm;
    private List<SysRole> roleList;//用户关联多个角色
    private List<SysMenu> menuList; //用户关联权限
    private String perms; //接口权限串
    private String roles; //接口角色串

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getAdnm() {
        return adnm;
    }

    public void setAdnm(String adnm) {
        this.adnm = adnm;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    public List<SysMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<SysMenu> menuList) {
        this.menuList = menuList;
    }

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

}
