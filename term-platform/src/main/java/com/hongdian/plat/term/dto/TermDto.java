/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.dto;

import com.hongdian.plat.term.entity.Term;
import com.hongdian.plat.term.entity.TermHeader;
import com.hongdian.plat.term.entity.TermLocation;
import com.hongdian.plat.term.entity.TermModel;
import com.hongdian.plat.term.entity.TermStd;
import com.hongdian.plat.term.entity.TermType;
import com.hongdian.plat.term.entity.TermVersion;

/**
 * @date 2018-06-02 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
public class TermDto extends Term {

	private TermLocation termLocation;
	private TermType termType;
	private TermVersion termV;
	private TermModel termM;
	private TermStd termStd;
	private TermHeader termHeader;

	public TermLocation getTermLocation() {
		return termLocation;
	}

	public void setTermLocation(TermLocation termLocation) {
		this.termLocation = termLocation;
	}

	public TermType getTermType() {
		return termType;
	}

	public void setTermType(TermType termType) {
		this.termType = termType;
	}

	public TermVersion getTermV() {
		return termV;
	}

	public void setTermV(TermVersion termV) {
		this.termV = termV;
	}

	public TermModel getTermM() {
		return termM;
	}

	public void setTermM(TermModel termM) {
		this.termM = termM;
	}

	public TermStd getTermStd() {
		return termStd;
	}

	public void setTermStd(TermStd termStd) {
		this.termStd = termStd;
	}

	public TermHeader getTermHeader() {
		return termHeader;
	}

	public void setTermHeader(TermHeader termHeader) {
		this.termHeader = termHeader;
	}

}
