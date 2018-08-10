package com.hongdian.sys.auth.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.Utils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hongdian.sys.auth.dto.SysUserDto;
import com.hongdian.sys.auth.entity.SysMenu;
import com.hongdian.sys.auth.entity.SysUser;
import com.hongdian.sys.auth.entity.SysUserRole;
import com.hongdian.sys.auth.exception.UsernameOrPasswordInvalidException;
import com.hongdian.sys.auth.mapper.SysUserMapper;
import com.hongdian.sys.auth.mapper.SysUserRoleMapper;
import com.hongdian.sys.auth.service.ISysMenuService;
import com.hongdian.sys.auth.service.ISysUserRoleService;
import com.hongdian.sys.auth.service.ISysUserService;
import com.hongdian.sys.auth.util.AuthConstants;
import com.hongdian.sys.auth.util.FileUtil;
import com.hongdian.sys.common.dto.UserDto;
import com.hongdian.sys.common.util.JSONUtils;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author cli
 * @since 2018-05-08
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SysUserServiceImpl.class.getName());
	@Resource
	SysUserMapper userMapper;
	@Resource
	SysUserRoleMapper userRoleMapper;
	@Resource
	ISysMenuService menuService;
	@Resource
	ISysUserRoleService userRoleService;
	@Resource
	StringRedisTemplate redisTemplate;

	@Value(value = "${jwt.expireTime}")
	private int expire;

	@Override
	public SysUser login(String userId, String password) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("ID", userId);
		columnMap.put("PWD", password);
		SysUser user;
		try {
			user = userMapper.selectByMap(columnMap).get(0);
			if (user != null) {
				cacheUser(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameOrPasswordInvalidException();
		}
		return user;
	}

	@Override
	public void logout(String userId) {
		clearUserCache(userId);
	}

	private void cacheUser(SysUser user) {
		UserDto userDto = new UserDto(user.getDeptId(), user.getAdcd(), user.getEmail(), user.getIsEnabled(), user.getActiveEndDate(), user.getName(), user.getId());
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("userId", user.getId());
			List<SysMenu> menuList = menuService.selectMenusByUserId(queryMap);
			queryMap.clear();
			queryMap.put("USER_ID", user.getId());
			List<SysUserRole> userRoleList = userRoleService.selectByMap(queryMap);
			StringBuilder perms = new StringBuilder();
			for (SysMenu menu : menuList) {
				perms.append(menu.getPerm()).append(",");
			}

			StringBuilder roleIds = new StringBuilder();
			for (SysUserRole userRole : userRoleList) {
				roleIds.append(userRole.getRoleId()).append(",");
			}
			userDto.setPerms(perms.toString());
			userDto.setRoles(roleIds.toString());

			redisTemplate.opsForValue().set(user.getRedisKey(), JSONUtils.writeValueAsString(userDto), expire, TimeUnit.SECONDS);
		} catch (Exception ex) {
			logger.error(null, ex);
		}
	}

	private void clearUserCache(String userId) {
		try {
			redisTemplate.delete(SysUser.REDIS_KEY_PREFIX + userId);
		} catch (Exception e) {
			logger.error(null, e);
		}
	}

	@Override
	public Page<SysUserDto> selectDtoPage(Page<SysUserDto> pg, Map<String, Object> queryMap) {
		return pg.setRecords(baseMapper.selectDtoList(pg, queryMap));
	}

	@Override
	public List<SysUserDto> selectDtoList(Map<String, Object> queryMap) {
		return baseMapper.selectDtoList(queryMap);
	}

	@SuppressWarnings("static-access")
	@Override
	public void saveUser(SysUser sysUser, List<SysUserRole> userRoleList, Integer type) {
		if (type == AuthConstants.TYPE_INSERT) {//插入操作
			sysUser.setCreateTime(new Date());//用户创建时间
			sysUser.setIsEnabled(sysUser.UNLOCK_STATUS);//用户有效状态
			sysUser.setPwd(Utils.md5("123456"));//新建用户默认密码
			this.insert(sysUser);//插入用户
			for (SysUserRole userRole : userRoleList) {//插入用户角色关联信息
				userRoleMapper.insert(userRole);
			}
		} else if (type == AuthConstants.TYPE_UPDATE) {//更新操作
			Wrapper<SysUserRole> wrapper = new EntityWrapper<SysUserRole>();
			wrapper.eq("USER_ID", sysUser.getId());
			userRoleMapper.delete(wrapper);//删除用户角色关联信息
			for (SysUserRole userRole : userRoleList) {//插入用户角色关联信息
				userRoleMapper.insert(userRole);
			}
			this.updateById(sysUser);
		}
	}

	@Override
	public SysUserDto getRadisUser(String key) {
		String value = key == null ? null : redisTemplate.opsForValue().get(key);
		return value == null ? null : (SysUserDto) JSONUtils.readValue(value, SysUser.class);
	}
}
