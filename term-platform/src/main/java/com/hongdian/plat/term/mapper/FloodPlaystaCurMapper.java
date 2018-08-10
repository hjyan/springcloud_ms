package com.hongdian.plat.term.mapper;

import com.hongdian.plat.term.entity.FloodPlaystaCur;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 泄洪预警播放状态实时表 Mapper 接口
 * </p>
 *
 * @author xlhua
 * @since 2018-07-19
 */
public interface FloodPlaystaCurMapper extends BaseMapper<FloodPlaystaCur> {

    public List<FloodPlaystaCur> findFoodPlaystaCurList(Pagination page, Map<String, Object> queryMap);
    
}
