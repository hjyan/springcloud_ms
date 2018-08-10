package com.zyyan.ms.term.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zyyan.ms.term.common.BaseController;
import com.zyyan.ms.term.dto.TermStatusDescDto;
import com.zyyan.ms.term.entity.TermStatusDesc;
import com.zyyan.ms.term.service.ITermStatusDescService;
import com.zyyan.ms.term.service.ITermTypeStatusService;
import com.zyyan.ms.common.entity.RespRes;
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
 * 状态详情表 前端控制器
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@Controller
@RequestMapping("/termStatusDesc")
public class TermStatusDescController extends BaseController {

	@Resource
	private ITermStatusDescService termDescService;
	@Resource
	private ITermTypeStatusService termTypeStatusService;

	@RequestMapping(value = "findTermDescPage")
	@ResponseBody
	public Page<TermStatusDescDto> findTermDescPage(HttpServletRequest request, Integer page, Integer rows) {
		Page<TermStatusDescDto> pg = new Page(page, rows);
		String name = request.getParameter("name");
		String termType = request.getParameter("termType");
		String groupId = request.getParameter("groupId");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("search", name);
		queryMap.put("groupId", groupId);
		queryMap.put("termType", termType);
		return termDescService.findDescPage(pg, queryMap);
	}

	@RequestMapping(value = "findTermDescList")
	@ResponseBody
	public List<TermStatusDescDto> findTermDescList(HttpServletRequest request) {
		String name = request.getParameter("name");
		String termType = request.getParameter("termType");
		String groupId = request.getParameter("groupId");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("search", name);
		queryMap.put("termType", termType);
		queryMap.put("groupId", groupId);
		return termDescService.findDescList(queryMap);
	}

	@RequestMapping(value = "add")
	@ResponseBody
	public RespRes add(TermStatusDesc desc) {
		RespRes res = RespRes.fail(null);
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", desc.getName());
		queryMap.put("description", desc.getDescription());
		List<TermStatusDesc> list = termDescService.selectByMap(queryMap);
		if (list.isEmpty()) {
			termDescService.insert(desc);//插入
			res.setCode(RespRes.CODE200);
		} else {
			res.setMsg("已经存在相似设备状态描述");
		}
		return res;
	}

	@RequestMapping(value = "update")
	@ResponseBody
	public RespRes update(TermStatusDesc desc) {
		RespRes res = RespRes.fail(null);
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", desc.getName());
		queryMap.put("description", desc.getDescription());
		List<TermStatusDesc> list = termDescService.selectByMap(queryMap);
		if (list.isEmpty() || list.size() == 1 && list.get(0).getId().intValue() == desc.getId()) {
			termDescService.updateById(desc);//更新
			res.setCode(RespRes.CODE200);
		} else {
			res.setMsg("已经存在相似设备状态描述");
		}
		return res;
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public RespRes delete(Integer id) {
		try {
			if (CommUtils.isNull(id)) {
				return RespRes.fail("请指定要删除的Id");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status_id", id);
			termTypeStatusService.deleteByMap(map);//删除关联
			termDescService.deleteById(id);
			return RespRes.ok();
		} catch (Exception e) {
			logger.error(null, e);
		}
		return RespRes.fail("删除异常");
	}

}
