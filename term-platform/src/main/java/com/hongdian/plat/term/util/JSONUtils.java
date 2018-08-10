package com.hongdian.plat.term.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

public class JSONUtils {

	private static ObjectMapper mapper = new ObjectMapper();

	static {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		mapper.setDateFormat(dateFormat);
		DateTimeConverter dtConverter = new DateConverter();
		dtConverter.setPattern("yyyy-MM-dd hh:mm:ss");
		ConvertUtils.register(dtConverter, Date.class);
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
	public static HashMap<String, Object> readMapFromString(String str) {
		HashMap<String, Object> map = new HashMap<>();
		try {
			map = mapper.readValue(str, HashMap.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public static <T> List readValueAsList(String content, Class<T> type) throws Exception {
		List obj = null;
		JavaType javaType = getCollectionType(ArrayList.class, type);
		obj = mapper.readValue(content, javaType);
		return obj;
	}

	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}
}
