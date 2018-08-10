/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.zyyan.ms.term.dto.TaskDto;
import com.zyyan.ms.term.entity.ScheduleTask;
import java.util.List;
import java.util.Map;

/**
 * @Title: ITaskService.class
 * @author zyyan
 * @date 2018-5-16 13:48:22
 * @version V1.0
 * @Description:
 */
public interface ITaskService extends IService<ScheduleTask> {

	/**
	 * 批量删除预置任务
	 *
	 * @param taskIdList 任务编号列表
	 * @param termSnList
	 */
	public void deleteScheduleTask(List<Integer> taskIdList, List<String> termSnList);

	public List<ScheduleTask> findUnsuccessScheduleTask();

	public void initScheduleTaskIntoCache();

	public Page<TaskDto> findTaskPage(Page<TaskDto> pg, Map<String, Object> queryMap);

	public List<TaskDto> findTaskList(Map<String, Object> queryMap);

}
