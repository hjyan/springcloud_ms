package com.hongdian.plat.term.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongdian.plat.term.entity.FloodPlaystaHis;
import com.hongdian.plat.term.mapper.FloodPlaystaHisMapper;
import com.hongdian.plat.term.service.IFloodPlaystaHisService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hongdian.plat.term.entity.FloodPlaystaCur;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 泄洪预警播放状态历史表 服务实现类
 * </p>
 *
 * @author xlhua
 * @since 2018-07-19
 */
@Service
public class FloodPlaystaHisServiceImpl extends ServiceImpl<FloodPlaystaHisMapper, FloodPlaystaHis> implements IFloodPlaystaHisService {

    @Resource
    FloodPlaystaHisMapper hisMapper;

    @Override
    public List<FloodPlaystaHis> findFoodPlaystaHisList(Map<String, Object> queryMap) {
        return hisMapper.findFoodPlaystaHisList(queryMap);
    }

//    @Resource FloodPlaystaHisMapper hisMapper;
//    
//    @Override
//    public void insertFloodPlaystaHis() {
//        hisMapper.insertFloodPlaystaHis();
//    }
}
