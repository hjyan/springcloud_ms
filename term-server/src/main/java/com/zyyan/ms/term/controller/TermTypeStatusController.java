package com.zyyan.ms.term.controller;

import com.zyyan.ms.term.entity.TermTypeStatus;
import com.zyyan.ms.term.service.ITermTypeStatusService;
import com.zyyan.ms.common.entity.RespRes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 设备状态组关系表 前端控制器
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@Controller
@RequestMapping("/termTypeStatus")
public class TermTypeStatusController {

	@Resource
	ITermTypeStatusService termTypeStatusService;

	@RequestMapping(value = "addBatch")
	@ResponseBody
	public RespRes addBatch(@RequestBody List<TermTypeStatus> termTypeStatusList) {
		if (termTypeStatusList.isEmpty()) {
			return RespRes.fail("list 没有数据");
		}
		termTypeStatusService.insertBatch(termTypeStatusList);
		return RespRes.ok();
	}

	@RequestMapping(value = "updateBatch")
	@ResponseBody
	public RespRes updateBatch(@RequestBody List<TermTypeStatus> termTypeStatusList) {
		if (termTypeStatusList.isEmpty()) {
			return RespRes.fail("list 没有数据");
		}
		termTypeStatusService.updateBatch(termTypeStatusList);
		return RespRes.ok();
	}

}
