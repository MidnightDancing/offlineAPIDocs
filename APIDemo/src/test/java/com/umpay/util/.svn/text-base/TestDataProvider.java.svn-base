package com.umpay.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestDataProvider {
	public static String jsonStrDataProvider(InputStream is) {		
    	BufferedReader isr = new BufferedReader(new InputStreamReader(is));
    	StringBuffer buffer  = new StringBuffer();
    	String str;
		try {
			str = isr.readLine();
			while(str!=null){
	    		buffer.append(str);
	    		str = isr.readLine();
	    	}
	    	isr.close();
		} catch (IOException e) {	
			e.printStackTrace();
		}
    	
		return buffer.toString();
		
	}
//	/*
//	 * Return a Map[][1]
//	 */
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public static Object[][] loopListContainsMap(Object obj,String fileName){		
//		String testDataStr = TestDataProvider.jsonStrDataProvider(obj.getClass().getResourceAsStream(fileName));
//		ArrayList<Map<String,Object>> investments = JsonUtil.jsonStringToCollection(testDataStr, ArrayList.class, Map.class);
//		int size = investments.size();
//		Map[][] inputStrArray = new Map[size][1];
//		for(int i=0;i<size;i++){
//			inputStrArray[i][0] = investments.get(i);
//		}
//		return inputStrArray;
//	}
//	/*
//	 * Return a List<Map<String,Object>>
//	 */
//	public static List<Map<String,Object>> retriveListContainsMaps(Object obj,String fileName){		
//		String testDataStr = TestDataProvider.jsonStrDataProvider(obj.getClass().getResourceAsStream(fileName));
//		ArrayList<Map<String,Object>> investments = JsonUtil.jsonStringToCollection(testDataStr, ArrayList.class, Map.class);		
//		return investments;
//	}
//	/*
//	 * Return a List<Map<String,Object>>
//	 */
//	public static List<Map<String,String>> retriveListContainsMapsAsString(Object obj,String fileName){		
//		String testDataStr = TestDataProvider.jsonStrDataProvider(obj.getClass().getResourceAsStream(fileName));
//		ArrayList<Map<String,String>> investments = JsonUtil.jsonStringToCollection(testDataStr, ArrayList.class, Map.class);		
//		return investments;
//	}
//	/*
//	 * Return a String[][1]
//	 */
//	public static Object[][] loopStringArray(Object obj,String fileName){		
//		String testDataStr = TestDataProvider.jsonStrDataProvider(obj.getClass().getResourceAsStream(fileName));
//		ArrayList<String> investments = JsonUtil.jsonStringToCollection(testDataStr, ArrayList.class,String.class);
//		int size = investments.size();
//		String[][] inputStrArray = new String[size][1];
//		for(int i=0;i<size;i++){
//			inputStrArray[i][0] = investments.get(i);
//		}
//		return inputStrArray;
//	}
//	
//	
//	/*
//	 * Return a Map<String,Map<String,Object>>
//	 */
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public static Map<String,Map<String,String>> loopListMap(Object obj,String fileName){		
//		String testDataStr = TestDataProvider.jsonStrDataProvider(obj.getClass().getResourceAsStream(fileName));
//		Map<String,Map<String,String>> investments = JsonUtil.jsonStringToObject(testDataStr, Map.class);
//		System.out.println(investments);
//		return investments;
//	}
//	
}
