/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hongdian.plat.term.exec.action;

import com.hongdian.plat.term.entity.FloodPlaystaCur;
import com.hongdian.plat.term.entity.FloodPlaystaHis;
import com.hongdian.plat.term.mqttmodel.up.WarnStatusMqttEntity;
import com.hongdian.plat.term.mqttmodel.base.FepUpMqttEntity;
import com.hongdian.plat.term.service.IFloodPlaystaCurService;
import com.hongdian.plat.term.service.IFloodPlaystaHisService;
import com.hongdian.plat.term.util.JSONUtils;
import com.hongdian.sys.common.util.CommUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @date 2018-05-30 00:00:00
 * @Description:
 * @author zyyan
 * Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
@Component
public class WarnStatusAction extends BaseAction{

    @Resource
    IFloodPlaystaCurService staCurService;
    
    @Resource
    IFloodPlaystaHisService staHisService; 
    
    @Override
    public void excute(FepUpMqttEntity fep) {
        String content = fep.getContent();
        WarnStatusMqttEntity entity = (WarnStatusMqttEntity) JSONUtils.readValue(content, WarnStatusMqttEntity.class);
        //添加实时状态表记录 注：当设备编码（termSn）存在的时候更新，否则添加。@Resource
        FloodPlaystaCur staCur = staCurService.selectById(fep.getSn());
        FloodPlaystaCur staCur2 = new FloodPlaystaCur();
        staCur2.setTermSn(fep.getSn());
        staCur2.setAlarmInfoId(entity.getPlayRandom().toString());
        staCur2.setDataTm(entity.getTime());
        if (!CommUtils.isNull(entity.getPlayStatus())) {
            staCur2.setPlayStatus(Integer.parseInt(entity.getPlayStatus()));
        }
        staCur2.setContent(content);
        if(staCur!=null){
            staCurService.updateById(staCur2);
        }else{
            staCurService.insert(staCur2);
        }
        //添加历史状态表记录
        FloodPlaystaHis staHis = new FloodPlaystaHis();
        staHis.setTermSn(fep.getSn());
        staHis.setAlarmInfoId(entity.getPlayRandom().toString());
        staHis.setDataTm(entity.getTime());
        if (!CommUtils.isNull(entity.getPlayStatus())) {
            staCur2.setPlayStatus(Integer.parseInt(entity.getPlayStatus()));
        }
        staHis.setContent(content);
        staHisService.insert(staHis);
    }

}
