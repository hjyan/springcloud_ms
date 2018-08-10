package com.hongdian.plat.term.mapper;

import com.hongdian.plat.term.entity.TermType;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hongdian.plat.term.dto.TermTypeStatusDto;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设备类型表 Mapper 接口
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
public interface TermTypeMapper extends BaseMapper<TermType> {

	public List<TermTypeStatusDto> selectTermTypeStatusDtoList(Map<String, Object> queryMap);
    
    public List<TermType> findTermTypesList();

}
