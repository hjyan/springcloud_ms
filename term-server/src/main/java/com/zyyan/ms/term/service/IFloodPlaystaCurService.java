package com.zyyan.ms.term.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.zyyan.ms.term.entity.FloodPlaystaCur;
import com.baomidou.mybatisplus.service.IService;
import java.util.Map;

/**
 * <p>
 * 泄洪预警播放状态实时表 服务类
 * </p>
 *
 * @author xlhua
 * @since 2018-07-19
 */
public interface IFloodPlaystaCurService extends IService<FloodPlaystaCur> {

    /**
     * 查询实时预警的列表 除了预警状态为清除或者已删除的
     * @return 
     */
    public Page<FloodPlaystaCur> findFoodPlaystaCurList(Page<FloodPlaystaCur> pg, Map<String, Object> queryMap);
}
