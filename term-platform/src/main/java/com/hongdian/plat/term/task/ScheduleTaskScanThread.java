/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.task;

import com.hongdian.plat.term.config.SpringContextUtil;
import com.hongdian.plat.term.constant.SystemConstants;
import com.hongdian.plat.term.exec.devenum.DevCtrlEnum;
import com.hongdian.plat.term.exec.devenum.ErrorEnum;
import com.hongdian.plat.term.exec.devenum.OnLineEnum;
import com.hongdian.plat.term.exec.devenum.ScheduleTaskStatusEnum;
import com.hongdian.plat.term.entity.ScheduleTask;
import com.hongdian.plat.term.entity.TermHeader;
import com.hongdian.plat.term.service.ITaskService;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

/**
 * @date 2018-05-17 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
public class ScheduleTaskScanThread implements Runnable {

	private final Logger log = LoggerFactory.getLogger(ScheduleTaskScanThread.class);
	private ITaskService taskService;
	private TaskExecutor taskExecutor;

	public ScheduleTaskScanThread() {
		taskService = (ITaskService) SpringContextUtil.getBean(ITaskService.class);
		taskExecutor = (TaskExecutor) SpringContextUtil.getBean(TaskExecutor.class);
	}

	@Override
	public void run() {
		Iterator<Integer> it;
		boolean isOnLine;//是否在线
		boolean noExecuting;
		//是否需要发送
		boolean isMustSend;
		Set<Entry<Integer, ScheduleTask>> taskSet;
		ScheduleTask task;
		TermHeader termStatus;
		String taskName;
		while (SystemConstants.SCHEDULE_IS_RUN) {
			try {
				if (SystemConstants.SCHEDULE_TASK_CACHE.isEmpty()) {
					Thread.sleep(5000);
				}
				taskSet = SystemConstants.SCHEDULE_TASK_CACHE.entrySet();
				for (Entry<Integer, ScheduleTask> taskEntity : taskSet) {
					task = taskEntity.getValue();
					taskName = task.getCtrlName();

					//检查终端是否有正在执行的任务（不是取消升级时，如果终端有正在执行的任务将不会下发）
					if (!taskName.equals(DevCtrlEnum.LIST_DEVICE.getCtrlName())
							&& !taskName.equals(DevCtrlEnum.CLEAN_FTP_UPGRADE.getCtrlName())
							&& !taskName.equals(DevCtrlEnum.CLEAN_LOCAL_UPGRADE.getCtrlName())) {
						if (this.checkedTermHaveTask(task.getTermSn())) {//查找sn是否有正在执行的任务
							continue;
						}
					}

					termStatus = SystemConstants.TERM_HEADER_CACHE.get(task.getTermSn());
					if (null != termStatus) {
						isOnLine = termStatus.getOnlineState().intValue() == OnLineEnum.ON_LINE.getStatus();//在线
						noExecuting = ScheduleTaskStatusEnum.NON_EXEC.getStatus() == task.getStatus();//未执行

						isMustSend = isOnLine && noExecuting;
						if (isMustSend) {
							//启动任务执行线程
							ScheduleTaskSendThread taskSendThread = new ScheduleTaskSendThread(task);
							taskExecutor.execute(taskSendThread);
						}
					}
				}
				Thread.sleep(5000);
			} catch (InterruptedException ex) {
				break;
			} catch (Exception ex) {
				continue;
			}
		}

	}

	/**
	 * 检查终端是否已经有正在执行的任务
	 *
	 * @param termSn
	 * @return
	 */
	private boolean checkedTermHaveTask(String termSn) {
		Collection<ScheduleTask> coll = SystemConstants.SCHEDULE_TASK_CACHE.values();
		boolean flag = false;
		ScheduleTask scheduleTask;
		for (Iterator<ScheduleTask> it = coll.iterator(); it.hasNext();) {
			scheduleTask = it.next();
			if ((ScheduleTaskStatusEnum.EXECING.getStatus() == scheduleTask.getStatus() || ScheduleTaskStatusEnum.SENDED.getStatus() == scheduleTask.getStatus())
					&& scheduleTask.getTermSn().equals(termSn)) {

				//预置任务下发超时
				if (Calendar.getInstance().getTimeInMillis() - scheduleTask.getExecStartTime().getTime()
						>= SystemConstants.TASK_EXECUTE_TIMEOUT) {
					this.updateTask(scheduleTask);
					it.remove();
					continue;
				}
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * 修改预置任务信息
	 *
	 * @param task
	 */
	private void updateTask(ScheduleTask task) {
		task.setFailCode(ErrorEnum.TASK_TIMEOUT.getErrorCode());
		task.setStatus(ScheduleTaskStatusEnum.FAILURE.getStatus());
		task.setExecEndTime(Calendar.getInstance().getTime());
		taskService.updateById(task);
	}

}
