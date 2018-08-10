/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.exec.action;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zyyan.ms.term.constant.SystemConstants;
import com.zyyan.ms.term.exec.devenum.OnLineEnum;
import com.zyyan.ms.term.mqttmodel.up.LogoutMqttEntity;
import com.zyyan.ms.term.mqttmodel.base.FepUpMqttEntity;
import com.zyyan.ms.term.entity.TermHeader;
import com.zyyan.ms.term.service.ITermHeaderService;
import com.zyyan.ms.term.service.ITermService;
import com.zyyan.ms.term.util.JSONUtils;
import com.zyyan.ms.common.util.CommUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 设备登出执行，设备登出 online_state状态置为离线
 *
 * @date 2018-05-21 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
@Component
public class DevLogoutAction extends BaseAction {

    @Resource
    ITermService termService;
    @Resource
    ITermHeaderService termHeaderService;

    @Override
    public void excute(FepUpMqttEntity fep) {
        String content = fep.getContent();
        String termSn = fep.getSn();
        LogoutMqttEntity entity = JSONUtils.readValue(content, LogoutMqttEntity.class);

        //termHeader
        TermHeader termHeader = SystemConstants.TERM_HEADER_CACHE.get(termSn);
        if (CommUtils.isNull(termHeader)) {
            termHeader = new TermHeader();
            termHeader.setTermSn(termSn);
        }
        termHeader.setOnlineState(OnLineEnum.OFF_LINE.getStatus());
        termHeader.setLastLogoutTime(entity.getTime());
        termHeader.setSourceBiz(null);
        termHeader.setSourceOps(null);

        //更新缓存和库
        SystemConstants.TERM_HEADER_CACHE.put(termSn, termHeader);
//        Wrapper<TermHeader> wra = new EntityWrapper<>();
//        wra.eq("TERM_SN", termSn);
        termHeaderService.insertOrUpdateAllColumn(termHeader);
    }
}
