/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.exec.action;

import com.hongdian.plat.term.mqttmodel.base.FepUpMqttEntity;
import com.hongdian.plat.term.entity.TermParamValue;
import com.hongdian.plat.term.service.ITermParamValueService;
import com.hongdian.plat.term.util.JSONUtils;
import com.hongdian.sys.common.util.CommUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @date 2018-05-21 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
@Component
public class DevParamAction extends BaseAction {

	@Resource
	ITermParamValueService termValueService;

	@Override
	public void excute(FepUpMqttEntity fep) {
		String content = fep.getContent();
		String termSn = fep.getSn();
		try {
			if (!CommUtils.isNull(termSn)) {
				List<TermParamValue> paramList = JSONUtils.readValueAsList(content, TermParamValue.class);
				for (TermParamValue value : paramList) {
					value.setTermSn(termSn);
				}
				termValueService.replaceBatch(paramList);
			}
		} catch (Exception ex) {
			log.error(null, ex);
		}
	}

}
