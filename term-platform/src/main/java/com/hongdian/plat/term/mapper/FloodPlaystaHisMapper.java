package com.hongdian.plat.term.mapper;

import com.hongdian.plat.term.entity.FloodPlaystaHis;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hongdian.plat.term.entity.FloodPlaystaCur;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 泄洪预警播放状态历史表 Mapper 接口
 * </p>
 *
 * @author xlhua
 * @since 2018-07-19
 */
public interface FloodPlaystaHisMapper extends BaseMapper<FloodPlaystaHis> {

    public List<FloodPlaystaHis> findFoodPlaystaHisList(Map<String, Object> queryMap);
    
}
