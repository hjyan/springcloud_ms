package com.zyyan.ms.term.constant;

import java.util.HashMap;

public class CodeConstants {
	
	private static HashMap<Integer, String> codeMap = new HashMap<>();
	
	static {
		codeMap.put(0, "term execute command successfully ......");
		codeMap.put(-2, "term out off line ......");
		codeMap.put(-3, "term's upgrading param is invalid ......");
		codeMap.put(-4, "term is upgrading ......");
		codeMap.put(-5, "package is not found ......");
		codeMap.put(-6, "error occured when term read package ......");
		codeMap.put(-7, "term time out ......");
		codeMap.put(-8, "term is in other transaction ......");
		codeMap.put(-9, "fail to stop term's upgrading ......");
		codeMap.put(-10, "term rejects to execute command ......");
		codeMap.put(-11, "fail to execute command ......");
		codeMap.put(-12, "fail to send message to term ......");
		codeMap.put(-13, "front-end command is invalid ......");
	}
	
	public static String getMsg(Integer code) {
		return codeMap.get(code);
	}

}
