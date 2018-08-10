package com.hongdian.plat.term.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongdian.plat.term.entity.TermStatus;
import com.baomidou.mybatisplus.service.IService;
import com.hongdian.plat.term.dto.TermStatusDto;
import com.hongdian.plat.term.dto.TermStatusExtend;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设备状态表 服务类
 * </p>
 *
 * @author cli
 * @since 2018-05-28
 */
public interface ITermStatusService extends IService<TermStatus> {

	Page<TermStatusDto> findPage(Page<TermStatusDto> page, Map<String, Object> queryMap);

	List<TermStatusExtend> findTermStatusLsit(Map<String, Object> queryMap);
}
