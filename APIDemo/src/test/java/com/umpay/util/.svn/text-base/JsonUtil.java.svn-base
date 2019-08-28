package com.umpay.util;

import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 
 * <p>Title:JsonUtil.java</p>
 *
 * <p>Description:Json格式化工具类</p>
 *
 * <p>Copyright:Copyright (c) 2015</p>
 *
 * <p>Company:creditease</p>
 *
 * @author cuiyingjie
 *
 * @version v1.0
 */
public class JsonUtil {

	/**
	 * 将json字符串转换成泛型的对象
	 * @param jsonString  json字符串
	 * @param c 泛型的对象
	 * @return 泛型的对象
	 * @throws ClassCastException
	 * @author 
	 * @date 
	 */
	public static <T> T jsonStringToObject(String jsonString, Class<T> c){
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return (T)mapper.readValue(jsonString, c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 将json字符串转换成泛型的集合对象
	 * @param jsonString json字符串
	 * @param collectionClass 泛型的Collection
	 * @param elementClasses 元素类 
	 * @return
	 * @author 
	 * @param <T>
	 * @date
	 */
	@SuppressWarnings("unchecked")
	public static  <T> T jsonStringToCollection(String jsonString,Class<T> collectionClass, Class<?>... elementClasses){
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
			return (T) mapper.readValue(jsonString, javaType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @param obj Object
	 * @param isEnableFeature
	 * @return
	 */
	public static String objectToJsonString(Object obj,boolean isEnableFeature){
		try {
			ObjectMapper mapper = new ObjectMapper();
			if(isEnableFeature){
				mapper.enable(SerializationFeature.INDENT_OUTPUT);
			}else{
				mapper.disable(SerializationFeature.INDENT_OUTPUT);		
			}
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 将对象转换成json字符串
	 * @param obj Object
	 * @return String
	 * @author 
	 * @date
	 */
	public static String objectToJsonString(Object obj){
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(SerializationFeature.INDENT_OUTPUT);		
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public static Map<String,Object> objectToMap(Object obj){
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(SerializationFeature.INDENT_OUTPUT);	
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.convertValue(obj, Map.class);
					
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static <T> T mapToObject(Map<String,?> map,Class<T> c){
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(SerializationFeature.INDENT_OUTPUT);	
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.convertValue(map, c);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
