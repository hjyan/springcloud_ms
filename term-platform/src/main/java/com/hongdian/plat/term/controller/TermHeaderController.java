package com.hongdian.plat.term.controller;

import com.hongdian.plat.term.constant.SystemConstants;
import com.hongdian.plat.term.entity.TermHeader;
import com.hongdian.plat.term.service.ITermHeaderService;
import com.hongdian.sys.common.util.CommUtils;
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
 * 设备消息头部表 前端控制器
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@Controller
@RequestMapping("/termHeader")
public class TermHeaderController {

	@Resource
	ITermHeaderService headerService;

	/**
	 * 查找 缓存设备状态
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findListFromCache")
	@ResponseBody
	public List<TermHeader> findListFromCache(HttpServletRequest request) {
		String termSn = request.getParameter("termSn");
		List<TermHeader> result = new ArrayList<TermHeader>();
		try {
			if (CommUtils.notNull(termSn)) {
				result.add(SystemConstants.TERM_HEADER_CACHE.get(termSn));
			} else {
				result.addAll(SystemConstants.TERM_HEADER_CACHE.values());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查找数据库中设备状态
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findListFromDb")
	@ResponseBody
	public List<TermHeader> findListFromDb(HttpServletRequest request) {
		String termSn = request.getParameter("termSn");
		Map<String, Object> map = new HashMap<String, Object>();
		List<TermHeader> result = null;
		try {
			result = headerService.selectByMap(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
