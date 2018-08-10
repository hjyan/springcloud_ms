package com.zyyan.ms.common.entity;

import java.beans.Transient;
import java.io.Serializable;

/**
 * 
 * @author licong
 *
 */
public class JWTInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private transient final String REDIS_CACHE_PREFIX="HONGDIAN#USER#";
    
    private String userId;
    private String name;
    
	public JWTInfo() {
		super();
	}
	
	public JWTInfo(String userId, String name) {
		super();
		this.userId = userId;
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Transient
	public String getRedisKey() {
		return this.REDIS_CACHE_PREFIX+this.userId;
	}

	@Override
	public String toString() {
		return "JWTInfo [userId=" + userId + ", name=" + name + "]";
	}
	
}
