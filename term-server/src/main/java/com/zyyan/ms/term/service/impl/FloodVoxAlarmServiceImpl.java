package com.zyyan.ms.term.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.zyyan.ms.term.entity.FloodVoxAlarm;
import com.zyyan.ms.term.mapper.FloodVoxAlarmMapper;
import com.zyyan.ms.term.service.IFloodVoxAlarmService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zyyan.ms.term.exec.devenum.AlarmPubStatusEnum;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 泄洪预警手动语音报警表 服务实现类
 * </p>
 *
 * @author xlhua
 * @since 2018-07-19
 */
@Service
public class FloodVoxAlarmServiceImpl extends ServiceImpl<FloodVoxAlarmMapper, FloodVoxAlarm> implements IFloodVoxAlarmService {

	@Resource
	private FloodVoxAlarmMapper alarmMapper;

	/**
	 * 实现添加预警记录
	 *
	 * @param floodVoxAlarm
	 */
//    @Override
//    public void insertAlarm(FloodVoxAlarm floodVoxAlarm) {
//        alarmMapper.insertAlarm(floodVoxAlarm);
//    }

	/**
	 * 根据taskId更新预警状态
	 *
	 * @param taskId
	 */
	@Override
	public void updatePubStatusByTaskId(Integer taskId) {
		FloodVoxAlarm alarm = new FloodVoxAlarm();
		alarm.setId(taskId);
		alarm.setPubStatus(AlarmPubStatusEnum.alarm_playing.getPubStatus());
		alarmMapper.updateById(alarm);
	}

	@Override
	public Page<FloodVoxAlarm> findFoodPlaystaCurList(Page<FloodVoxAlarm> pg, Map<String, Object> queryMap) {
		return pg.setRecords(alarmMapper.findFoodPlaystaCurList(pg, queryMap));
	}

	@Override
	public List<FloodVoxAlarm> findFoodPlaystaCurGatesList(Map<String, Object> queryMap) {
		return alarmMapper.findFoodPlaystaCurGatesList(queryMap);
	}

}
