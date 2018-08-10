/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.sys.auth.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongdian.sys.auth.annotation.Log;
import com.hongdian.sys.auth.entity.SysUser;
import com.hongdian.sys.auth.exception.JwtTokenInvalidException;
import com.hongdian.sys.auth.service.ISysDeptService;
import com.hongdian.sys.auth.service.ISysMenuService;
import com.hongdian.sys.auth.service.ISysUserService;
import com.hongdian.sys.auth.util.UserUtils;
import com.hongdian.sys.common.constant.Constants;
import com.hongdian.sys.common.entity.JWTInfo;
import com.hongdian.sys.common.util.CommUtils;
import com.hongdian.sys.common.util.JwtUtils;
import com.hongdian.sys.common.util.TimeUtil;

/**
 * 登录管理 controller
 *
 * @author cli
 */
@Controller
@Configuration
@SuppressWarnings("all")
public class LoginController extends BaseController {

    @Resource
    private ISysUserService userService;

    @Resource
    private ISysMenuService menuService;

    @Resource
    private ISysDeptService deptService;

    @Resource
    private StringRedisTemplate redisTemplate;

    @Value(value = "${jwt.expireTime}")
    private int expire;

    @Value(value = "${jwt.priKeyPath}")
    private String priKeyPath;

    @Value(value = "${jwt.pubKeyPath}")
    private String pubKeyPath;

    /**
     * 登录验证
     *
     * @param request
     * @param response
     * @return, method = RequestMethod.POST
     */
    @ResponseBody
    @Log(value = "用户登录")
    @RequestMapping(value = "login.hd")
    public Map<String, Object> loginOld(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = CommUtils.getReturnMap();
        try {
            String userId = request.getParameter("username");
            String password = request.getParameter("password");
            SysUser userLogin = userService.login(userId, password);
            if (userLogin != null) {
                JWTInfo jwtInfo = UserUtils.User2JwtInfo(userLogin);
                String token = JwtUtils.generateToken(jwtInfo, priKeyPath, expire);
                CommUtils.setReturnMapFlagTrue(resultMap);
                CommUtils.setUserToken(resultMap, token);
                //+ deptService.selectById(userLogin.getName())
                CommUtils.setReturnMapInfo(resultMap, userLogin.getName() + "_" + "_"
                        + TimeUtil.convertDateToString(userLogin.getActiveEndDate(), TimeUtil.YMDHMS));
            }
        } catch (Exception ex) {
            log.error(null, ex);
            CommUtils.setReturnMapInfo(resultMap, ex.getMessage());
        }
        return resultMap;
    }

    /**
     * 注销
     *
     * @param request
     * @return
     */
    @Log("用户注销")
    @ResponseBody
    @RequestMapping(value = "logout.hd")
    public Map<String, Object> logout(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = CommUtils.getReturnMap();
        String token = request.getHeader("access_token");
        JWTInfo jwtInfo;
        try {
            jwtInfo = JwtUtils.getInfoFromToken(token, pubKeyPath);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JwtTokenInvalidException();
        }
        userService.logout(jwtInfo.getUserId());
        CommUtils.setReturnMapFlagTrue(resultMap);
        return resultMap;
    }

    /**
     * 检查用户登陆session add by gliu
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("checkSession.hd")
    @ResponseBody
    public String checkSession(HttpServletRequest request, HttpServletResponse response) {
        SysUser user = (SysUser) request.getSession().getAttribute(Constants.SESSION_USER);
        if (user == null) {
            return "false";
        }
        return "true";
    }

}
