package com.hongdian.plat.term.mapper;

import com.hongdian.plat.term.entity.TermStatus;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.hongdian.plat.term.dto.TermStatusDto;
import com.hongdian.plat.term.dto.TermStatusExtend;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设备状态表 Mapper 接口
 * </p>
 *
 * @author cli
 * @since 2018-05-28
 */
public interface TermStatusMapper extends BaseMapper<TermStatus> {

	public List<TermStatusDto> selectTermStatusDtoList(Pagination page, Map<String, Object> queryMap);
	
	public List<TermStatusExtend> selectTermStatusList(Map<String, Object> queryMap);
}
