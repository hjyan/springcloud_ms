/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.dto;

import com.hongdian.plat.term.entity.Term;
import com.hongdian.plat.term.entity.TermParamGroup;
import com.hongdian.plat.term.entity.TermParamInfo;
import com.hongdian.plat.term.entity.TermParamValue;

/**
 *
 * @author win 10
 */
public class TermParamDto {
	private TermParamValue value;
	private TermParamInfo info;
	private TermParamGroup group;
	private Term term;

	public TermParamValue getValue() {
		return value;
	}

	public void setValue(TermParamValue value) {
		this.value = value;
	}

	public TermParamInfo getInfo() {
		return info;
	}

	public void setInfo(TermParamInfo info) {
		this.info = info;
	}

	public TermParamGroup getGroup() {
		return group;
	}

	public void setGroup(TermParamGroup group) {
		this.group = group;
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}
	
}
