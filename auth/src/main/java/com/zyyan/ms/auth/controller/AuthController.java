package com.zyyan.ms.auth.controller;

import com.zyyan.ms.auth.dto.SysUserDto;
import com.zyyan.ms.auth.entity.SysMenu;
import com.zyyan.ms.auth.entity.SysRole;
import com.zyyan.ms.auth.service.ISysMenuService;
import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyyan.ms.auth.service.ISysUserService;
import com.zyyan.ms.common.util.CommUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AuthController extends BaseController {

	@Resource(name = "redisTemplate")
	RedisTemplate<String, String> template;
	
	@Resource
	ISysMenuService menuService;

	@Resource
	ISysUserService userService;

	@RequestMapping("/checkRole/{role}/{userId}")
	@ResponseBody
	public Boolean checkRole(String role, String userId) throws Exception {
		System.out.println("starting check role ..... ********************************");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("userId", userId);
		List<SysUserDto> userList = userService.selectDtoList(queryMap);
		if(!userList.isEmpty()){
			SysUserDto user = userList.get(0);
			if(!user.getRoleList().isEmpty()){
				for(SysRole r : user.getRoleList()){
					if(Integer.valueOf(role) == r.getId().intValue()){
						return true;
					}
				}
			}
		}
		return false;
	}

	@RequestMapping("/checkPermission/{perm}/{userId}")
	@ResponseBody
	public Boolean checkPermission(String perm, String userId) throws Exception {
		System.out.println("starting check permission ..... ********************************");
		if(CommUtils.isNull(perm)){
			return true;
		}
		
		if (CommUtils.isNull(userId)) {
			return false;
		}
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("userId", userId);
		List<SysMenu> menus = menuService.selectMenusByUserId(queryMap);
		for(SysMenu menu : menus){
			if(perm.equals(menu.getPerm())){
				return true;
			}
		}
		return false;
	}

}
