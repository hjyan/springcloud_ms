package com.zyyan.ms.term.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.zyyan.ms.term.dto.TaskDto;
import com.zyyan.ms.term.entity.ScheduleTask;
import java.util.List;
import java.util.Map;

/**
 * 预置任务
 *
 * @author Administrator
 */
public interface ScheduleTaskMapper extends BaseMapper<ScheduleTask> {

	/**
	 * 根据条件查询批量预置任务--设置排序
	 *
	 * @param queryMap
	 * @return
	 */
	public List<ScheduleTask> findScheduleTaskListByOrder(Map<String, Object> queryMap);

	/**
	 * 根据条件查询批量预置任务(自动补全)
	 *
	 * @param queryMap
	 * @return
	 */
	public List<ScheduleTask> findTaskManageInfoToAutocomplete(Map<String, Object> queryMap);

//    public List<ScheduleTask> findScheduleTaskList(Map<String, Object> queryMap);
	public List<TaskDto> findTaskList(Pagination page, Map<String, Object> queryMap);

	public List<TaskDto> findTaskList(Map<String, Object> queryMap);
}
