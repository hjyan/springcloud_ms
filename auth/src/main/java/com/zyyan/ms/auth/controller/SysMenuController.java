package com.zyyan.ms.auth.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zyyan.ms.auth.dto.SysUserDto;
import com.zyyan.ms.auth.entity.SysMenu;
import com.zyyan.ms.auth.service.ISysMenuService;
import com.zyyan.ms.auth.service.ISysRoleMenuService;
import com.zyyan.ms.auth.service.ISysUserService;
import com.zyyan.ms.auth.util.UserUtils;
import com.zyyan.ms.common.entity.RespRes;
import com.zyyan.ms.common.util.CommUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * ${table.comment} 前端控制器
 * </p>
 *
 * @author cli
 * @since 2018-05-08
 */
@Controller
@RequestMapping("/sysMenu")
public class SysMenuController extends BaseController {

    @Resource
    private ISysMenuService menuService;
    @Resource
    private ISysUserService userService;
    @Resource
    private ISysRoleMenuService roleMenuService;
    @Resource

    UserUtils userUtils;

    @RequestMapping("/findPage")
    @ResponseBody
    public Page<SysMenu> findPage(HttpServletRequest request, Integer page, Integer rows) {
        Wrapper<SysMenu> wrapper = new EntityWrapper<>();
        //添加条件
        Page<SysMenu> pg = new Page<SysMenu>(page, rows);
        try {
            pg = menuService.selectPage(pg, wrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pg;
    }

    @RequestMapping("/add")
    @ResponseBody
    public RespRes add(SysMenu menu) {
        try {
            menuService.insert(menu);
            return RespRes.ok();
        } catch (Exception e) {
            log.error(null, e);
        }
        return RespRes.fail("添加权限异常");
    }

    @RequestMapping("/update")
    @ResponseBody
    public RespRes update(SysMenu menu) {
        try {
            menuService.updateById(menu);
            return RespRes.ok();
        } catch (Exception e) {
            log.error(null, e);
        }
        return RespRes.fail("更新权限异常");
    }

    @RequestMapping("/delete")
    @ResponseBody
    public RespRes delete(Long id) {
        if (id == null) {
            return RespRes.fail("请指定需要删除的Menu");
        }
        try {
            menuService.deleteById(id);
            //删除权限角色关联表
            Map<String, Object> delmap = new HashMap<String, Object>();
            delmap.put("MENU_ID", id);
            roleMenuService.deleteByMap(delmap);
            return RespRes.ok();
        } catch (Exception e) {
            log.error(null, e);
        }
        return RespRes.fail("删除权限异常");
    }

    @RequestMapping(value = "/getMenus")
    @ResponseBody
    public List<SysMenu> getMenu(HttpServletRequest request, HttpServletResponse resp) {
        SysUserDto userLogin = getLoginUser();
        if (userLogin == null) {
            return null;
        }
        return userLogin.getMenuList();
    }

    /**
     * 通过菜单获取所属按钮
     *
     * @param request
     * @param resp
     * @return
     */
    @RequestMapping(value = "getPermsByMenuId")
    @ResponseBody
    public List<SysMenu> getPermsByMenuId(HttpServletRequest request, HttpServletResponse resp) {
        String menuId = request.getParameter("menuId");//查找给定menuId 的子菜单
        List<SysMenu> menuList = null;
        if (CommUtils.notNull(menuId)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pid", Long.valueOf(menuId));
            menuList = menuService.selectByMap(map);
        }
        return menuList;
    }

    private SysUserDto getLoginUser (){
        Map<String, Object> queryMap = new HashMap<String, Object>();
        String loginUserId = userUtils.getCurrentUserId();
        SysUserDto userLogin = null;
        if (CommUtils.notNull(loginUserId)) {
            queryMap.put("id", userUtils.getCurrentUserId());
            List<SysUserDto> list = userService.selectDtoList(queryMap);
            userLogin = list.isEmpty() ? null : list.get(0);
            try {
                menuService.setUserMenuTree(userLogin);
            } catch (Exception ex) {
                log.error(null, ex);
            }
        }
        return userLogin;
    }
}
