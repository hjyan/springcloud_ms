package com.zyyan.ms.term.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.zyyan.ms.term.common.BaseController;
import com.zyyan.ms.term.dto.StationDto;
import com.zyyan.ms.term.entity.BizStatBasic;
import com.zyyan.ms.term.service.IBizStatBasicService;
import com.zyyan.ms.term.service.ITermHeaderService;
import com.zyyan.ms.term.service.ITermService;
import com.zyyan.ms.common.entity.RespRes;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 测站基本信息 前端控制器
 * </p>
 *
 * @author cli
 * @since 2018-05-16
 */
@Controller
@RequestMapping("/bizStationInfo")
public class StationController extends BaseController {

    @Resource
    private IBizStatBasicService stationService;

    @Resource
    private ITermService termService;

    @Resource
    private ITermHeaderService termHeaderService;

    @RequestMapping("/findPage")
    @ResponseBody
    public Page<StationDto> findPage(HttpServletRequest request, Integer page, Integer rows) {
        String onlineState = request.getParameter("onlineState");//在线状态
        String termSn = request.getParameter("termSn");//设备编码
        String stcd = request.getParameter("stcd");//国家测站号
        String stnm = request.getParameter("stnm");//站点名称

        Page<StationDto> pg = new Page(page, rows);
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("onlineState", onlineState);
        queryMap.put("termSn", termSn);
        queryMap.put("stcd", stcd);
        queryMap.put("stnm", stnm);
        Page<StationDto> pageObj = stationService.findStationPage(pg, queryMap);
        return pageObj;
    }

    /**
     * 添加站点
     * @param request
     * @return 
     */
    @RequestMapping("/add")
    @ResponseBody
    public RespRes addStation(HttpServletRequest request) {
        String sn = request.getParameter("sn");//设备站址
        //国家测站号 stcd
        String stcd = request.getParameter("stcd");
        //测站名称  stnm
        String stnm = request.getParameter("stnm");
        //测站类型  sttp
        String sttp = request.getParameter("sttp");
        //设备类型  TERM_TYPE_ID  term
        String typeId = request.getParameter("typeId");
        //所属行政区划  adcd
        String adcd = request.getParameter("adcd");
        //设备型号  TERM_MODEL  term
        String termModel = request.getParameter("termModel");
        //负责人   DIRECTOR
        String director = request.getParameter("director");
        //联系电话  TEL
        String tel = request.getParameter("tel");
        //SIM卡号
        //建成日期  BUILD_DATE
        String buildDate = request.getParameter("buildDate");
        //管理单位  MNG_CD
        String mngCd = request.getParameter("mngCd");
        //安装位置  STLC
        String stlc = request.getParameter("stlc");
        //经纬度  经LGTD  纬LTTD
        String lgtd = request.getParameter("lgtd");
        String lttd = request.getParameter("lttd");
        //添加测站基本信息并返回Id
        int id = stationService.insertStation(stcd,stnm,sttp,adcd,director,tel,buildDate,stlc,mngCd,lgtd,lttd,sn);
        //添加设备相关信息
        termService.insertTerm(id,sn,termModel,typeId);
        //添加header表相关信息
        termHeaderService.insertTermHeader(sn);
        return RespRes.ok();
    }

    /**
     * 删除站点 
     */
    @RequestMapping("/delete")
    @ResponseBody
    public RespRes deleteStation(HttpServletRequest request){
        String id = request.getParameter("id");
        String sn = request.getParameter("sn");
        //删除station
        stationService.deleteById(Integer.parseInt(id));
        //删除trem
        termService.deleteById(sn);
        //删除term_header
        termHeaderService.deleteById(sn);
        return RespRes.ok();
    }
    
    /**
     * 修改站点 
     */
    @RequestMapping("/update")
    @ResponseBody
    public RespRes updateStation(HttpServletRequest request){
        String id = request.getParameter("id");
        String sn = request.getParameter("sn");//设备站址
        //国家测站号 stcd
        String stcd = request.getParameter("stcd");
        //测站名称  stnm
        String stnm = request.getParameter("stnm");
        //测站类型  sttp
        String sttp = request.getParameter("sttp");
        //设备类型  TERM_TYPE_ID  term
        String typeId = request.getParameter("typeId");
        //所属行政区划  adcd
        String adcd = request.getParameter("adcd");
        //设备型号  TERM_MODEL  term
        String termModel = request.getParameter("termModel");
        //负责人   DIRECTOR
        String director = request.getParameter("director");
        //联系电话  TEL
        String tel = request.getParameter("tel");
        //SIM卡号
        //建成日期  BUILD_DATE
        String buildDate = request.getParameter("buildDate");
        //管理单位  MNG_CD
        String mngCd = request.getParameter("mngCd");
        //安装位置  STLC
        String stlc = request.getParameter("stlc");
        //经纬度  经LGTD  纬LTTD
        String lgtd = request.getParameter("lgtd");
        String lttd = request.getParameter("lttd");
        //根据Id修改测站信息
        stationService.updateStation(id,stcd,stnm,sttp,adcd,director,tel,buildDate,stlc,mngCd,lgtd,lttd,sn);
        //添加设备相关信息
        termService.updateTerm(id,sn,termModel,typeId);
        //添加header表相关信息
        termHeaderService.updateTermHeader(sn);
        return RespRes.ok();
    }
}
