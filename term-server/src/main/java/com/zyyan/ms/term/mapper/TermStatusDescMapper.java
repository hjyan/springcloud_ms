package com.zyyan.ms.term.mapper;

import com.zyyan.ms.term.entity.TermStatusDesc;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.zyyan.ms.term.dto.TermStatusDescDto;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 状态详情表 Mapper 接口
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
public interface TermStatusDescMapper extends BaseMapper<TermStatusDesc> {

	public List<TermStatusDescDto> selectTermDescList(Pagination page, Map<String, Object> queryMap);

	public List<TermStatusDescDto> selectTermDescList(Map<String, Object> queryMap);

	public List<TermStatusDescDto> selectTermDescListByType(Pagination page, Map<String, Object> queryMap);

	public List<TermStatusDescDto> selectTermDescListByType(Map<String, Object> queryMap);

}
