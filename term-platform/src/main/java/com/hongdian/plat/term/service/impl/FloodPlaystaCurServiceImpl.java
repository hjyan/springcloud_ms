package com.hongdian.plat.term.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongdian.plat.term.entity.FloodPlaystaCur;
import com.hongdian.plat.term.mapper.FloodPlaystaCurMapper;
import com.hongdian.plat.term.service.IFloodPlaystaCurService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 泄洪预警播放状态实时表 服务实现类
 * </p>
 *
 * @author xlhua
 * @since 2018-07-19
 */
@Service
public class FloodPlaystaCurServiceImpl extends ServiceImpl<FloodPlaystaCurMapper, FloodPlaystaCur> implements IFloodPlaystaCurService {

    @Resource FloodPlaystaCurMapper playstaCurMapper;
    
    @Override
    public Page<FloodPlaystaCur> findFoodPlaystaCurList(Page<FloodPlaystaCur> pg, Map<String, Object> queryMap) {
        return pg.setRecords(playstaCurMapper.findFoodPlaystaCurList(pg,queryMap));
    }
}
