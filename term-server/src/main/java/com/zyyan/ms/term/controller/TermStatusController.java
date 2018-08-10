package com.zyyan.ms.term.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.zyyan.ms.term.dto.StationDto;
import com.zyyan.ms.term.dto.TermStatusDto;
import com.zyyan.ms.term.dto.TermStatusExtend;
import com.zyyan.ms.term.entity.TermStatus;
import com.zyyan.ms.term.service.ITermStatusService;
import com.zyyan.ms.common.util.CommUtils;
import java.util.ArrayList;
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
 * 设备状态表 前端控制器
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@Controller
@RequestMapping("/termStatus")
public class TermStatusController {

	@Resource
	ITermStatusService termStatusService;

	@RequestMapping(value = "/findPage")
	@ResponseBody
	public Page findPage(HttpServletRequest request, Integer page, Integer rows) {
		Page<TermStatusDto> pg = new Page(page, rows);
		String termSn = request.getParameter("termSn");
		String statusName = request.getParameter("statusName");
		String statusValueCondition = request.getParameter("statusValueCondition");

		List<String> termSnList = new ArrayList<String>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if (CommUtils.notNull(statusName) && CommUtils.notNull(statusValueCondition)) {
			queryMap.put("statusName", statusName);
			queryMap.put("statusValueCondition", statusValueCondition);
			List<TermStatusExtend> statusList = termStatusService.findTermStatusLsit(queryMap);
			if (!statusList.isEmpty()) {
				for (TermStatusExtend status : statusList) {
					termSnList.add(status.getTermSn());
				}
			}
		}
		queryMap.put("termSn", termSn);
		if (!termSnList.isEmpty()) {
			queryMap.put("termSnList", termSnList);
		}
		return termStatusService.findPage(pg, queryMap);
	}

	@RequestMapping(value = "/findTermStatusList")
	@ResponseBody
	public List<TermStatusExtend> findTermStatusList(HttpServletRequest request) {
		String termSn = request.getParameter("termSn");
		String termType = request.getParameter("termTypeId");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("termSn", termSn);
		queryMap.put("termType", termType);
		return termStatusService.findTermStatusLsit(queryMap);
	}
}
