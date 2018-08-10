package com.zyyan.ms.auth.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * ${table.comment}
 * </p>
 *
 * @author cli
 * @since 2018-05-08
 */
@TableName("SYS_USER_ROLE")
public class SysUserRole extends Model<SysUserRole> {

    private static final long serialVersionUID = 1L;

    @TableField("ROLE_ID")
    private Long roleId;
    @TableField("USER_ID")
    private String userId;
    public SysUserRole(){
    }
    public SysUserRole(Long roleId, String userId) {
        this.roleId = roleId;
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    protected Serializable pkVal() {
        return 1;
    }

}
