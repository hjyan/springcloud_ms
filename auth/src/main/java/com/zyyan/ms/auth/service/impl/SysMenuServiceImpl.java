package com.zyyan.ms.auth.service.impl;

import com.zyyan.ms.auth.entity.SysMenu;
import com.zyyan.ms.auth.mapper.SysMenuMapper;
import com.zyyan.ms.auth.service.ISysMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zyyan.ms.auth.dto.SysUserDto;
import com.zyyan.ms.common.util.CommUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment} 服务实现类
 * </p>
 *
 * @author cli
 * @since 2018-05-08
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Override
    public void setUserMenuTree(SysUserDto user) throws Exception {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("userId", user.getId());
        List<SysMenu> menuList = baseMapper.selectMenusByUserId(queryMap);
        if (CommUtils.isNull(menuList)) {
            return;
        }
        generateMenuTree(menuList);//构建权限树
        user.setMenuList(menuList);
    }

    @Override
    public void setMenuOrder(List<SysMenu> list) throws Exception {
        for (SysMenu menu : list) {
            baseMapper.updateById(menu);
        }
    }

    @Override
    public List<SysMenu> selectRoleMenuList(Map<String, Object> queryMap) throws Exception {
        return baseMapper.selectRoleMenuList(queryMap);
    }
    
    //根据权限列表构建权限树
    private void generateMenuTree(List<SysMenu> menuList) {
        List<SysMenu> allMenuList = new ArrayList<SysMenu>();
        allMenuList.addAll(menuList);
        TreeMap<Integer, List<SysMenu>> menuMap = new TreeMap<Integer, List<SysMenu>>();
        int level = 0;
        //按照菜单层级分类
        for (SysMenu m : menuList) {
            if (menuMap.get(m.getLevelMenu()) == null) {
                List<SysMenu> list_level = new ArrayList<SysMenu>();
                list_level.add(m);
                menuMap.put(m.getLevelMenu(), list_level);
            } else {
                menuMap.get(m.getLevelMenu()).add(m);
            }
            if (m.getLevelMenu()> level) {
                level = m.getLevelMenu();
            }
        }
        menuList.clear();//清空，准备存储下面处理完成的菜单
        //从最低层级一层一层往上聚合
        for (int i = level; i > 1; i--) {
            for (SysMenu m : menuMap.get(i)) {
                for (SysMenu pm : menuMap.get(i - 1)) {
                    if (m.getPid().intValue() == pm.getId().intValue()) {
                        if (pm.getSubMenu() == null) {
                            List<SysMenu> sub_menu = new ArrayList<SysMenu>();
                            sub_menu.add(m);
                            pm.setSubMenu(sub_menu);
                        } else {
                            pm.getSubMenu().add(m);
                        }
                        break;
                    }
                }
            }
        }
        menuList.addAll(menuMap.get(1));
    }

    @Override
    public List<SysMenu> selectMenusByUserId(Map<String, Object> queryMap) throws Exception {
        return baseMapper.selectMenusByUserId(queryMap);
    }
	
}
