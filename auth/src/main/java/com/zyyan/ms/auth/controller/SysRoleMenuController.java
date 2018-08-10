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
import com.zyyan.ms.auth.entity.SysRoleMenu;
import com.zyyan.ms.auth.service.ISysRoleMenuService;

/**
 * <p>
 * 角色权限关系表 前端控制器
 * </p>
 *
 * @author cli
 * @since 2018-05-08
 */
@Controller
@RequestMapping("/SysRoleMenuMenu")
public class SysRoleMenuController extends BaseController {

    @Resource
    private ISysRoleMenuService service;

    @RequestMapping("/findPage")
    @ResponseBody
    public List<SysRoleMenu> findPage(HttpServletRequest request, Integer page, Integer rows) {
        Wrapper<SysRoleMenu> wrapper = new EntityWrapper<>();
        Page<SysRoleMenu> pg = new Page<SysRoleMenu>(page, rows);
        try {
            pg = service.selectPage(pg, wrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<SysRoleMenu> list = pg.getRecords();
        return list;
    }

    @RequestMapping("/add")
    public void add(SysRoleMenu role) {
        service.insert(role);
    }

    @RequestMapping("/update")
    public void update(SysRoleMenu role) {
        service.updateById(role);
    }

    @RequestMapping("/delete")
    public void delete(Integer id) {
        service.deleteById(id);
    }
}
