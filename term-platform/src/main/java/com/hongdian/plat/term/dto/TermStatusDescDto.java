/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term.dto;

import com.hongdian.plat.term.entity.TermStatusDesc;
import com.hongdian.plat.term.entity.TermStatusGroup;

/**
 *
 * @author win 10
 */
public class TermStatusDescDto extends TermStatusDesc {

	private TermStatusGroup statusGroup;

	public TermStatusGroup getStatusGroup() {
		return statusGroup;
	}

	public void setStatusGroup(TermStatusGroup statusGroup) {
		this.statusGroup = statusGroup;
	}

}
