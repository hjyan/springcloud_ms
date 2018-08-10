package com.hongdian.plat.term.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongdian.plat.term.entity.BizStatBasic;
import com.baomidou.mybatisplus.service.IService;
import com.hongdian.plat.term.dto.StationDto;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cli
 * @since 2018-06-02
 */
public interface IBizStatBasicService extends IService<BizStatBasic> {

    public Page<StationDto> findStationPage(Page<StationDto> page, Map<String, Object> queryMap);
    
    public int insertStation(String stcd,String stnm,String sttp,String adcd,String director,String tel,String buildDate,String stlc,String mngCd,String lgtd,String lttd,String sn);
    
    public void updateStation(String id,String stcd,String stnm,String sttp,String adcd,String director,String tel,String buildDate,String stlc,String mngCd,String lgtd,String lttd,String sn);
}
