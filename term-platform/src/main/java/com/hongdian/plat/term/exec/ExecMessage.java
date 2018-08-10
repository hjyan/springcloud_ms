/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hongdian.plat.term.exec;

/**   
 * @date 2018-05-25 00:00:00
 * @Description: 
 * @author zyyan
 * Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
public class ExecMessage{
	
	private byte[] body;

	public ExecMessage(byte[] body) {
		this.body = body;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}
	
}
