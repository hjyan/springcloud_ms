package com.zyyan.ms.auth.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.Utils;
import com.baomidou.mybatisplus.plugins.Page;
import com.zyyan.ms.auth.dto.RequestParamAddUser;
import com.zyyan.ms.auth.dto.SysUserDto;
import com.zyyan.ms.auth.entity.SysUser;
import com.zyyan.ms.auth.entity.SysUserRole;
import com.zyyan.ms.auth.service.ISysUserRoleService;
import com.zyyan.ms.auth.service.ISysUserService;
import com.zyyan.ms.auth.util.AuthConstants;
import com.zyyan.ms.common.entity.RespRes;
import com.zyyan.ms.common.util.CommUtils;
import com.zyyan.ms.common.util.JSONUtils;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author cli
 * @since 2018-05-08
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {

    @Resource
    private ISysUserService userService;
    @Resource
    private ISysUserRoleService userRoleService;

    /**
     * 查询用户列表
     * @param request
     * @param page
     * @param rows
     * @return 
     */
    @RequestMapping("/findPage")
    @ResponseBody
    public Page<SysUserDto> findPage(HttpServletRequest request, Integer page, Integer rows) {
        Page<SysUserDto> pg = new Page<SysUserDto>(page, rows);
        pg = userService.selectDtoPage(pg, getParamMap(request));
        return pg;
    }
    
    /**
     * 增加用户
     * @param user
     * @param roleIds
     * @return 
     */
    @RequestMapping("/add")
    @ResponseBody
    public RespRes add(String param) {
        RequestParamAddUser paramUser = (RequestParamAddUser)JSONUtils.readValue(param, RequestParamAddUser.class);
        SysUser user = paramUser.getUser();
        String roleIds = paramUser.getRoleIds();
        String errMsg = "";
        try {
            //ID不为空判断
            if (CommUtils.notNull(user.getId()) && CommUtils.notNull(roleIds)) {
                //用户重复性判断
                SysUser temp = userService.selectById(user.getId());
                if (null != temp) {
                    errMsg = "登录ID " + user.getId() + " 已经存在!";
                } else {
                    List<SysUserRole> userRoleList = new ArrayList<SysUserRole>();
                    for (String roleId : Arrays.asList(roleIds.split(","))) {
                        userRoleList.add(new SysUserRole(Long.valueOf(roleId), user.getId()));
                    }
                    userService.saveUser(user, userRoleList, AuthConstants.TYPE_INSERT);
                    return RespRes.ok();
                }
            }
        } catch (Exception ex) {
            log.error(null, ex);
            errMsg = "添加用户发生异常";
        }
        return RespRes.fail(errMsg);
    }

    /**
     * 更新用户信息
     * @param user
     * @param roleIds
     * @return 
     */
    @RequestMapping("/update")
    @ResponseBody
    public RespRes update(String param) {
        RequestParamAddUser paramUser = (RequestParamAddUser) JSONUtils.readValue(param, RequestParamAddUser.class);
        SysUser user = paramUser.getUser();
        String roleIds = paramUser.getRoleIds();
        String errMsg = "";
        try {
            List<SysUserRole> userRoleList = new ArrayList<SysUserRole>();
            for (String roleId : Arrays.asList(roleIds.split(","))) {
                userRoleList.add(new SysUserRole(Long.valueOf(roleId), user.getId()));
            }
            userService.saveUser(user, userRoleList, AuthConstants.TYPE_UPDATE);
            return RespRes.ok();
        } catch (Exception ex) {
            log.error(null, ex);
            errMsg = "更新用户发生异常";
        }
        return RespRes.fail(errMsg);
    }

    /**
     * 删除用户，必须指定用户ID
     * @param id
     * @return 
     */
    @RequestMapping("/delete")
    @ResponseBody
    public RespRes delete(String id) {
        String errMsg = "";
        try {
            if(null == id){
                return RespRes.fail("请指定要删除用户的ID");
            }
            Map<String, Object> delMap = new HashMap<String, Object>();
            delMap.put("USER_ID", id);
            userRoleService.deleteByMap(delMap);
            delMap.clear();
            delMap.put("ID", id);
            userService.deleteByMap(delMap);
            return RespRes.ok();
        } catch (Exception ex) {
            log.error(null, ex);
            errMsg = "删除用户异常";
        }
        return RespRes.fail(errMsg);
    }
    
    /**
     * 锁定或解锁用户
     * @param request
     * @return 
     */
    @RequestMapping(value = "/lockUser")
    @ResponseBody
    public RespRes lockUser(HttpServletRequest request) {
        String lock = request.getParameter("lock");
        String userId = request.getParameter("userId");
        try {
            if (CommUtils.isNull(userId) || CommUtils.isNull(lock)) {
                return RespRes.fail("请指定锁定/解锁用户ID以及锁定状态");
            } else {
                SysUser user = new SysUser();
                user.setId(userId);
                user.setIsEnabled(Integer.valueOf(lock));
                userService.updateById(user);
                return RespRes.ok();
            }
        } catch (Exception ex) {
            log.error(null, ex);
        }
        return RespRes.fail("操作异常");
    }
    
    @RequestMapping(value = "/changePwd")
    @ResponseBody
    public RespRes changePwd(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String oldPwd = request.getParameter("oldPwd");
        String newPwd = request.getParameter("newPwd");
        try {
            if(CommUtils.isNull(userId)){
                return RespRes.fail("请指定修改密码的userId");
            }
            if(CommUtils.isNull(newPwd)){
                return RespRes.fail("新密码不能为空");
            }
            SysUser user = userService.selectById(userId);
            if(null != user){
                if(user.getPwd().equals(oldPwd)){
                    user.setPwd(newPwd);
                    userService.updateById(user);
                    return RespRes.ok();
                }else{
                    return RespRes.fail("原密码输入错误");
                }
            }
        } catch (Exception ex) {
            log.error(null, ex);
        }
        return RespRes.fail("修改密码异常");
    }
    
    /**
     * 重置密码
     * @param request
     * @return 
     */
    @RequestMapping(value = "/resetPwd")
    @ResponseBody
    public RespRes resetPwd(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        try {
            if (CommUtils.isNull(userId)) {
                return RespRes.fail("请指定修改密码的 userId");
            }
            SysUser user = userService.selectById(userId);
            if(null != user){
                user.setPwd(Utils.md5("123456"));
                userService.updateById(user);
                return RespRes.ok();
            }else{
                return RespRes.fail("无此用户");
            }
        } catch (Exception ex) {
            log.error(null, ex);
        }
        return RespRes.fail("重置密码异常");
    }
}
