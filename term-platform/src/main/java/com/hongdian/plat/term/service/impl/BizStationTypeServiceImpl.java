package com.hongdian.plat.term.service.impl;

import com.hongdian.plat.term.entity.BizStationType;
import com.hongdian.plat.term.mapper.BizStationTypeMapper;
import com.hongdian.plat.term.service.IBizStationTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测站类型 服务实现类
 * </p>
 *
 * @author xlhua
 * @since 2018-08-01
 */
@Service
public class BizStationTypeServiceImpl extends ServiceImpl<BizStationTypeMapper, BizStationType> implements IBizStationTypeService {

    @Resource
    BizStationTypeMapper stationTypeMapper;

    @Override
    public List<BizStationType> selectStationTypesList(){
        return stationTypeMapper.getstationTypesList();
    }
}