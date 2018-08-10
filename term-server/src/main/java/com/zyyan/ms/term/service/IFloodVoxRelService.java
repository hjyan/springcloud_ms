package com.zyyan.ms.term.service;

import com.zyyan.ms.term.entity.FloodVoxRel;
import com.baomidou.mybatisplus.service.IService;
import java.util.HashMap;

/**
 * <p>
 * 泄洪预警手动语音报警设备信息表 服务类
 * </p>
 *
 * @author xlhua
 * @since 2018-07-19
 */
public interface IFloodVoxRelService extends IService<FloodVoxRel> {

    /**
     * 发布预警添加被通知终端记录
     */
    public void insertFloodVoxRel(FloodVoxRel floodVoxRel);
    
    /**
     * 删除关联的终端信息
     */
    public void deletefloodVoxRelByIdAndGate(HashMap<String,Object> queryMap);
}
