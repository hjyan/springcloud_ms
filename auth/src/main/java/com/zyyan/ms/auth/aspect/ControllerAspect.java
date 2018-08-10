package com.zyyan.ms.auth.aspect;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zyyan.ms.auth.dto.SysUserDto;
import com.zyyan.ms.auth.service.ISysUserService;
import com.zyyan.ms.auth.util.StringUtils;
import com.zyyan.ms.auth.util.UserUtils;
import com.zyyan.ms.common.entity.JWTInfo;
import com.zyyan.ms.common.util.CommUtils;
import com.zyyan.ms.common.util.JwtUtils;
import org.aspectj.lang.annotation.Before;

//@Aspect
@Component
public class ControllerAspect {

    @Value(value = "${jwt.pubKeyPath}")
    private String pubKeyPath;

    @Resource
    UserUtils userUtils;
    @Resource
    ISysUserService userService;

    /**
     * Handle to the log file
     */
    private final Log log = LogFactory.getLog(getClass());

    public ControllerAspect() {
    }

    @AfterReturning("execution(* com.zyyan.ms.auth.controller.*.*(..))")
    public void logMethodAccessAfter(JoinPoint joinPoint) {
        String typeName = joinPoint.getSignature().getDeclaringTypeName();
        log.info("***** Completed: " + StringUtils.getClassNameFromQualifiedName(typeName) + ":" + joinPoint.getSignature().getName() + " *****");
    }

    @Before("execution(* com.zyyan.ms.auth.controller.Sys*.*(..))")
    public void logMethodAccessBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String tokenHeader = request.getHeader("access_token");
		if(CommUtils.isNull(tokenHeader)){
			tokenHeader = request.getParameter("access_token");
		}
        JWTInfo info = JwtUtils.getInfoFromToken(tokenHeader, pubKeyPath);
        userUtils.setCurrentUserId(info.getUserId());
    }
}
