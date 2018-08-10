/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.exec.action;

import com.hongdian.plat.term.constant.SystemConstants;
import com.hongdian.plat.term.entity.ScheduleTask;
import com.hongdian.plat.term.entity.TermHeader;
import com.hongdian.plat.term.exec.devenum.OnLineEnum;
import com.hongdian.plat.term.exec.devenum.ScheduleTaskStatusEnum;
import com.hongdian.plat.term.mqttmodel.base.FepUpMqttEntity;
import com.hongdian.plat.term.service.ITaskService;
import com.hongdian.plat.term.service.ITermHeaderService;
import com.hongdian.sys.common.util.CommUtils;
import com.hongdian.sys.common.util.TimeUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @date 2018-05-21 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
@Component
public class FepLogoutAction extends BaseAction {

	@Value(value = "${sys.fep-itss-pattern}")
	private String fepItssPattern;

	@Resource
	ITermHeaderService termHeaderService;

	@Resource
	ITaskService taskService;

	@Override
	public void excute(FepUpMqttEntity fep) {
		//fep登出，所有设备下线
		List<TermHeader> list = new ArrayList<>();
		list.addAll(SystemConstants.TERM_HEADER_CACHE.values());
		for (TermHeader header : list) {
			if (CommUtils.notNull(header.getSourceOps()) && header.getSourceOps().startsWith(fepItssPattern)) {
				header.setOnlineState(OnLineEnum.OFF_LINE.getStatus());
				header.setLastLogoutTime(new Date());
				header.setSourceBiz(null);
				header.setSourceOps(null);
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("onlineState", OnLineEnum.OFF_LINE.getStatus());
		map.put("lastLogoutTime", TimeUtil.convertDateToString(new Date(), TimeUtil.YMDHMS));
		map.put("sourceOps", fep.getSource());//更新时带上 fep 来源
		termHeaderService.updateFepSourceTermOffline(map);

		//停止正在执行的任务(删除状态)
		Set<Map.Entry<Integer, ScheduleTask>> taskSet = SystemConstants.SCHEDULE_TASK_CACHE.entrySet();
		List<ScheduleTask> tempList = new ArrayList<>();
		for (Map.Entry<Integer, ScheduleTask> taskEntity : taskSet) {
			ScheduleTask task = taskEntity.getValue();
			Integer taskId = taskEntity.getKey();
			if (fep.getSource().equals(task.getSource())
					&& (task.getStatus() == ScheduleTaskStatusEnum.EXECING.getStatus() || task.getStatus() == ScheduleTaskStatusEnum.SENDED.getStatus())) {//正在等在此fep执行的任务
				task.setStatus(ScheduleTaskStatusEnum.DELETE.getStatus());
				task.setFailMsg("fep 服务断开");
				tempList.add(task);
				SystemConstants.SCHEDULE_TASK_CACHE.remove(taskId);
			}
		}

		if (!tempList.isEmpty()) {
			taskService.updateBatchById(tempList);
		}
	}

}
