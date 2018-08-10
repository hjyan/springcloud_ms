package com.zyyan.ms.auth.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zyyan.ms.auth.entity.SysMenu;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * ${table.comment} Mapper 接口
 * </p>
 *
 * @author cli
 * @since 2018-05-08
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> selectMenusByUserId(Map<String, Object> queryMap);

    List<SysMenu> selectRoleMenuList(Map<String, Object> queryMap);

}
