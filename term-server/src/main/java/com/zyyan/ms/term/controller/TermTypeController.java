package com.zyyan.ms.term.controller;

import com.zyyan.ms.term.dto.TermTypeStatusDto;
import com.zyyan.ms.term.entity.TermType;
import com.zyyan.ms.term.service.ITermTypeService;
import com.zyyan.ms.common.util.CommUtils;
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
 * 设备类型表 前端控制器
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@Controller
@RequestMapping("/termType")
public class TermTypeController {

	@Resource
	ITermTypeService termTypeService;

	@RequestMapping(value = "findTermTypeStatusDtoList")
	@ResponseBody
	public List<TermTypeStatusDto> findTermTypeStatusDtoList(HttpServletRequest request) {
		String termType = request.getParameter("termType");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if (CommUtils.notNull(termType)) {
			queryMap.put("termType", termType);
		}
		return termTypeService.findTermTypeStatusDtoList(queryMap);
	}
    
    /**
     * 下拉列表
     */
    @RequestMapping(value = "/termTypes")
	@ResponseBody
    public List<TermType> findTermTypesList(HttpServletRequest request) {
		return termTypeService.findTermTypesList();
	}
}
