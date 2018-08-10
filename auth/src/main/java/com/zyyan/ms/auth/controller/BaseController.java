package com.zyyan.ms.auth.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {
    
    public final Logger log = LoggerFactory.getLogger(this.getClass());

    public static Map<String, Object> getParamMap(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, String[]> parameterMap = new HashMap<String, String[]>();
        parameterMap.putAll(request.getParameterMap());
        //去掉page 和 rows 条件
        parameterMap.remove("page");
        parameterMap.remove("rows");
        if (parameterMap != null) {
            Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
            for (Entry<String, String[]> entry : entrySet) {
                if (null != entry.getValue()) {
                    map.put(entry.getKey(), entry.getValue()[0]);
                }
            }
        }
        return map;
    }
}
