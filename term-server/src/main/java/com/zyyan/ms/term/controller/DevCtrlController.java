/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.controller;

import com.zyyan.ms.term.exec.FepConsts;
import com.zyyan.ms.term.exec.devenum.OnLineEnum;
import com.zyyan.ms.term.service.IDevCtrlService;
import com.zyyan.ms.term.service.ITermHeaderService;
import com.zyyan.ms.common.constant.Constants;
import com.zyyan.ms.common.entity.RespRes;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @date 2018-05-30 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
@Controller
@RequestMapping("/devCtrl")
public class DevCtrlController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    IDevCtrlService devCtrlService;

    @Resource
    ITermHeaderService termHeaderService;

    @RequestMapping("/setParam")
    @ResponseBody
    public RespRes setParam(HttpServletRequest request) {
        try {
            String termSn = request.getParameter("termSn");
            String content = request.getParameter("content");
            devCtrlService.setParam(termSn, FepConsts.itssName, content);
        } catch (Exception e) {
            logger.error(null, e);
            return RespRes.fail(e.getMessage());
        }
        return RespRes.ok();
    }

    @RequestMapping("/getParam")
    @ResponseBody
    public RespRes getParam(HttpServletRequest request) {
        try {
            String termSn = request.getParameter("termSn");
            devCtrlService.getParam(termSn, FepConsts.itssName);
        } catch (Exception e) {
            logger.error(null, e);
            return RespRes.fail(e.getMessage());
        }
        return RespRes.ok();
    }

    //reset 重启
    @RequestMapping("/reset")
    @ResponseBody
    public RespRes reset(HttpServletRequest request) {
        try {
            String termSn = request.getParameter("termSn");
            devCtrlService.reset(termSn, FepConsts.itssName);
        } catch (Exception e) {
            logger.error(null, e);
            return RespRes.fail(e.getMessage());
        }
        return RespRes.ok();
    }

    //set_clock 设置时钟
    @RequestMapping("/setClock")
    @ResponseBody
    public RespRes setClock(HttpServletRequest request) {
        try {
            String termSn = request.getParameter("termSn");
            String dateTime = request.getParameter("dateTime");
            devCtrlService.setClock(termSn, FepConsts.itssName, dateTime);
        } catch (Exception e) {
            logger.error(null, e);
            return RespRes.fail(e.getMessage());
        }
        return RespRes.ok();

    }

    //get_current 召测
    @RequestMapping("/getCurrent")
    @ResponseBody
    public RespRes getCurrent(HttpServletRequest request) {
        try {
            String termSn = request.getParameter("termSn");
            String content = request.getParameter("content");
            devCtrlService.getCurrent(termSn, FepConsts.itssName);
        } catch (Exception e) {
            logger.error(null, e);
            return RespRes.fail(e.getMessage());
        }
        return RespRes.ok();
    }

    //format 格式化
    @RequestMapping("/format")
    @ResponseBody
    public RespRes format(HttpServletRequest request) {
        try {
            String termSn = request.getParameter("termSn");
            devCtrlService.format(termSn, FepConsts.itssName);
        } catch (Exception e) {
            logger.error(null, e);
            return RespRes.fail(e.getMessage());
        }
        return RespRes.ok();
    }

    //start_main_upgrade远程升级 
    @RequestMapping("/startMainUpgrade")
    @ResponseBody
    public RespRes startMainUpgrade(HttpServletRequest request) {
        try {
            String termSn = request.getParameter("termSn");
            String content = request.getParameter("content");
            devCtrlService.startMainUpgrade(termSn, FepConsts.itssName, content);
        } catch (Exception e) {
            logger.error(null, e);
            return RespRes.fail(e.getMessage());
        }
        return RespRes.ok();
    }

    // get_event 事件提取
    @RequestMapping("/getEvent")
    @ResponseBody
    public RespRes getEvent(HttpServletRequest request) {
        try {
            String termSn = request.getParameter("termSn");
            String beginTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            devCtrlService.getEvent(termSn, FepConsts.itssName, beginTime, endTime);
        } catch (Exception e) {
            logger.error(null, e);
            return RespRes.fail(e.getMessage());
        }
        return RespRes.ok();
    }

    /**
     * ***********************************业务部分协议****************************************
     */
    // "yjgb_play_warn 预警发布 并生成预警记录和被通知终端记录
    @RequestMapping("/playWarn")
    @ResponseBody
    public RespRes playWarn(HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        String termSn = request.getParameter("termSn");
        //播放内容content 是一个json串
        String content = request.getParameter("content");
        //终端个数
        String gateNum = request.getParameter("gateNum");
        //预警标题
        String title = request.getParameter("title");
        //预警等级
        String level = request.getParameter("level");
        //播放次数
        String playNum = request.getParameter("playNum");
        //预警类型
        String alarmType = request.getParameter("alarmType");
//        sysuser user =  request.getSession().getAttribute(Constants.SESSION_USER);
        String createPerson = "alarm";//后期获取user在用user  暂时写死
        //这里是一系列的终端ID，批量添加 
        String gates = request.getParameter("gates");
        //后期需要修改成content进行组装。
        try {
            //下发
            devCtrlService.playWarn(gates, FepConsts.bussName, content, gateNum, title, level, playNum, createPerson, gates, alarmType);
        } catch (Exception e) {
            logger.error(null, e);
            return RespRes.fail(e.getMessage());
        }
        return RespRes.ok();
    }

    // yjgb_play_mp3 播放mp3 并生成预警记录和被通知终端记录
    @RequestMapping("/playMp3")
    @ResponseBody
    public RespRes playMp3(HttpServletRequest request) {
        try {
            String termSn = request.getParameter("termSn");
            String content = request.getParameter("content");
            //终端个数
            String gateNum = request.getParameter("gateNum");
            //预警标题
            String title = request.getParameter("title");
            //预警等级
            String level = request.getParameter("level");
            //播放次数
            String playNum = request.getParameter("playNum");
            //文件类型
            String fileType = request.getParameter("fileType");
//        sysuser user =  request.getSession().getAttribute(Constants.SESSION_USER);
            String createPerson = "alarm";//后期获取user在用user  暂时写死
            //这里是一系列的终端ID，批量添加 
            String gates = request.getParameter("gates");
            //预警类型
            String alarmType = request.getParameter("alarmType");
            //第一位数
            String firstNum = request.getParameter("firstNum");
            //第一位数
            String secondNum = request.getParameter("secondNum");
            fileType = fileType + firstNum + secondNum;
            //下发
            devCtrlService.playMp3(gates, FepConsts.bussName, content, gateNum, title, level, playNum, createPerson, fileType, gates, alarmType);
        } catch (Exception e) {
            logger.error(null, e);
            return RespRes.fail(e.getMessage());
        }
        return RespRes.ok();
    }

    // yjgb_upgrade_mp3 更新mp3
    @RequestMapping("/upgradeMp3")
    @ResponseBody
    public RespRes upgradeMp3(HttpServletRequest request) {
        try {
            String termSn = request.getParameter("termSn");
            String content = request.getParameter("content");
            devCtrlService.upgradeMp3(termSn, FepConsts.bussName, content);
        } catch (Exception e) {
            logger.error(null, e);
            return RespRes.fail(e.getMessage());
        }
        return RespRes.ok();
    }

    // 删除发布的预警  删除flood_vox_alarm   flood_vox_rel   dev_schedule_task
    @RequestMapping("/alarm/delete")
    @ResponseBody
    public RespRes deleteAlarm(HttpServletRequest request) {
        String id = request.getParameter("id");
        String gate = request.getParameter("gate");
        devCtrlService.deleteAlarm(id, gate);
        return RespRes.ok();
    }
}
