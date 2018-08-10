/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.config;

import com.zyyan.ms.term.constant.SystemConstants;
import com.zyyan.ms.term.entity.TermHeader;
import com.zyyan.ms.term.exec.FepConsts;
import com.zyyan.ms.term.service.ITaskService;
import com.zyyan.ms.term.service.ITermHeaderService;
import com.zyyan.ms.term.service.ITermStatusDescService;
import com.zyyan.ms.term.exec.thread.ProcessTopicThread;
import com.zyyan.ms.term.service.IBizStatBasicService;
import com.zyyan.ms.term.service.ITermParamValueService;
import com.zyyan.ms.term.task.ScheduleTaskScanThread;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @date 2018-05-24 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
@Component
public class SystemInit implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(SystemInit.class);
    @Resource
    ITermStatusDescService descService;
    @Resource
    ITermHeaderService termHeaderService;
    @Resource
    ITaskService taskService;
    @Resource
    IBizStatBasicService stationService;
    @Resource
    ITermParamValueService termValueService;
    @Resource
    TaskExecutor taskExecutor;
    @Value("${sys.fep-name-itss}")
    private String fepItssName;
    @Value("${sys.fep-name-yjgb}")
    private String fepBussName;
    @Value("${sys.platName}")
    private String platName;

    @Override
    public void run(String... strings) throws Exception {
        initTermDescCache();//初始化 term desc cache
        initTermHeaderCache();//初始化设备在线状态缓存
        initClientName();
        taskService.initScheduleTaskIntoCache();//初始化预置任务缓存
        taskExecutor.execute(new ProcessTopicThread());//启动消息处理线程
        taskExecutor.execute(new ScheduleTaskScanThread());//启动预置任务扫描线程
        log.info("systerm init cache end ......");
    }

    private void initTermDescCache() {
        SystemConstants.TERM_STATUS_DESC_LIST.addAll(descService.selectByMap(null));
    }

    private void initTermHeaderCache() {
        List<TermHeader> termList = termHeaderService.selectList(null);
        SystemConstants.TERM_HEADER_CACHE.clear();
        for (TermHeader header : termList) {
            SystemConstants.TERM_HEADER_CACHE.put(header.getTermSn(), header);
        }
    }

    private void initClientName() {
        FepConsts.itssName = fepItssName;
        FepConsts.bussName = fepBussName;
        FepConsts.platName = platName;
    }

}
