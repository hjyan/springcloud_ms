package com.hongdian.plat.term.service;

import com.hongdian.plat.term.entity.TermModel;
import com.baomidou.mybatisplus.service.IService;
import java.util.List;

/**
 * <p>
 * 设备型号表 服务类
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
public interface ITermModelService extends IService<TermModel> {

    public List<TermModel> findTermModelsList();
    
}
