package com.zyyan.ms.term.service.impl;

import com.zyyan.ms.term.entity.FloodVoxRel;
import com.zyyan.ms.term.mapper.FloodVoxRelMapper;
import com.zyyan.ms.term.service.IFloodVoxRelService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 泄洪预警手动语音报警设备信息表 服务实现类
 * </p>
 *
 * @author xlhua
 * @since 2018-07-19
 */
@Service
public class FloodVoxRelServiceImpl extends ServiceImpl<FloodVoxRelMapper, FloodVoxRel> implements IFloodVoxRelService {

    @Resource FloodVoxRelMapper voxRelMapper;
    
    @Override
    public void insertFloodVoxRel(FloodVoxRel floodVoxRel) {
        voxRelMapper.insertFloodVoxRel(floodVoxRel);
    }

    @Override
    public void deletefloodVoxRelByIdAndGate(HashMap<String, Object> queryMap) {
        voxRelMapper.deleteFloodVoxRelByIdAndGate(queryMap);
    }

}
