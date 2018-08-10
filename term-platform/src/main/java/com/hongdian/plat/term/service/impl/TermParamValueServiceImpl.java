package com.hongdian.plat.term.service.impl;

import com.hongdian.plat.term.entity.TermParamValue;
import com.hongdian.plat.term.mapper.TermParamValueMapper;
import com.hongdian.plat.term.service.ITermParamValueService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hongdian.plat.term.dto.TermParamDto;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cli
 * @since 2018-05-28
 */
@Service
public class TermParamValueServiceImpl extends ServiceImpl<TermParamValueMapper, TermParamValue> implements ITermParamValueService {

	public void replaceBatch(List<TermParamValue> list) {
		baseMapper.replaceBatch(list);
	}

	@Override
	public List<TermParamDto> selectTermParamDtoList(Map<String, Object> queryMap) {
		return baseMapper.selectTermParamDtoList(queryMap);
	}

}
