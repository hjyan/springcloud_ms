package com.zyyan.ms.term.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.zyyan.ms.term.entity.FloodPlaystaHis;
import com.zyyan.ms.term.mapper.FloodPlaystaHisMapper;
import com.zyyan.ms.term.service.IFloodPlaystaHisService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zyyan.ms.term.entity.FloodPlaystaCur;
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
