package com.hongdian.plat.term.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongdian.plat.term.entity.Term;
import com.hongdian.plat.term.service.ITermService;
import com.hongdian.sys.common.util.CommUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 设备基本信息表 前端控制器
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@Controller
@RequestMapping("/term")
public class TermController {

    @Resource
    ITermService termService;
    
    /**
     * 下拉term列表
     */
    @RequestMapping("/terms")
    @ResponseBody
    public List<Term> findTerms(HttpServletRequest request) {
        List<Term> list=null ;
        list= termService.selectTermList();
        return list;
    }
    
}

