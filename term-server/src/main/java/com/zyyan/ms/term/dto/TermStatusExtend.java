/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.dto;

import com.zyyan.ms.term.entity.TermStatus;
import com.zyyan.ms.term.entity.TermStatusDesc;

/**
 *
 * @author win 10
 */
public class TermStatusExtend extends TermStatus{
	private TermStatusDesc desc;

	public TermStatusDesc getDesc() {
		return desc;
	}

	public void setDesc(TermStatusDesc desc) {
		this.desc = desc;
	}
	
}
