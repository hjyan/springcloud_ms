package com.zyyan.ms.term.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zyyan.ms.term.dto.TaskDto;
import com.zyyan.ms.term.entity.FloodPlaystaCur;
import com.zyyan.ms.term.service.IFloodPlaystaCurService;
import com.zyyan.ms.common.util.CommUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 泄洪预警播放状态实时表 前端控制器
 * </p>
 *
 * @author xlhua
 * @since 2018-07-19
 */
@Controller
@RequestMapping("/floodPlaystaCur")
public class FloodPlaystaCurController {

    @Resource
    IFloodPlaystaCurService playstaCurService;
    
    @RequestMapping("/plays")
    @ResponseBody
    public Page<FloodPlaystaCur> findPage(HttpServletRequest request) {
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String termSn = request.getParameter("termSn");
        Page<FloodPlaystaCur> pg=null ;
        if (!CommUtils.isNull(rows) && !CommUtils.isNull(page)) {
            pg = new Page(Integer.parseInt(page),Integer.parseInt(rows));
        }
        Wrapper<FloodPlaystaCur> wrapper = new EntityWrapper<>();
        wrapper.setParamAlias(termSn);
        Page<FloodPlaystaCur>  pageCur= playstaCurService.selectPage(pg, wrapper);
        return pageCur;
    }
    
    /**
     * 实时状态查询
     * 主要参数为：设备编码和预警状态
     */
    @RequestMapping("/playss")
    @ResponseBody
    public Page<FloodPlaystaCur> findStaCurPage(HttpServletRequest request) {
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String termSn = request.getParameter("termSn");
        String playStatus = request.getParameter("playStatus");
        String search = request.getParameter("search");
        Page<FloodPlaystaCur> pg=null ;
        if (!CommUtils.isNull(rows) && !CommUtils.isNull(page)) {
            pg = new Page(Integer.parseInt(page),Integer.parseInt(rows));
        }
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("termSn", termSn);
        queryMap.put("playStatus", playStatus);
        queryMap.put("search", search);
        Page<FloodPlaystaCur>  pageCur= playstaCurService.findFoodPlaystaCurList(pg,queryMap);
        return pageCur;
    }
}

