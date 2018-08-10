package com.hongdian.plat.term.mapper;

import com.hongdian.plat.term.entity.BizStatBasic;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.hongdian.plat.term.dto.StationDto;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cli
 * @since 2018-06-02
 */
public interface BizStatBasicMapper extends BaseMapper<BizStatBasic> {

	public List<StationDto> findStationDtoList(Map<String, Object> map);

	public List<StationDto> findStationDtoList(Pagination page, Map<String, Object> map);

}
