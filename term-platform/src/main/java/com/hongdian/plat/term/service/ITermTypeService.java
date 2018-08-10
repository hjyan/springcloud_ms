package com.hongdian.plat.term.service;

import com.hongdian.plat.term.entity.TermType;
import com.baomidou.mybatisplus.service.IService;
import com.hongdian.plat.term.dto.TermTypeStatusDto;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设备类型表 服务类
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
public interface ITermTypeService extends IService<TermType> {

	public List<TermTypeStatusDto> findTermTypeStatusDtoList(Map<String, Object> queryMap);

    public List<TermType> findTermTypesList();
}
