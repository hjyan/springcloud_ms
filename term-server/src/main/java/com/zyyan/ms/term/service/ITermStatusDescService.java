package com.zyyan.ms.term.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.zyyan.ms.term.entity.TermStatusDesc;
import com.baomidou.mybatisplus.service.IService;
import com.zyyan.ms.term.dto.TermStatusDescDto;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 状态详情表 服务类
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
public interface ITermStatusDescService extends IService<TermStatusDesc> {

	public Page<TermStatusDescDto> findDescPage(Page<TermStatusDescDto> page, Map<String, Object> queryMap);

	public List<TermStatusDescDto> findDescList(Map<String, Object> queryMap);

}
