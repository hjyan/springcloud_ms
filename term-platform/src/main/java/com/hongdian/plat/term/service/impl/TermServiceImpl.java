package com.hongdian.plat.term.service.impl;

import com.hongdian.plat.term.entity.Term;
import com.hongdian.plat.term.mapper.TermMapper;
import com.hongdian.plat.term.service.ITermService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 设备基本信息表 服务实现类
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@Service
public class TermServiceImpl extends ServiceImpl<TermMapper, Term> implements ITermService {

    @Resource
    TermMapper termMapper;

    @Override
    public List<Term> selectTermList() {
        return termMapper.getTermsList();
    }

    @Override
    public void insertTerm(int id,String sn,String termModel,String typeId) {
        //取出id
        Term term = new Term();
        term.setStationId(String.valueOf(id));//测站Id
        term.setUseTime(new Date());//系统时间
        term.setSn(sn);
        term.setTermTypeId(typeId);
        term.setTermStdId("0");
        term.setTermModel(termModel);
        this.insert(term);
    }

    @Override
    public void updateTerm(String id, String sn, String termModel,String typeId) {
        Term term =  this.selectById(sn);
        term.setTermModel(termModel);
        term.setTermTypeId(typeId);
        term.updateById();
    }

}
