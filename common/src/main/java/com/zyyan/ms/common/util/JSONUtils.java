package com.zyyan.ms.common.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {
	private static ObjectMapper mapper = new ObjectMapper();
	
	static {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		mapper.setDateFormat(dateFormat);
	}
	
	
	public static String writeValueAsString(Object obj) {
		String value = "";
		try {
			value = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			
		}
		return value;
	}
	
	public static <T> T readValue(String src, Class<T> type) {
		T obj = null;
		try {
			obj = mapper.readValue(src, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public static HashMap<String,Object> readMapFormString(String str) {
		HashMap<String,Object> map = new HashMap<>();
		try {
			 map = mapper.readValue(str, HashMap.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
