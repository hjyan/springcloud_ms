/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.dto;

import com.zyyan.ms.term.entity.ScheduleTask;

/**
 *
 * @author win 10
 */
public class TaskDto extends ScheduleTask{
    private String termName;
    private String stnm;

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getStnm() {
        return stnm;
    }

    public void setStnm(String stnm) {
        this.stnm = stnm;
    }
    
}
