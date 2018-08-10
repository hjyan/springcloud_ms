package com.zyyan.ms.term.service.impl;

import com.zyyan.ms.term.entity.TermModel;
import com.zyyan.ms.term.mapper.TermModelMapper;
import com.zyyan.ms.term.service.ITermModelService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 设备型号表 服务实现类
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@Service
public class TermModelServiceImpl extends ServiceImpl<TermModelMapper, TermModel> implements ITermModelService {

    @Resource
    TermModelMapper termModelMapper;
    
    @Override
    public List<TermModel> findTermModelsList() {
        return termModelMapper.findTermModelsList();
    }

}
