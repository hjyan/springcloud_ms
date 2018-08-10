package com.hongdian.plat.term.entity;

/**
 * <p>
 * 泄洪预警手动语音实时报警表
 * </p>
 *
 * @author xlhua
 * @since 2018-07-19
 */
public class FloodVoxAlarmCurrent  extends FloodVoxAlarm{

    private FloodVoxRel rel;
    private FloodPlaystaCur cur;

    public FloodVoxRel getRel() {
        return rel;
    }

    public FloodPlaystaCur getCur() {
        return cur;
    }

    public void setRel(FloodVoxRel rel) {
        this.rel = rel;
    }

    public void setCur(FloodPlaystaCur cur) {
        this.cur = cur;
    }
    
}
