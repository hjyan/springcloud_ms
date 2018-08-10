package com.zyyan.ms.term.service.impl;

import com.zyyan.ms.term.entity.BizDept;
import com.zyyan.ms.term.mapper.BizDeptMapper;
import com.zyyan.ms.term.service.IBizDeptService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 行政区划信息 服务实现类
 * </p>
 *
 * @author xlhua
 * @since 2018-08-01
 */
@Service
public class BizDeptServiceImpl extends ServiceImpl<BizDeptMapper, BizDept> implements IBizDeptService {

    @Resource
    BizDeptMapper bizDeptMapper;

    @Override
    public List<BizDept> findBizDeptsList() {
        return bizDeptMapper.getBizDeptList();
    }
    
    
}
