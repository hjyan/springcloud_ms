package com.zyyan.ms.term.mapper;

import com.zyyan.ms.term.entity.TermModel;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

/**
 * <p>
 * 设备型号表 Mapper 接口
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
public interface TermModelMapper extends BaseMapper<TermModel> {

    public List<TermModel> findTermModelsList();
}
