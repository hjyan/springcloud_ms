/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.dto;

import com.zyyan.ms.term.entity.TermStatusDesc;
import com.zyyan.ms.term.entity.TermStatusGroup;

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
