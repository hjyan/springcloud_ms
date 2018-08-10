package com.hongdian.plat.term.controller;


import com.hongdian.plat.term.entity.TermModel;
import com.hongdian.plat.term.entity.TermType;
import com.hongdian.plat.term.service.ITermModelService;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 设备型号表 前端控制器
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@Controller
@RequestMapping("/termModel")
public class TermModelController {

    @Resource
    ITermModelService modelService;
    
    /**
     * 下拉列表
     */
    @RequestMapping(value = "/termModels")
	@ResponseBody
    public List<TermModel> findTermTypesList(HttpServletRequest request) {
		return modelService.findTermModelsList();
	}
}

