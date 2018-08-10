package com.zyyan.ms.term.controller;

import com.zyyan.ms.term.entity.BizStationType;
import com.zyyan.ms.term.service.IBizStationTypeService;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * <p>
 * 测站类型 前端控制器
 * </p>
 *
 * @author xlhua
 * @since 2018-08-01
 */
@Controller
@RequestMapping("/bizStationType")
public class BizStationTypeController {

    @Resource IBizStationTypeService stationTypeService;
    
    /**
     * 下拉term列表
     */
    @RequestMapping("/stationTypes")
    @ResponseBody
    public List<BizStationType> findTerms(HttpServletRequest request) {
        List<BizStationType> list=null ;
        list= stationTypeService.selectStationTypesList();
        return list;
    }
}

