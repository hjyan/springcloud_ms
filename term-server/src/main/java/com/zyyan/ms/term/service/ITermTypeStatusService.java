package com.zyyan.ms.term.service;

import com.zyyan.ms.term.entity.TermTypeStatus;
import com.baomidou.mybatisplus.service.IService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设备状态组关系表 服务类
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
public interface ITermTypeStatusService extends IService<TermTypeStatus> {

	public void updateBatch(List<TermTypeStatus> termTypeStatusList);
}
