package com.hongdian.plat.term.mapper;

import com.hongdian.plat.term.entity.TermHeader;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.Map;

/**
 * <p>
 * 设备消息头部表 Mapper 接口
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
public interface TermHeaderMapper extends BaseMapper<TermHeader> {

	public void updateAllTermOffline(Map<String, Object> map);

	public void updateFepSourceTermOffline(Map<String, Object> map);
}
