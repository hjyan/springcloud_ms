package com.zyyan.ms.term.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.zyyan.ms.term.entity.TermStatus;
import com.zyyan.ms.term.mapper.TermStatusMapper;
import com.zyyan.ms.term.service.ITermStatusService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zyyan.ms.term.dto.TermStatusDto;
import com.zyyan.ms.term.dto.TermStatusExtend;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 设备状态表 服务实现类
 * </p>
 *
 * @author cli
 * @since 2018-05-28
 */
@Service
public class TermStatusServiceImpl extends ServiceImpl<TermStatusMapper, TermStatus> implements ITermStatusService {

	@Override
	public Page<TermStatusDto> findPage(Page<TermStatusDto> page, Map<String, Object> queryMap) {
		List<TermStatusDto> list = baseMapper.selectTermStatusDtoList(page, queryMap);
		parseStatusList(list);
		return page.setRecords(list);
	}

	private void parseStatusList(List<TermStatusDto> list) {
		for (TermStatusDto dto : list) {
			List idList = Arrays.asList(dto.getStatusIds().split(","));
			dto.setStatusIdsList(idList);
			List nameList = Arrays.asList(dto.getStatusNames().split(","));
			dto.setStatusNameList(nameList);
			List descList = Arrays.asList(dto.getStatusDescs().split(","));
			dto.setStatusDescList(descList);
			List valueList = Arrays.asList(dto.getStatusValues().split(","));
			dto.setStatusValueList(valueList);
		}
	}

	@Override
	public List<TermStatusExtend> findTermStatusLsit(Map<String, Object> queryMap) {
		return baseMapper.selectTermStatusList(queryMap);
	}

}
