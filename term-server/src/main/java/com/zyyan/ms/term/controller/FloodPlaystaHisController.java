package com.zyyan.ms.term.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.zyyan.ms.term.entity.FloodPlaystaCur;
import com.zyyan.ms.term.entity.FloodPlaystaHis;
import com.zyyan.ms.term.service.IFloodPlaystaHisService;
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
 * 泄洪预警播放状态历史表 前端控制器
 * </p>
 *
 * @author xlhua
 * @since 2018-07-19
 */
@Controller
@RequestMapping("/floodPlaystaHis")
public class FloodPlaystaHisController {
    @Resource
    IFloodPlaystaHisService hisService;
    /**
     * 状态的历史变化
     */
    @RequestMapping("/history")
    @ResponseBody
    public List<FloodPlaystaHis> findStaHistory(HttpServletRequest request) {
        String termSn = request.getParameter("termSn");
        String id = request.getParameter("id");
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("termSn", termSn);
        queryMap.put("id", id);
        List<FloodPlaystaHis>  hisList= hisService.findFoodPlaystaHisList(queryMap);
        return hisList;
    }
}

