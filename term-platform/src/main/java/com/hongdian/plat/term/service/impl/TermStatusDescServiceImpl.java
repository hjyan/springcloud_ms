package com.hongdian.plat.term.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongdian.plat.term.entity.TermStatusDesc;
import com.hongdian.plat.term.mapper.TermStatusDescMapper;
import com.hongdian.plat.term.service.ITermStatusDescService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hongdian.plat.term.dto.TermStatusDescDto;
import com.hongdian.sys.common.util.CommUtils;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 状态详情表 服务实现类
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@Service
public class TermStatusDescServiceImpl extends ServiceImpl<TermStatusDescMapper, TermStatusDesc> implements ITermStatusDescService {

	@Override
	public Page<TermStatusDescDto> findDescPage(Page<TermStatusDescDto> page, Map<String, Object> queryMap) {
		if(CommUtils.notNull(queryMap.get("termType"))){
			return page.setRecords(baseMapper.selectTermDescListByType(page, queryMap));
		}
		return page.setRecords(baseMapper.selectTermDescList(page, queryMap));
	}

	@Override
	public List<TermStatusDescDto> findDescList(Map<String, Object> queryMap) {
		if (CommUtils.notNull(queryMap.get("termType"))) {
			return baseMapper.selectTermDescListByType(queryMap);
		}
		return baseMapper.selectTermDescList(queryMap);
	}

}
