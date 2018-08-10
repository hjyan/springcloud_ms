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
import com.hongdian.sys.auth.entity.SysDict;
import com.hongdian.sys.auth.service.ISysDictService;

/**
 * <p>
 * ${table.comment} 前端控制器
 * </p>
 *
 * @author cli
 * @since 2018-05-08
 */
@Controller
@RequestMapping("/sysDict")
public class SysDictController extends BaseController {

    @Resource
    private ISysDictService service;

    @RequestMapping("/findPage")
    @ResponseBody
    public List<SysDict> findPage(HttpServletRequest request, Integer page, Integer rows) {
        Wrapper<SysDict> wrapper = new EntityWrapper<>();
        Page<SysDict> pg = new Page<SysDict>(page, rows);
        try {
            pg = service.selectPage(pg, wrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<SysDict> list = pg.getRecords();
        return list;
    }

    @RequestMapping("/add")
    public void add(SysDict role) {
        service.insert(role);
    }

    @RequestMapping("/update")
    public void update(SysDict role) {
        service.updateById(role);
    }

    @RequestMapping("/delete")
    public void delete(Integer id) {
        service.deleteById(id);
    }
}
