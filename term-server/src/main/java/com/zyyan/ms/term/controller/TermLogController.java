package com.zyyan.ms.term.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zyyan.ms.term.entity.TermLog;
import com.zyyan.ms.term.service.ITermLogService;
import com.zyyan.ms.common.util.CommUtils;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 设备日志表 前端控制器
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@Controller
@RequestMapping("/termLog")
public class TermLogController {

	@Resource
	ITermLogService termLogService;

	@RequestMapping("/findPage")
	@ResponseBody
	public Page<TermLog> findPage(HttpServletRequest request, Integer page, Integer rows) {
		String termSn = request.getParameter("termSn");
		String sidx = request.getParameter("sidx");
		String sord = request.getParameter("sord");
		Page<TermLog> pg = new Page(page, rows);
		pg.setAsc("desc".equals(sord) ? false : true);
		sidx = CommUtils.isNull(sidx) ? "id" : sidx;//默认为id 倒叙
		pg.setOrderByField(sidx);
		Wrapper<TermLog> ew = new EntityWrapper<TermLog>();
		ew.eq("term_sn", termSn);
		return termLogService.selectPage(pg, ew);
	}
}
