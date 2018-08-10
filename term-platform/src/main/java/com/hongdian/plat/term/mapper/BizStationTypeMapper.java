package com.hongdian.plat.term.mapper;

import com.hongdian.plat.term.entity.BizStationType;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

/**
 * <p>
 * 测站类型 Mapper 接口
 * </p>
 *
 * @author xlhua
 * @since 2018-08-01
 */
public interface BizStationTypeMapper extends BaseMapper<BizStationType> {

    /**
     * 下拉列表
     */
    public List<BizStationType> getstationTypesList();
}
