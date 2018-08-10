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
import com.zyyan.ms.auth.entity.SysUserRole;
import com.zyyan.ms.auth.service.ISysUserRoleService;

/**
 * <p>
 * ${table.comment} 前端控制器
 * </p>
 *
 * @author cli
 * @since 2018-05-08
 */
@Controller
@RequestMapping("/sysUserRole")
public class SysUserRoleController extends BaseController {

    @Resource
    private ISysUserRoleService service;

    @RequestMapping("/findPage")
    @ResponseBody
    public List<SysUserRole> findPage(HttpServletRequest request, Integer page, Integer rows) {
        Wrapper<SysUserRole> wrapper = new EntityWrapper<>();
        Page<SysUserRole> pg = new Page<SysUserRole>(page, rows);
        try {
            pg = service.selectPage(pg, wrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<SysUserRole> list = pg.getRecords();
        return list;
    }

    @RequestMapping("/add")
    public void add(SysUserRole role) {
        service.insert(role);
    }

    @RequestMapping("/update")
    public void update(SysUserRole role) {
        service.updateById(role);
    }

    @RequestMapping("/delete")
    public void delete(Integer id) {
        service.deleteById(id);
    }
}
