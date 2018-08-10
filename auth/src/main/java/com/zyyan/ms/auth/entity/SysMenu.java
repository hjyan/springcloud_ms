package com.zyyan.ms.auth.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * ${table.comment}
 * </p>
 *
 * @author cli
 * @since 2018-05-08
 */
@TableName("SYS_MENU")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    @TableField("VISIBLE")
    private Integer visible;
    @TableField("ICON")
    private String icon;
    @TableField("PERM")
    private String perm;
    @TableField("URL_CONTROL")
    private String urlControl;
    @TableField("ODR")
    private Integer odr;
    @TableField("TYPE")
    private Integer type;
    @TableField("LEVEL")
    private Integer levelMenu;
    @TableField("URL_VIEW")
    private String urlView;
    @TableField("NAME")
    private String name;
    @TableField("PID")
    private Long pid;
    @TableId(value = "ID")
    private Long id;
    @TableField(exist = false)
    private List<SysMenu> subMenu;
    
    @TableField(exist = false)
    private String hasRole;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPerm() {
        return perm;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }

    public String getUrlControl() {
        return urlControl;
    }

    public void setUrlControl(String urlControl) {
        this.urlControl = urlControl;
    }

    public String getUrlView() {
        return urlView;
    }

    public void setUrlView(String urlView) {
        this.urlView = urlView;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Integer getOdr() {
        return odr;
    }

    public void setOdr(Integer odr) {
        this.odr = odr;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLevelMenu() {
        return levelMenu;
    }

    public void setLevelMenu(Integer levelMenu) {
        this.levelMenu = levelMenu;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public List<SysMenu> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(List<SysMenu> subMenu) {
        this.subMenu = subMenu;
    }

    public String getHasRole() {
        return hasRole;
    }

    public void setHasRole(String hasRole) {
        this.hasRole = hasRole;
    }

}
