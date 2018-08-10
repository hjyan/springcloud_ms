/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.exec.action;

import com.hongdian.plat.term.entity.Term;
import com.hongdian.plat.term.mqttmodel.base.FepUpMqttEntity;
import com.hongdian.plat.term.service.ITermService;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @date 2018-05-30 00:00:00
 * @Description:
 * @author zyyan
 * Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
@Component
public class DevRegisterAction extends BaseAction {

    @Resource
    private ITermService termService;
    
    /**
     * 正常情况下 ，设备会在第一次连接平台的时候发注册包 后面就不发 注册包，发 登录包 设备注册的逻辑是，term 表里面添加/更新一条记录
     * @param fep
     */
    @Override
    public void excute(FepUpMqttEntity fep) {
        String sn = fep.getSn();
        Term term =termService.selectById(sn);
        String version = fep.getVersion();
        term.setTermVersion(version);
        termService.updateById(term);
    }
}
