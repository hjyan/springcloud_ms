package com.zyyan.ms.auth.service;

import com.zyyan.ms.auth.entity.SysMenu;
import com.baomidou.mybatisplus.service.IService;
import com.zyyan.ms.auth.dto.SysUserDto;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * ${table.comment} 服务类
 * </p>
 *
 * @author cli
 * @since 2018-05-08
 */
public interface ISysMenuService extends IService<SysMenu> {
    
    /**
     * 获取用户权限树
     *
     * @param user
     * @throws Exception
     */
    public void setUserMenuTree(SysUserDto user) throws Exception;

    /**
     * 设置权限序号
     *
     * @param list
     * @throws Exception
     */
    public void setMenuOrder(List<SysMenu> list) throws Exception;

    /**
     *
     * @param queryMap
     * @return
     * @throws Exception
     */
    public List<SysMenu> selectRoleMenuList(Map<String, Object> queryMap) throws Exception;
    
    public List<SysMenu> selectMenusByUserId(Map<String, Object> queryMap) throws Exception;
	
}
