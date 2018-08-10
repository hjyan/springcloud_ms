package com.zyyan.ms.term.mapper;

import com.zyyan.ms.term.entity.FloodVoxAlarm;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 泄洪预警手动语音报警表 Mapper 接口
 * </p>
 *
 * @author xlhua
 * @since 2018-07-19
 */
public interface FloodVoxAlarmMapper extends BaseMapper<FloodVoxAlarm> {

    /**
     * 发布预警添加预警记录
     * @param floodVoxAlarm
     */
//    public int insertAlarm(FloodVoxAlarm floodVoxAlarm);
    
    /**
     * 根据taskId更新预警的状态
     * @param taskId
     */
    public void updatePubStatusByTaskId(FloodVoxAlarm voxAlarm);
    
    /**
     * 实时预警的列表
     * @param page
     * @param queryMap
     * @return 
     */
    public List<FloodVoxAlarm> findFoodPlaystaCurList(Pagination page, Map<String, Object> queryMap);
    
    /**
     * 查询实时预警的列表对应的gates设备
     */
    public List<FloodVoxAlarm> findFoodPlaystaCurGatesList(Map<String, Object> queryMap);
}
