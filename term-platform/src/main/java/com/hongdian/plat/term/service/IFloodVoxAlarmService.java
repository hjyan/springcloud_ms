package com.hongdian.plat.term.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongdian.plat.term.entity.FloodVoxAlarm;
import com.baomidou.mybatisplus.service.IService;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 泄洪预警手动语音报警表 服务类
 * </p>
 *
 * @author xlhua
 * @since 2018-07-19
 */
public interface IFloodVoxAlarmService extends IService<FloodVoxAlarm> {

    /**
     * 发布预警添加预警记录
     */
//    public void insertAlarm(FloodVoxAlarm floodVoxAlarm);
    
    /**
     * 根据taskId更新预警的状态
     */
    public void updatePubStatusByTaskId(Integer taskId);
    
    /**
     * 查询实时预警的列表 除了预警状态为清除或者已删除的
     * @return 
     */
    public Page<FloodVoxAlarm> findFoodPlaystaCurList(Page<FloodVoxAlarm> pg, Map<String, Object> queryMap);
    
    /**
     * 查询实时预警的列表对应的gates设备
     */
    public List<FloodVoxAlarm> findFoodPlaystaCurGatesList(Map<String, Object> queryMap);
}
