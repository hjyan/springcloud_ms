package com.zyyan.ms.term.service;

import com.zyyan.ms.term.entity.BizDept;
import com.baomidou.mybatisplus.service.IService;
import java.util.List;

/**
 * <p>
 * 行政区划信息 服务类
 * </p>
 *
 * @author xlhua
 * @since 2018-08-01
 */
public interface IBizDeptService extends IService<BizDept> {

    public List<BizDept> findBizDeptsList();
}
