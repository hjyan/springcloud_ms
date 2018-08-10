package com.hongdian.sys.auth.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 角色权限关系表
 * </p>
 *
 * @author cli
 * @since 2018-05-08
 */
@TableName("SYS_ROLE_MENU")
public class SysRoleMenu extends Model<SysRoleMenu> {

    private static final long serialVersionUID = 1L;

    public SysRoleMenu() {
    }

    public SysRoleMenu(Integer roleId, Long menuId) {
        this.menuId = menuId;
        this.roleId = roleId;
    }

    /**
     * 权限编号
     */
    @TableField("MENU_ID")
    private Long menuId;
    /**
     * 角色编号
     */
    @TableId("ROLE_ID")
    private Integer roleId;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }


    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
