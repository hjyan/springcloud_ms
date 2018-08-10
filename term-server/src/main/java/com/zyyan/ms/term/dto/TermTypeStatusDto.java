/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.dto;

import com.zyyan.ms.term.entity.TermType;
import java.util.List;

/**
 *
 * @author win 10
 */
public class TermTypeStatusDto extends TermType{
	List<TermStatusDescDto> descDtoList;

	public List<TermStatusDescDto> getDescDtoList() {
		return descDtoList;
	}

	public void setDescDtoList(List<TermStatusDescDto> descDtoList) {
		this.descDtoList = descDtoList;
	}

}
