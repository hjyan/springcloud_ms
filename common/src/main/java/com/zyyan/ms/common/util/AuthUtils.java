package com.zyyan.ms.common.util;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.zyyan.ms.common.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@EnableFeignClients
public class AuthUtils {

	@Autowired
	AuthService authService;

	@FeignClient("auth-service")
	interface AuthService {

		@RequestMapping("/checkRole/{role}/{userId}")
		@ResponseBody
		public boolean checkRole(@PathVariable(name = "role") String role, @PathVariable(name = "userId") String userId);

		@RequestMapping("/checkPermission/{perm}/{userId}")
		@ResponseBody
		public boolean checkPermission(@PathVariable(name = "perm") String perm, @PathVariable(name = "userId") String userId);
				
	}

	@Resource
	RedisTemplate<String, String> redisTemplate;

	public boolean checkPermission(String permission, String userId) {
		String dtoStr = redisTemplate.opsForValue().get(UserDto.REDIS_KEY_PREFIX + userId);
		if (dtoStr != null && !dtoStr.equals("")) {
			UserDto dto = JSONUtils.readValue(dtoStr, UserDto.class);
			boolean valid = dto.getPerms().contains(permission);
			return valid;
		} else {
			return authService.checkPermission(permission, userId);
		}
	}

	public boolean checkRole(String role, String userId) {
		System.out.println("role:" + role + ", userId:" + userId);
		return true;
//		String dtoStr = redisTemplate.opsForValue().get(UserDto.REDIS_KEY_PREFIX + userId);
//		if (dtoStr != null && !dtoStr.equals("")) {
//			UserDto dto = JSONUtils.readValue(dtoStr, UserDto.class);
//			boolean valid = dto.getPerms().contains(permission);
//			return valid;
//		} else {
//			return authService.checkPermission(role, userId);
//		}
	}

}
