package com.hongdian.sys.auth.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hongdian.sys.auth.dto.RequestParamAddRole;
import com.hongdian.sys.auth.entity.SysMenu;
import com.hongdian.sys.auth.entity.SysRole;
import com.hongdian.sys.auth.entity.SysRoleMenu;
import com.hongdian.sys.auth.service.ISysMenuService;
import com.hongdian.sys.auth.service.ISysRoleMenuService;
import com.hongdian.sys.auth.service.ISysRoleService;
import com.hongdian.sys.auth.service.ISysUserRoleService;
import com.hongdian.sys.auth.util.UserUtils;
import com.hongdian.sys.common.entity.RespRes;
import com.hongdian.sys.common.util.CommUtils;
import com.hongdian.sys.common.util.JSONUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * ${table.comment} 前端控制器
 * </p>
 *
 * @author cli
 * @since 2018-05-07
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController {

    @Resource
    private ISysRoleService roleService;
    @Resource
    private ISysMenuService menuService;
    @Resource
    private ISysRoleMenuService roleMenuService;
    @Resource
    private ISysUserRoleService userRoleService;
    @Resource
    UserUtils userUtils;

    @RequestMapping("/findPage")
    @ResponseBody
    public Page<SysRole> findPage(HttpServletRequest request, Integer page, Integer rows) {
        Wrapper<SysRole> wrapper = new EntityWrapper<SysRole>();
        String name = request.getParameter("name");
        if(CommUtils.notNull(name)){
            wrapper.like("name", name);
        }
        Page<SysRole> pg = new Page<SysRole>(page, rows);
        try {
            pg = roleService.selectPage(pg, wrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pg;
    }
    
    @RequestMapping("/findList")
    @ResponseBody
    public List<SysRole> findList(HttpServletRequest request, Integer page, Integer rows) {
        Wrapper<SysRole> wrapper = new EntityWrapper<SysRole>();
        String name = request.getParameter("name");
        if (CommUtils.notNull(name)) {
            wrapper.like("name", name);
        }
        List<SysRole> list = roleService.selectList(wrapper);
        return list;
    }

    @RequestMapping("/add")
    @ResponseBody
    public RespRes add(String param) {
        RequestParamAddRole addRoleParam = (RequestParamAddRole) JSONUtils.readValue(param, RequestParamAddRole.class);
        SysRole role = addRoleParam.getRole();
        String menuIds = addRoleParam.getMenuIds();
        try {
            //判断角色名称不能重复
            Map<String, Object> queryMap = new HashMap<String, Object>();
            queryMap.put("name", role.getName());
            if (roleService.selectByMap(queryMap).isEmpty()) {
                roleService.insert(role);//自增主键
                //插入角色权限表
                for (String menuId : Arrays.asList(menuIds.split(","))) {
                    roleMenuService.insert(new SysRoleMenu(role.getId(), Long.valueOf(menuId)));
                }
                return RespRes.ok();
            }
        } catch (Exception e) {
            log.error(null, e);
        }
        return RespRes.fail("添加角色异常");
    }

    @RequestMapping("/update")
    @ResponseBody
    public RespRes update(String param) {
        RequestParamAddRole addRoleParam = (RequestParamAddRole) JSONUtils.readValue(param, RequestParamAddRole.class);
        SysRole role = addRoleParam.getRole();
        String menuIds = addRoleParam.getMenuIds();
        try {
            Integer roleId = role.getId();
            if (roleId == null) {
                return RespRes.fail("请指定角色ID");
            }
            roleService.updateById(role);
            //删除角色权限关联
            Map<String, Object> delMap = new HashMap<String, Object>();
            delMap.put("ROLE_ID", roleId);
            roleMenuService.deleteByMap(delMap);

            //插入角色权限关联
            for (String menuId : Arrays.asList(menuIds.split(","))) {
                roleMenuService.insert(new SysRoleMenu(roleId, Long.valueOf(menuId)));
            }

            return RespRes.ok();
        } catch (Exception e) {
            log.error(null, e);
        }
        return RespRes.fail("更新角色异常");
    }

    @RequestMapping("/delete")
    @ResponseBody
    public RespRes delete(Integer id) {
        if (id == null) {
            return RespRes.fail("请指定需要删除的角色ID");
        }
        try {
            //查询是否有用户拥有改角色
            Map<String, Object> queryMap = new HashMap<String, Object>();
            queryMap.put("ROLE_ID", id);
            if (!userRoleService.selectByMap(queryMap).isEmpty()) {
                return RespRes.fail("存在该角色的用户，无法删除角色");
            }
            roleService.deleteById(id);
            //删除角色权限关联
            queryMap.clear();
            queryMap.put("ROLE_ID", id);
            roleMenuService.deleteByMap(queryMap);
            return RespRes.ok();
        } catch (Exception e) {
            log.error(null, e);
        }
        return RespRes.fail("删除角色异常");
    }

    @RequestMapping(value = "findRoleMenu")
    @ResponseBody
    public List<SysMenu> findRoleMenu(HttpServletRequest request) {
        List<SysMenu> list = new ArrayList<SysMenu>();
        try {
            String roleId = request.getParameter("roleId");
            Map<String, Object> queryMap = new HashMap<String, Object>();
            //根据角色查询
            if (CommUtils.notNull(roleId)) {
                queryMap.put("roleId", roleId);
                list = menuService.selectRoleMenuList(queryMap);
            } else {
                //否则查询当前用户权限
                queryMap.put("userId", userUtils.getCurrentUserId());
                list = menuService.selectMenusByUserId(queryMap);
            }
        } catch (Exception ex) {
            log.error(null, ex);
        }
        return list;
    }

}
