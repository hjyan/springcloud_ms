package com.zyyan.ms.term.service;

import com.zyyan.ms.term.entity.TermHeader;
import com.baomidou.mybatisplus.service.IService;
import java.util.Map;

/**
 * <p>
 * 设备消息头部表 服务类
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
public interface ITermHeaderService extends IService<TermHeader> {

	public void updateAllTermOffline(Map<String, Object> map);
	
	public void updateFepSourceTermOffline(Map<String, Object> map);
    
    public void insertTermHeader(String sn);
    
    public void updateTermHeader(String sn);
}
