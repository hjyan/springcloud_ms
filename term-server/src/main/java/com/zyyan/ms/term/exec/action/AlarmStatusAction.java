/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.exec.action;

import com.zyyan.ms.term.entity.FloodPlaystaCur;
import com.zyyan.ms.term.entity.FloodPlaystaHis;
import com.zyyan.ms.term.mqttmodel.base.FepUpMqttEntity;
import com.zyyan.ms.term.mqttmodel.up.AlarmStatusMqttEntity;
import com.zyyan.ms.term.service.IFloodPlaystaCurService;
import com.zyyan.ms.term.service.IFloodPlaystaHisService;
import com.zyyan.ms.term.util.JSONUtils;
import com.zyyan.ms.common.util.CommUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @date 2018-05-30 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
@Component
public class AlarmStatusAction extends BaseAction {

    @Resource
    IFloodPlaystaCurService staCurService;
    
    @Resource
    IFloodPlaystaHisService staHisService; 
    
	@Override
	public void excute(FepUpMqttEntity fep) {
		String content = fep.getContent();
		AlarmStatusMqttEntity alarmStatus = (AlarmStatusMqttEntity) JSONUtils.readValue(content, AlarmStatusMqttEntity.class);
		//入库
        //添加实时状态表记录 注：当设备编码（termSn）存在的时候更新，否则添加。
        FloodPlaystaCur staCur = staCurService.selectById(fep.getSn());
        FloodPlaystaCur staCur2 = new FloodPlaystaCur();
        staCur2.setTermSn(fep.getSn());
        staCur2.setAlarmInfoId(alarmStatus.getPlayRandom().toString());
        staCur2.setDataTm(alarmStatus.getTime());
        if (!CommUtils.isNull(alarmStatus.getPlayStatus())) {
            staCur2.setPlayStatus(Integer.parseInt(alarmStatus.getPlayStatus()));
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
        staHis.setAlarmInfoId(alarmStatus.getPlayRandom().toString());
        staHis.setDataTm(alarmStatus.getTime());
        if (!CommUtils.isNull(alarmStatus.getPlayStatus())) {
            staCur2.setPlayStatus(Integer.parseInt(alarmStatus.getPlayStatus()));
        }
        staHis.setContent(content);
        staHisService.insert(staHis);
	}

}
