package com.hongdian.plat.term.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongdian.plat.term.entity.FloodPlaystaHis;
import com.baomidou.mybatisplus.service.IService;
import com.hongdian.plat.term.entity.FloodPlaystaCur;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 泄洪预警播放状态历史表 服务类
 * </p>
 *
 * @author xlhua
 * @since 2018-07-19
 */
public interface IFloodPlaystaHisService extends IService<FloodPlaystaHis> {

    public List<FloodPlaystaHis> findFoodPlaystaHisList(Map<String, Object> queryMap);

    /**
     * 添加预警播放状态历史表
     */
//    public void insertFloodPlaystaHis();
}
