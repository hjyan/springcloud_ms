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
import com.hongdian.sys.auth.entity.SysDictType;
import com.hongdian.sys.auth.service.ISysDictTypeService;

/**
 * <p>
 * ${table.comment} 前端控制器
 * </p>
 *
 * @author cli
 * @since 2018-05-08
 */
@Controller
@RequestMapping("/irrig/SysDictTypeType")
public class SysDictTypeController extends BaseController {

    @Resource
    private ISysDictTypeService service;

    @RequestMapping("/findPage")
    @ResponseBody
    public List<SysDictType> findPage(HttpServletRequest request, Integer page, Integer rows) {
        Wrapper<SysDictType> wrapper = new EntityWrapper<>();
        Page<SysDictType> pg = new Page<SysDictType>(page, rows);
        try {
            pg = service.selectPage(pg, wrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<SysDictType> list = pg.getRecords();
        return list;
    }

    @RequestMapping("/add")
    public void add(SysDictType role) {
        service.insert(role);
    }

    @RequestMapping("/update")
    public void update(SysDictType role) {
        service.updateById(role);
    }

    @RequestMapping("/delete")
    public void delete(Integer id) {
        service.deleteById(id);
    }
}
