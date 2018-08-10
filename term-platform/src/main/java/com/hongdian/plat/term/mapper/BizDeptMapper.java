package com.hongdian.plat.term.mapper;

import com.hongdian.plat.term.entity.BizDept;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

/**
 * <p>
 * 行政区划信息 Mapper 接口
 * </p>
 *
 * @author xlhua
 * @since 2018-08-01
 */
public interface BizDeptMapper extends BaseMapper<BizDept> {

    public List<BizDept> getBizDeptList();
    
}
