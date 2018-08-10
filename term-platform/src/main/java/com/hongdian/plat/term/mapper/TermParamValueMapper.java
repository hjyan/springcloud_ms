package com.hongdian.plat.term.mapper;

import com.hongdian.plat.term.entity.TermParamValue;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hongdian.plat.term.dto.TermParamDto;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cli
 * @since 2018-05-28
 */
public interface TermParamValueMapper extends BaseMapper<TermParamValue> {
	
	List<TermParamDto> selectTermParamDtoList(Map<String, Object> queryMap);

    public void replaceBatch(List<TermParamValue> list);
}
