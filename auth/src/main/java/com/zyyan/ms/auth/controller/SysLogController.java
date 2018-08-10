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
import com.zyyan.ms.auth.entity.SysLog;
import com.zyyan.ms.auth.service.ISysLogService;

/**
 * <p>
 * ${table.comment} 前端控制器
 * </p>
 *
 * @author cli
 * @since 2018-05-08
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController extends BaseController {

    @Resource
    private ISysLogService service;

    @RequestMapping("/findPage")
    @ResponseBody
    public List<SysLog> findPage(HttpServletRequest request, Integer page, Integer rows) {
        Wrapper<SysLog> wrapper = new EntityWrapper<>();
        Page<SysLog> pg = new Page<SysLog>(page, rows);
        try {
            pg = service.selectPage(pg, wrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<SysLog> list = pg.getRecords();
        return list;
    }

    @RequestMapping("/add")
    public void add(SysLog role) {
        service.insert(role);
    }

    @RequestMapping("/update")
    public void update(SysLog role) {
        service.updateById(role);
    }

    @RequestMapping("/delete")
    public void delete(Integer id) {
        service.deleteById(id);
    }
}
