package com.zyyan.ms.term.service;

import com.zyyan.ms.term.entity.TermParamValue;
import com.baomidou.mybatisplus.service.IService;
import com.zyyan.ms.term.dto.TermParamDto;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cli
 * @since 2018-05-28
 */
public interface ITermParamValueService extends IService<TermParamValue> {

    public void replaceBatch(List<TermParamValue> list);
	
	public List<TermParamDto> selectTermParamDtoList(Map<String, Object> queryMap);
}
