package com.hongdian.plat.term.entity;

/**
 * <p>
 * 泄洪预警手动语音实时报警表
 * </p>
 *
 * @author xlhua
 * @since 2018-07-19
 */
public class FloodVoxAlarmHistory  extends FloodVoxAlarm{

    private FloodVoxRel rel;
    private FloodPlaystaHis his;

    public FloodVoxRel getRel() {
        return rel;
    }

    public FloodPlaystaHis getHis() {
        return his;
    }

    public void setRel(FloodVoxRel rel) {
        this.rel = rel;
    }

    public void setHis(FloodPlaystaHis his) {
        this.his = his;
    }

}
