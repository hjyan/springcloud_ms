package com.hongdian.plat.term.service;

import com.hongdian.plat.term.entity.Term;
import com.baomidou.mybatisplus.service.IService;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设备基本信息表 服务类
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
public interface ITermService extends IService<Term> {

    public List<Term> selectTermList();
    
    public void insertTerm(int id,String sn,String termModel,String typeId);

    public void updateTerm(String id,String sn,String termModel,String typeId);
}
