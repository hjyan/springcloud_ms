package com.hongdian.sys.auth.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hongdian.sys.auth.entity.SysDept;
import com.hongdian.sys.auth.service.ISysDeptService;
import com.hongdian.sys.common.entity.RespRes;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * ${table.comment} 前端控制器
 * </p>
 *
 * @author cli
 * @since 2018-05-08
 */
@Controller
@RequestMapping("/sysDept")
public class SysDeptController extends BaseController {

	@Resource
	private ISysDeptService service;

	@RequestMapping("/findPage")
	@ResponseBody
	public Page<SysDept> findPage(HttpServletRequest request, Integer page, Integer rows) {
		Wrapper<SysDept> wrapper = new EntityWrapper<>();
		Page<SysDept> pg = new Page<SysDept>(page, rows);
		try {
			pg = service.selectPage(pg, wrapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pg;
	}

	@RequestMapping("/findList")
	@ResponseBody
	public List<SysDept> findList(HttpServletRequest request) {
		List<SysDept> list = null;
		try {
			list = service.selectByMap(getParamMap(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping("/add")
	@ResponseBody
	public RespRes add(SysDept dept) {
		service.insert(dept);
		return RespRes.ok();
	}

	@RequestMapping("/update")
	@ResponseBody
	public RespRes update(SysDept dept) {
		EntityWrapper<SysDept> ew = new EntityWrapper<SysDept>();
		ew.eq("CODE", dept.getCode());
		service.update(dept, ew);
		return RespRes.ok();
	}

	@RequestMapping("/delete")
	@ResponseBody
	public RespRes delete(Integer code) {
		if (null == code) {
			return RespRes.fail("请指定code");
		}
		Map<String, Object> delmap = new HashMap<String, Object>();
		delmap.put("code", code);
		service.deleteByMap(delmap);
		return RespRes.ok();
	}
}
