/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.term.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author win 10
 */
public class TermStatusDto implements Serializable{
	
	private String termSn;
	private String statusIds;
	private String statusNames;
	private String statusDescs;
	private String statusValues;
	
	private List<String> statusIdsList;
	private List<String> statusValueList;
	private List<String> statusNameList;
	private List<String> statusDescList;

	public String getTermSn() {
		return termSn;
	}

	public void setTermSn(String termSn) {
		this.termSn = termSn;
	}

	public String getStatusValues() {
		return statusValues;
	}

	public void setStatusValues(String statusValues) {
		this.statusValues = statusValues;
	}

	public String getStatusNames() {
		return statusNames;
	}

	public void setStatusNames(String statusNames) {
		this.statusNames = statusNames;
	}

	public String getStatusDescs() {
		return statusDescs;
	}

	public void setStatusDescs(String statusDescs) {
		this.statusDescs = statusDescs;
	}

	public List<String> getStatusValueList() {
		return statusValueList;
	}

	public void setStatusValueList(List<String> statusValueList) {
		this.statusValueList = statusValueList;
	}

	public List<String> getStatusNameList() {
		return statusNameList;
	}

	public void setStatusNameList(List<String> statusNameList) {
		this.statusNameList = statusNameList;
	}

	public List<String> getStatusDescList() {
		return statusDescList;
	}

	public void setStatusDescList(List<String> statusDescList) {
		this.statusDescList = statusDescList;
	}

	public String getStatusIds() {
		return statusIds;
	}

	public void setStatusIds(String statusIds) {
		this.statusIds = statusIds;
	}

	public List<String> getStatusIdsList() {
		return statusIdsList;
	}

	public void setStatusIdsList(List<String> statusIdsList) {
		this.statusIdsList = statusIdsList;
	}
	
}
