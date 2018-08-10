/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zyyan.ms.term.exec.devenum.ScheduleTaskStatusEnum;
import com.zyyan.ms.term.entity.ScheduleTask;
import com.zyyan.ms.term.mapper.ScheduleTaskMapper;
import com.zyyan.ms.term.service.ITaskService;
import com.zyyan.ms.term.constant.SystemConstants;
import com.zyyan.ms.term.dto.TaskDto;
import com.zyyan.ms.common.util.CommUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title: TaskServiceImpl.class
 * @author zyyan
 * @date 2018-05-19 15:22:25
 * @version V1.0
 * @Description:
 */
@Service
public class TaskServiceImpl extends ServiceImpl<ScheduleTaskMapper, ScheduleTask> implements ITaskService {

	@Resource
	private ScheduleTaskMapper taskMapper;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void deleteScheduleTask(List<Integer> taskIdList, List<String> termSnList) {
		if (CommUtils.isNull(taskIdList) && CommUtils.isNull(termSnList)) {
			return;
		}

		Map<String, Object> deleteMap = new HashMap<String, Object>();
		//删除预置任务
		Iterator<ScheduleTask> taskIterator = SystemConstants.SCHEDULE_TASK_CACHE.values().iterator();
		if (!CommUtils.isNull(taskIdList)) {
			deleteMap.put("taskIdList", taskIdList);
			//删除缓存预置任务
			while (taskIterator.hasNext()) {
				if (taskIdList.contains(taskIterator.next().getId())) {
					taskIterator.remove();
				}
			}
		} else if (!CommUtils.isNull(termSnList)) {
			deleteMap.put("termSnList", termSnList);
			//删除缓存预置任务
			while (taskIterator.hasNext()) {
				if (termSnList.contains(taskIterator.next().getTermSn())) {
					taskIterator.remove();
				}
			}
		}
		taskMapper.deleteByMap(deleteMap);
	}

	@Override
	public List<ScheduleTask> findUnsuccessScheduleTask() {
		Wrapper<ScheduleTask> wrap = new EntityWrapper<>();
		wrap.in("STATUS", new Integer[]{ScheduleTaskStatusEnum.NON_EXEC.getStatus(), ScheduleTaskStatusEnum.SENDED.getStatus(), ScheduleTaskStatusEnum.EXECING.getStatus()});
		List<ScheduleTask> list = taskMapper.selectList(wrap);
		return list;
	}

	@Override
	public void initScheduleTaskIntoCache() {
		List<ScheduleTask> taskList = this.findUnsuccessScheduleTask();
		if (!CommUtils.isNull(taskList)) {
			for (ScheduleTask scheduleTask : taskList) {
				SystemConstants.SCHEDULE_TASK_CACHE.put(scheduleTask.getId(), scheduleTask);
			}
		}
	}

    @Override
    public Page<TaskDto> findTaskPage(Page<TaskDto> pg, Map<String, Object> queryMap) {
        return pg.setRecords(taskMapper.findTaskList(pg, queryMap));
    }
	
    @Override
    public List<TaskDto> findTaskList(Map<String, Object> queryMap) {
        return taskMapper.findTaskList(queryMap);
    }

}
