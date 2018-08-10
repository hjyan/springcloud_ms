package com.zyyan.ms.term.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zyyan.ms.term.entity.FloodPlaystaCur;
import com.zyyan.ms.term.entity.FloodVoxAlarm;
import com.zyyan.ms.term.service.IFloodVoxAlarmService;
import com.zyyan.ms.common.util.CommUtils;
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
 * 泄洪预警手动语音报警表 前端控制器
 * </p>
 *
 * @author xlhua
 * @since 2018-07-19
 */
@Controller
@RequestMapping("/term/floodVoxAlarm")
public class FloodVoxAlarmController {

    @Resource
    IFloodVoxAlarmService voxAlarmService;

    /**
     * 实时状态查询 主要参数为：设备编码和预警状态
     */
    @RequestMapping("/current")
    @ResponseBody
    public Page<FloodVoxAlarm> findStaCurPage(HttpServletRequest request) {
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String termSn = request.getParameter("termSn");
        String pubStatus = request.getParameter("playStatus");//发布的状态
        String search = request.getParameter("search");
        Page<FloodVoxAlarm> pg = null;
        if (!CommUtils.isNull(rows) && !CommUtils.isNull(page)) {
            pg = new Page(Integer.parseInt(page), Integer.parseInt(rows));
        }
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("termSn", termSn);
        queryMap.put("pubStatus", pubStatus);
        queryMap.put("search", search);
        Page<FloodVoxAlarm> pageCur = voxAlarmService.findFoodPlaystaCurList(pg, queryMap);
        return pageCur;
    }
    
    /**
     * 实时状态查询 主要参数为：设备编码和预警状态  这里不需要分页
     */
    @RequestMapping("/current/gates")
    @ResponseBody
    public List<FloodVoxAlarm> findStaCurGatesPage(HttpServletRequest request) {
        String id = request.getParameter("id");
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("id", id);
        List<FloodVoxAlarm> curList = voxAlarmService.findFoodPlaystaCurGatesList(queryMap);
        return curList;
    }
}
