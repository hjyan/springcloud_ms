package com.hongdian.plat.term.controller;

import com.hongdian.plat.term.common.BaseController;
import com.hongdian.plat.term.entity.TermStatusDesc;
import com.hongdian.plat.term.entity.TermStatusGroup;
import com.hongdian.plat.term.service.ITermStatusGroupService;
import com.hongdian.sys.common.entity.RespRes;
import com.hongdian.sys.common.util.CommUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import static org.springframework.data.history.RevisionSort.desc;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 状态组表 前端控制器
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@Controller
@RequestMapping("/termStatusGroup")
public class TermStatusGroupController extends BaseController {

	@Resource
	ITermStatusGroupService termGroupService;

	@RequestMapping(value = "add")
	@ResponseBody
	public RespRes add(TermStatusGroup group) {
		RespRes res = RespRes.fail(null);
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", group.getName());
		List<TermStatusGroup> list = termGroupService.selectByMap(queryMap);
		if (list.isEmpty()) {
			termGroupService.insert(group);//插入
			res.setCode(RespRes.CODE200);
		} else {
			res.setMsg("已经存在相似设备分组描述");
		}
		return res;
	}

	@RequestMapping(value = "update")
	@ResponseBody
	public RespRes update(TermStatusGroup desc) {
		RespRes res = RespRes.fail(null);
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", desc.getName());
		List<TermStatusGroup> list = termGroupService.selectByMap(queryMap);
		if (list.isEmpty()) {
			termGroupService.updateById(desc);//更新
			res.setCode(RespRes.CODE200);
		} else {
			res.setMsg("已经存在相似设备分组描述");
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

			termGroupService.deleteById(id);
			return RespRes.ok();
		} catch (Exception e) {
			logger.error(null, e);
		}
		return RespRes.fail("删除异常");
	}
}
