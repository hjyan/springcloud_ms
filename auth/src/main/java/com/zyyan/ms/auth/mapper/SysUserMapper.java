package com.zyyan.ms.auth.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.zyyan.ms.auth.dto.SysUserDto;
import com.zyyan.ms.auth.entity.SysUser;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author cli
 * @since 2018-05-08
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    public List<SysUserDto> selectDtoList(Pagination pg, Map<String, Object> queryMap);

    public List<SysUserDto> selectDtoList(Map<String, Object> queryMap);

}
