package com.zyyan.ms.auth.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.zyyan.ms.auth.entity.SysUser;
import com.baomidou.mybatisplus.service.IService;
import com.zyyan.ms.auth.dto.SysUserDto;
import com.zyyan.ms.auth.entity.SysUserRole;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author cli
 * @since 2018-05-08
 */
public interface ISysUserService extends IService<SysUser> {

    SysUser login(String userId, String password);

    void logout(String userId);

    Page<SysUserDto> selectDtoPage(Page<SysUserDto> pg, Map<String, Object> queryMap);

    List<SysUserDto> selectDtoList(Map<String, Object> queryMap);

    void saveUser(SysUser sysUser, List<SysUserRole> userRoleList, Integer type);

    SysUserDto getRadisUser(String key);
}
