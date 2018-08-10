package com.zyyan.ms.term.mapper;

import com.zyyan.ms.term.entity.Term;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zyyan.ms.term.dto.TermDto;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设备基本信息表 Mapper 接口
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
public interface TermMapper extends BaseMapper<Term> {

	public TermDto selectTermDtoByStationId(Map<String, Object> map);
    
    public List<Term> getTermsList();
}
