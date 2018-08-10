package com.hongdian.plat.term.service;

import com.hongdian.plat.term.entity.BizStationType;
import com.baomidou.mybatisplus.service.IService;
import java.util.List;

/**
 * <p>
 * 测站类型 服务类
 * </p>
 *
 * @author xlhua
 * @since 2018-08-01
 */
public interface IBizStationTypeService extends IService<BizStationType> {

    public List<BizStationType> selectStationTypesList();
}
