package com.zyyan.ms.term.service.impl;

import com.zyyan.ms.term.entity.TermType;
import com.zyyan.ms.term.mapper.TermTypeMapper;
import com.zyyan.ms.term.service.ITermTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zyyan.ms.term.dto.TermTypeStatusDto;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 设备类型表 服务实现类
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@Service
public class TermTypeServiceImpl extends ServiceImpl<TermTypeMapper, TermType> implements ITermTypeService {

    @Resource
    TermTypeMapper termTypeMapper;
    
	@Override
	public List<TermTypeStatusDto> findTermTypeStatusDtoList(Map<String, Object> queryMap) {
		return baseMapper.selectTermTypeStatusDtoList(queryMap);
	}

    @Override
    public List<TermType> findTermTypesList() {
        return termTypeMapper.findTermTypesList();
    }

}
