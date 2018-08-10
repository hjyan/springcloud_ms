/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.zyyan.ms.term.common.BaseController;
import com.zyyan.ms.term.constant.SystemConstants;
import com.zyyan.ms.term.dto.TaskDto;
import com.zyyan.ms.term.service.ITaskService;
import com.zyyan.ms.common.entity.RespRes;
import com.zyyan.ms.common.util.CommUtils;
import java.util.Arrays;
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
@RequestMapping("/task")
public class TaskController extends BaseController {

	@Resource
	ITaskService taskService;

	@RequestMapping("/findTaskPage")
	@ResponseBody
	public Page<TaskDto> findTaskPage(HttpServletRequest request, Integer page, Integer rows) {
		String id = request.getParameter("id");
		String termSn = request.getParameter("termSn");
		String ctrlName = request.getParameter("ctrlName");
		String status = request.getParameter("status");
		String source = request.getParameter("source");
		String statusList = request.getParameter("statusList");

		String sidx = request.getParameter("sidx");
		String sord = request.getParameter("sord");
		Page<TaskDto> pg = new Page(page, rows);
		pg.setAsc("desc".equals(sord) ? false : true);
		sidx = CommUtils.isNull(sidx) ? "id" : sidx;//默认为id 倒叙
		pg.setOrderByField(sidx);
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", id);
		queryMap.put("termSn", termSn);
		queryMap.put("ctrlName", ctrlName);
		queryMap.put("status", status);
		queryMap.put("source", source);
		if (CommUtils.notNull(statusList)) {
			queryMap.put("statusList", Arrays.asList(statusList.split(",")));
		}
		return taskService.findTaskPage(pg, queryMap);
	}

	@RequestMapping("/findTaskList")
	@ResponseBody
	public List<TaskDto> findTaskList(HttpServletRequest request, Integer page, Integer rows) {
		return this.findTaskPage(request, page, rows).getRecords();
	}

	@RequestMapping("/delete")
	@ResponseBody
	public RespRes delete(Integer id) {
		if (CommUtils.isNull(id)) {
			return RespRes.fail("请指定需要删除任务的ID");
		}
		//更新缓存
		SystemConstants.SCHEDULE_TASK_CACHE.remove(id);
		taskService.deleteById(id);
		return RespRes.ok();
	}
}
