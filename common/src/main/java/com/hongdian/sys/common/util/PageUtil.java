/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hongdian.sys.common.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hongdian.sys.common.entity.Page;

/**
* @Description: todo
* @author gliu
* @date 2016-9-9 
* tags
* Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
*/
public class PageUtil {
    /**
     * 翻页
     */
    public static final String PARAM_NM_PAGE_NO = "page";//页面获取当前页码参数
    public static final String PARAM_NM_PAGE_SIZE = "rows";//页面获取每页数量参数
    public static final String PROP_NM_START_PAGE = "startCount";//传入后台查询的开始页数
    public static final String PROP_NM_PAGE_SIZE = "pageSize";//传入后台查询的记录数量

    /**
     * 获取页码
     *
     * @param request
     * @return
     */
    public static Integer getPageNoByWeb(HttpServletRequest request) {
        //获取页数
        String page = request.getParameter(PARAM_NM_PAGE_NO);
        if (null != page && !"".equals(page)) {
            return Integer.parseInt(request.getParameter(PARAM_NM_PAGE_NO));
        } else {
            return null;
        }
    }

    /**
     * 设置起始记录数和记录数量到map，mssql
     *
     * @param request
     * @param queryMap
     * @param page
     * @return
     */
    public static Integer setPageParam(HttpServletRequest request, Map<String, Object> queryMap, Page page) {
        //获取每页显示的记录数
        String pageSizeStr = request.getParameter(PARAM_NM_PAGE_SIZE);
        String pageNoStr = request.getParameter(PARAM_NM_PAGE_NO);
        if (pageNoStr != null && pageSizeStr != null && !"".equals(pageNoStr) && !"".equals(pageSizeStr)) {
            int pageSize = Integer.parseInt(pageSizeStr);
            int pageNo = Integer.parseInt(pageNoStr);
            int startCount = (pageNo - 1) * pageSize + 1;
            int endCount = pageNo * pageSize;
            if (null != queryMap) {
                queryMap.put(PROP_NM_START_PAGE, startCount);
                queryMap.put(PROP_NM_PAGE_SIZE, endCount);
            }
            page.setPageSize(pageSize);
            page.setPage(pageNo);
            return pageSize;
        } else {
            return null;
        }
    }
    
    public static void setSordFiled(HttpServletRequest request, Map<String, Object> queryMap) {
        String sidx = request.getParameter("sidx");
        String sord = request.getParameter("sord");
        if(!CommUtils.isNull(sidx)){
            queryMap.put("sidx", strFromHumpToUnderLine(sidx));
        }
        queryMap.put("sord", sord);
    }
    
    private static String strFromHumpToUnderLine(String humpStr){//将驼峰写法转换成下划线写法
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while(i < humpStr.length()){
            Character c = humpStr.charAt(i);
            if(Character.isUpperCase(c)){
                sb.append("_").append(c.toString().toLowerCase());
            }else{
                sb.append(c);
            }
            i++;
        }
        return sb.toString();
    }
}