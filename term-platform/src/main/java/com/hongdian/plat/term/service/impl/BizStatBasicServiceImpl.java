package com.hongdian.plat.term.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongdian.plat.term.entity.BizStatBasic;
import com.hongdian.plat.term.mapper.BizStatBasicMapper;
import com.hongdian.plat.term.service.IBizStatBasicService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hongdian.plat.term.dto.StationDto;
import com.hongdian.sys.common.util.CommUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cli
 * @since 2018-06-02
 */
@Service
public class BizStatBasicServiceImpl extends ServiceImpl<BizStatBasicMapper, BizStatBasic> implements IBizStatBasicService {

    @Override
    public Page<StationDto> findStationPage(Page<StationDto> page, Map<String, Object> queryMap) {
        return page.setRecords(baseMapper.findStationDtoList(page, queryMap));
    }

    @Override
    public int insertStation(String stcd, String stnm, String sttp, String adcd, String director, String tel, String buildDate, String stlc, String mngCd, String lgtd, String lttd, String sn) {
        BizStatBasic basic = null;
        try {
            basic = new BizStatBasic();
            basic.setStcd(stcd);
            basic.setStnm(stnm);
            basic.setSttp(sttp);
            basic.setAdcd(adcd);
            basic.setDirector(director);
            basic.setTel(tel);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date bulidTime = formatter.parse(buildDate);
            basic.setBuildDate(bulidTime);
            basic.setStlc(stlc);
            basic.setMngCd(mngCd);
            if (!CommUtils.isNull(lgtd)) {
                basic.setLgtd(Double.parseDouble(lgtd));
            }
            if (!CommUtils.isNull(lttd)) {
                basic.setLttd(Double.parseDouble(lttd));
            }
            this.insert(basic);
        } catch (ParseException ex) {
            Logger.getLogger(BizStatBasicServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return basic.getId();
    }

    @Override
    public void updateStation(String id, String stcd, String stnm, String sttp, String adcd, String director, String tel, String buildDate, String stlc, String mngCd, String lgtd, String lttd, String sn) {
        try {
            BizStatBasic basic = this.selectById(id);
            basic.setStcd(stcd);
            basic.setStnm(stnm);
            basic.setSttp(sttp);
            basic.setAdcd(adcd);
            basic.setDirector(director);
            basic.setTel(tel);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date bulidTime;
                bulidTime = formatter.parse(buildDate);
            basic.setBuildDate(bulidTime);
            basic.setStlc(stlc);
            basic.setMngCd(mngCd);
            if (!CommUtils.isNull(lgtd)) {
                basic.setLgtd(Double.parseDouble(lgtd));
            }
            if (!CommUtils.isNull(lttd)) {
                basic.setLttd(Double.parseDouble(lttd));
            }
            basic.updateById();
        } catch (ParseException ex) {
            Logger.getLogger(BizStatBasicServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
