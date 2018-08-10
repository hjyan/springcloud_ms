/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.controller;

import com.zyyan.ms.term.common.BaseController;
import com.zyyan.ms.term.dto.TermParamDto;
import com.zyyan.ms.term.service.ITermParamValueService;
import com.zyyan.ms.common.util.CommUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author win 10
 */
@Controller
@RequestMapping("/termParam")
public class TermParamController extends BaseController {

	@Resource
	ITermParamValueService paramService;

	@RequestMapping(value = "/findTermParamDtoList")
	@ResponseBody
	public List<TermParamDto> findTermParamDtoList(HttpServletRequest request) {
		String termSn = request.getParameter("termSn");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("termSn", termSn);
		return paramService.selectTermParamDtoList(queryMap);
	}

	@RequestMapping(value = "/findTermParamDtoMap")
	@ResponseBody
	public Collection<List<TermParamDto>> findTermParamDtoMap(HttpServletRequest request) {
		String termSn = request.getParameter("termSn");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("termSn", termSn);
		return exchangeByGroup(paramService.selectTermParamDtoList(queryMap));
	}

	private Collection<List<TermParamDto>> exchangeByGroup(List<TermParamDto> dtoList) {
		Map<String, List<TermParamDto>> termMap = new HashMap<String, List<TermParamDto>>();
		for (TermParamDto dto : dtoList) {
			if (CommUtils.isNull(dto.getGroup())) {
				continue;
			}
			String key = dto.getGroup().getId();
			if (CommUtils.notNull(termMap.get(key))) {
				termMap.get(key).add(dto);
			} else {
				List<TermParamDto> list = new ArrayList<TermParamDto>();
				list.add(dto);
				termMap.put(key, list);
			}
		}
		return termMap.values();
	}

}
