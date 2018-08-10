package com.zyyan.ms.term.service.impl;

import com.zyyan.ms.term.entity.TermTypeStatus;
import com.zyyan.ms.term.mapper.TermTypeStatusMapper;
import com.zyyan.ms.term.service.ITermTypeStatusService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 设备状态组关系表 服务实现类
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@Service
public class TermTypeStatusServiceImpl extends ServiceImpl<TermTypeStatusMapper, TermTypeStatus> implements ITermTypeStatusService {

	public void updateBatch(List<TermTypeStatus> termTypeStatusList) {
		Map<Integer, Integer> tempMap = new HashMap<Integer, Integer>();
		for (TermTypeStatus tts : termTypeStatusList) {
			tempMap.put(tts.getTermTypeId(), tts.getStatusId());
		}

		List<Integer> tempTypeList = new ArrayList<Integer>();
		tempTypeList.addAll(tempMap.keySet());

		//删除原有的关联
		for (Integer termTypeId : tempTypeList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("TERM_TYPE_ID", termTypeId);
			baseMapper.deleteByMap(map);
		}
		this.insertBatch(termTypeStatusList);
	}
}
