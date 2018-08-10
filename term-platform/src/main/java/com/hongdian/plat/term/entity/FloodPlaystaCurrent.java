/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.entity;

/**
 *
 * @author 15927
 */
public class FloodPlaystaCurrent extends FloodPlaystaCur {

    private FloodVoxRel rel;
    private FloodVoxAlarm alarm;

    public FloodVoxRel getRel() {
        return rel;
    }

    public FloodVoxAlarm getAlarm() {
        return alarm;
    }

    public void setRel(FloodVoxRel rel) {
        this.rel = rel;
    }

    public void setAlarm(FloodVoxAlarm alarm) {
        this.alarm = alarm;
    }

}
