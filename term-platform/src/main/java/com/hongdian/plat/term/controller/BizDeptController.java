package com.hongdian.plat.term.controller;


import com.hongdian.plat.term.entity.BizDept;
import com.hongdian.plat.term.entity.TermModel;
import com.hongdian.plat.term.service.IBizDeptService;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 行政区划信息 前端控制器
 * </p>
 *
 * @author xlhua
 * @since 2018-08-01
 */
@Controller
@RequestMapping("/term/bizDept")
public class BizDeptController {

    @Resource
    IBizDeptService bizDeptService;
    
    /**
     * 下拉所属行政区划列表
     */
    @RequestMapping(value = "/bizDepts")
	@ResponseBody
    public List<BizDept> findTermTypesList(HttpServletRequest request) {
		return bizDeptService.findBizDeptsList();
	}
}

