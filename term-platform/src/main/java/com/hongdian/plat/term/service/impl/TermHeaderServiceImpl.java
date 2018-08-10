package com.hongdian.plat.term.service.impl;

import com.hongdian.plat.term.entity.TermHeader;
import com.hongdian.plat.term.mapper.TermHeaderMapper;
import com.hongdian.plat.term.service.ITermHeaderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hongdian.sys.common.util.CommUtils;
import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 设备消息头部表 服务实现类
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@Service
public class TermHeaderServiceImpl extends ServiceImpl<TermHeaderMapper, TermHeader> implements ITermHeaderService {

	@Resource
	TermHeaderMapper baseMapper;

	@Override
	public void updateAllTermOffline(Map<String, Object> map) {
		baseMapper.updateAllTermOffline(map);
	}

	@Override
	public void updateFepSourceTermOffline(Map<String, Object> map) {
		baseMapper.updateFepSourceTermOffline(map);
	}

	@Override
	public void insertTermHeader(String sn) {
		TermHeader header = new TermHeader();
		header.setTermSn(sn);
		header.setLastLoginTime(new Date());
		header.setLastLogoutTime(new Date());
		this.insert(header);
	}

    @Override
    public void updateTermHeader(String sn) {
        
    }

}
