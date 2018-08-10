package com.hongdian.sys.auth.util;

import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.hongdian.sys.auth.entity.SysUser;
import com.hongdian.sys.common.entity.JWTInfo;
import com.hongdian.sys.common.util.JSONUtils;

@Component
public class UserUtils extends ThreadLocal<String> {

    @Resource
    StringRedisTemplate template;

    public static JWTInfo User2JwtInfo(SysUser user) {
        String userId = user.getId();
        String name = user.getName();
        return new JWTInfo(userId, name);
    }

    public void setCurrentUserId(String userId) {
        this.set(userId);
    }

    public String getCurrentUserId() {
        return this.get();
    }

    public Object getCurrentUserFromRedis(String userId, Class<?> clazz) {
        String userJson = template.opsForValue().get(SysUser.REDIS_KEY_PREFIX + userId);
        Object user = JSONUtils.readValue(userJson, clazz);
        return user;
    }

}
