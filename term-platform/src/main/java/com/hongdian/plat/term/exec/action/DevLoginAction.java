/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.exec.action;

import com.hongdian.plat.term.constant.SystemConstants;
import com.hongdian.plat.term.entity.BizStatBasic;
import com.hongdian.plat.term.exec.devenum.OnLineEnum;
import com.hongdian.plat.term.mqttmodel.up.DevLoginEntity;
import com.hongdian.plat.term.mqttmodel.base.FepUpMqttEntity;
import com.hongdian.plat.term.entity.Term;
import com.hongdian.plat.term.entity.TermHeader;
import com.hongdian.plat.term.service.IBizStatBasicService;
import com.hongdian.plat.term.service.ITermHeaderService;
import com.hongdian.plat.term.service.ITermService;
import com.hongdian.plat.term.util.JSONUtils;
import com.hongdian.sys.common.util.CommUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @date 2018-05-21 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
@Component
public class DevLoginAction extends BaseAction {

	@Value(value = "${sys.fep-itss-pattern}")
	private String itssFepPattern;

	@Resource
	ITermService termService;
	@Resource
	IBizStatBasicService stationService;
	@Resource
	ITermHeaderService termHeaderService;

	@Override
	public void excute(FepUpMqttEntity fep) {
		//处理设备登录消息
		String content = fep.getContent();
		String termSn = fep.getSn();
		DevLoginEntity entity = JSONUtils.readValue(content, DevLoginEntity.class);

		// term 和 termHeader
		Term term = new Term();
		term.setHardVersion(entity.getHardVer());
		term.setSn(termSn);
		term.setSoftVersion(entity.getSoftVer());
		term.setTermModel(entity.getModel());
		term.setTermTypeId(entity.getTermTypeId());
		term.setUseTime(entity.getTime());
		termService.insertOrUpdate(term);

		//termHeader
		TermHeader termHeader = SystemConstants.TERM_HEADER_CACHE.get(termSn);
		if (CommUtils.isNull(termHeader)) {
			termHeader = new TermHeader();
		}
		termHeader.setConnectType(null);
		termHeader.setIp(entity.getIp());
		termHeader.setLastLoginTime(entity.getTime());
		termHeader.setOnlineState(OnLineEnum.ON_LINE.getStatus());
		if (fep.getSource().startsWith(itssFepPattern)) {
			termHeader.setSourceOps(fep.getSource());//运维前置
		} else {
			termHeader.setSourceBiz(fep.getSource());//业务前置
		}
		termHeader.setParamVersion(entity.getParamVer());
		termHeader.setPort(Integer.valueOf(entity.getPort()));
		termHeader.setTermSn(termSn);
		//更新设备状态缓存和库
		SystemConstants.TERM_HEADER_CACHE.put(termSn, termHeader);
		termHeaderService.insertOrUpdateAllColumn(termHeader);

		//设备第一次注册需要向 biz_stat_basic 表插入一条数据，stationId 作为 term表的外键
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("SN", termSn);
		List<Term> termList = termService.selectByMap(map);
		if (!termList.isEmpty()) {
			Term termTemp = termList.get(0);
			if (CommUtils.isNull(termTemp.getStationId())) {
				BizStatBasic station = new BizStatBasic();
				station.setComments("no comments");
				stationService.insert(station);
				termTemp.setStationId(String.valueOf(station.getId()));
				termService.insertOrUpdate(termTemp);
			}
		}
	}

}
