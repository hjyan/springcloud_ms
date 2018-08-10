package com.zyyan.ms.auth.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zyyan.ms.auth.annotation.Log;
import com.zyyan.ms.auth.entity.SysLog;
import com.zyyan.ms.auth.entity.SysUser;
import com.zyyan.ms.auth.service.ISysLogService;
import com.zyyan.ms.auth.service.ISysUserService;
import com.zyyan.ms.auth.util.UserUtils;
import com.zyyan.ms.common.util.JSONUtils;

@Aspect
@Component
public class LogAspect {

    @Resource
    ISysUserService userService;

    @Resource
    ISysLogService sysLogService;

    @Resource
    UserUtils userUtils;

    private final org.apache.commons.logging.Log log = LogFactory.getLog(getClass());

    public LogAspect() {
    }

    @AfterReturning(value = "@annotation(com.zyyan.ms.auth.annotation.Log)")
    public void logMethodAccessAfter(JoinPoint joinPoint) {
        log.info("***** Completed Log: " + joinPoint.getSignature().getDeclaringTypeName() + ":" + joinPoint.getSignature().getName() + " *****");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Log log = method.getAnnotation(Log.class);
        String userId = userUtils.getCurrentUserId();
        SysUser user = userService.selectById(userId);
        if (log != null) {
            SysLog sysLog = new SysLog();
            sysLog.setId(55555L);
            sysLog.setCreateTime(new Date());
            sysLog.setTitle(log.value());
            sysLog.setUserName((user != null) ? user.getName() : "system");
            sysLog.setUrl(request.getRequestURI().toString());
            sysLog.setParams(JSONUtils.writeValueAsString(request.getParameterMap()));
            sysLog.setStatus("成功");
            sysLog.setUserIp(request.getRemoteAddr());
            try {
                sysLogService.insertOrUpdateAllColumn(sysLog);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("***** Completed Log: " + joinPoint.getSignature().getDeclaringTypeName() + ":" + joinPoint.getSignature().getName() + " *****");
    }

    @Before(value = "@annotation(com.zyyan.ms.auth.annotation.Log)")
    public void logMethodAccessBefore(JoinPoint joinPoint) {
        log.info("***** Starting Log: " + joinPoint.getSignature().getDeclaringTypeName() + ":" + joinPoint.getSignature().getName() + " *****");
        System.out.println("***** Starting Log: " + joinPoint.getSignature().getDeclaringTypeName() + ":" + joinPoint.getSignature().getName() + " *****");
    }
}
