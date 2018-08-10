package com.zyyan.ms.term.controller;


import com.zyyan.ms.term.entity.BizDept;
import com.zyyan.ms.term.entity.TermModel;
import com.zyyan.ms.term.service.IBizDeptService;
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

