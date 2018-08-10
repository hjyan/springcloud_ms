package com.zyyan.ms.term.mapper;

import com.zyyan.ms.term.entity.FloodVoxRel;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 泄洪预警手动语音报警设备信息表 Mapper 接口
 * </p>
 *
 * @author xlhua
 * @since 2018-07-19
 */
public interface FloodVoxRelMapper extends BaseMapper<FloodVoxRel> {

    public void insertFloodVoxRel(FloodVoxRel floodVoxRel);
    
    public void deleteFloodVoxRelByIdAndGate(HashMap<String, Object> queryMap);
    
}
