/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.dto;

import com.hongdian.plat.term.entity.BizStatBasic;

/**
 * @date 2018-05-16 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
public class StationDto extends BizStatBasic {

	private TermDto termDto;

	public TermDto getTermDto() {
		return termDto;
	}

	public void setTermDto(TermDto termDto) {
		this.termDto = termDto;
	}

}
