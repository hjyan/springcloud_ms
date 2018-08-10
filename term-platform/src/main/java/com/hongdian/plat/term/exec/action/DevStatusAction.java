/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.exec.action;

import com.hongdian.plat.term.constant.SystemConstants;
import com.hongdian.plat.term.mqttmodel.base.FepUpMqttEntity;
import com.hongdian.plat.term.entity.TermStatus;
import com.hongdian.plat.term.entity.TermStatusDesc;
import com.hongdian.plat.term.entity.TermStatusForm;
import com.hongdian.plat.term.entity.TermStatusH;
import com.hongdian.plat.term.service.ITermStatusFormService;
import com.hongdian.plat.term.service.ITermStatusHService;
import com.hongdian.plat.term.service.ITermStatusService;
import com.hongdian.plat.term.util.JSONUtils;
import com.hongdian.sys.common.util.CommUtils;
import java.util.ArrayList;
import java.util.Date;
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
public class DevStatusAction extends BaseAction {

	@Resource
	ITermStatusService statusService;
	@Resource
	ITermStatusFormService statusFormService;
	@Resource
	ITermStatusHService statusHService;//历史表


	@Override
	public void excute(FepUpMqttEntity fep) {
		String content = fep.getContent();
		String termSn = fep.getSn();
		Map<String, Object> termStatusMap = JSONUtils.readMapFromString(content);
		if(CommUtils.isNull(termSn) || termStatusMap.isEmpty()){
			log.info("termSn is valid...............................");
		}else{
			//生成设备状态表单,一个表单ID 代表一条数据, 入历史库
			TermStatusForm statusForm = new TermStatusForm(termSn, new Date(), fep.getAction());
			statusFormService.insert(statusForm);
			List<TermStatusH> statusHList = parseMap2TermStatusHList(termStatusMap, statusForm.getId());
			statusHService.insertBatch(statusHList);
			
			//入实时
			Map<String, Object> delMap = new HashMap<String, Object>();
			delMap.put("TERM_SN", termSn);
			statusService.deleteByMap(delMap);//删除
			List<TermStatus> statusList = parseMap2TermStatusList(termStatusMap, termSn);
			statusService.insertBatch(statusList);	//状态入库
		}
	}

	private List parseMap2TermStatusHList(Map<String, Object> termStatusMap, Long formId) {
		List obj = null;
		List result = new ArrayList();
		for (TermStatusDesc desc : SystemConstants.TERM_STATUS_DESC_LIST) {
			String value = (String) termStatusMap.get(desc.getName());
			if (CommUtils.notNull(value)) {
				result.add(new TermStatusH(formId, desc.getId(), value));
			}
		}
		return result;
	}

	private List parseMap2TermStatusList(Map<String, Object> termStatusMap, String termSn) {
		List obj = null;
		List result = new ArrayList();
		for (TermStatusDesc desc : SystemConstants.TERM_STATUS_DESC_LIST) {
			String value = (String) termStatusMap.get(desc.getName());
			if (CommUtils.notNull(value)) {
				result.add(new TermStatus(termSn, desc.getId(), value, null));
			}
		}
		return result;
	}
}
