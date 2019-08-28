package com.umpay.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/** ******************  类说明  *********************
 * class       :  ConfigUtil
 * @author     :  LiuJiLong
 * @version    :  1.0  
 * description :  配置工具类
 * @see        :                        
 * ************************************************/   
public class ConfigUtil {
	final static Logger logger = LoggerFactory.getLogger(ConfigUtil.class);
	static private String configPath;
	
	public static String getConfigPath() {
		return configPath;
	}
	
	public static void setConfigPath(String configPath) {
		ConfigUtil.configPath = configPath;
	}

	static Properties props = null;
	
	/**
	 * 初始化
	 */
	public ConfigUtil(){
//		ConfigUtil.configPath = configPath;
	}
	
	/**
	 * 初始化
	 * @param configPath="/config/jdbc.properties"
	 * @param charSet = UTF-8|GBK
	 */
	
	public static void loadConfig(String configPath,String charSet){

		try {
			props = new Properties();
			File classpathRoot = new File(System.getProperty("user.dir"));
			File dir = new File(classpathRoot,"");//项目所在根目录
			String prefix = dir.getAbsolutePath() + configPath;
			System.out.println("读取配置文件："+prefix);
			ConfigUtil.configPath = prefix;
			InputStreamReader in = new InputStreamReader(new FileInputStream(prefix),charSet);
			props.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadConfig(String configPath){
		ConfigUtil.configPath = configPath;
		InputStream in = null;

		try {
			in = ConfigUtil.class.getResourceAsStream(configPath);
			props = new Properties();
			props.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** ********************************************
	 * method name   : getConfig 
	 * description   : 获取配置的VALUE
	 * @return       : String
	 * @param        : @param key
	 * @param        : @return
	 * modified      : LiuJiLong ,  2011-12-20  上午11:15:23
	 * @see          : 
	 * ********************************************/      
	public static String getConfig(String key){
		return trim(props.getProperty(key));//配置文件读取
	}
	
	/**
	 * 获取环境参数
	 * @param key
	 * @return
	 */
	public static String getRunEnvironment(String key){
		String runEnvironment = "";
		runEnvironment =  trim(props.getProperty(key));//配置文件读取
		if (System.getenv("RunEnvironment") != null)//从传递的参数读取
		{
			return System.getenv("RunEnvironment");
		} else
		{
			return runEnvironment;
		}
	}
	
	/**
	 * 获取线上、开发、测试
	 * @param key
	 * @return
	 */
	public static String getEnv(){
		String testEnv = "";
		loadConfig("/config/config.properties","GBK");// 加载配置文件
		testEnv =  trim(props.getProperty("env"));//配置文件读取
		if (System.getenv("env") != null)//从传递的参数读取
		{
			return System.getenv("env");
		} else
		{
			return testEnv;
		}
	}
	
	/**
	 * 获取浏览器类型--火狐、谷歌
	 * @param key
	 * @return
	 */
	public static String getBrowserType(){
		String browserType = "";
		loadConfig("/config/config.properties","GBK");// 加载配置文件
		browserType =  trim(props.getProperty("browserType"));//配置文件读取
		if (System.getenv("browserType") != null)//从传递的参数读取
		{
			return System.getenv("browserType");
		} else
		{
			return browserType;
		}
	}
	
	/**
	 * 去掉字符串首尾的 <空格字符>（包括TAB），如果s为null则返回空字符串""。
	 */
	public static String trim(String s) {
		if (s == null)
			return "";
		else
			return s.trim();
	}
	
	/** ********************************************
	 * method name   : getKeys 
	 * description   : 获取配置的VALUES
	 * @return       : Enumeration<String>
	 * @param        : @return
	 * modified      : LiuJiLong ,  2011-12-20  上午11:15:43
	 * @see          : 
	 * ********************************************/      
	public static Enumeration<Object> getKeys(){
		return  (Enumeration<Object>)props.keys(); 
	}
	
	/** ********************************************
	 * method name   : getKeys 
	 * description   : 获取配置的VALUES
	 * @return       : Enumeration<String>
	 * @param        : @return
	 * modified      : LiuJiLong ,  2011-12-20  上午11:15:43
	 * @see          : 
	 * ********************************************/
	@SuppressWarnings("unchecked")
	public static Enumeration<String> getValuess(){
		return  (Enumeration<String>) props.propertyNames();
	}
	
	/** ********************************************
	 * method name   : getValuesByPre 
	 * description   : 返回一系列带有指定前缀的配置项
	 * @return       : Map<String,String>
	 * @param        : @param pre
	 * @param        : @return
	 * modified      : LiuJiLong ,  2012-8-6  下午03:04:13
	 * @see          : 
	 * ********************************************/      
	public static Map<String, String> getValuesByPre(String pre){
		Map<String, String> map = new HashMap<String, String>();
		for(Entry<Object, Object> ent:props.entrySet()){
			if(ent.getKey()!=null&&ent.getKey().toString().trim().startsWith(pre)){//如果配置的KEY值与传入的前缀一致，则返回其值
				map.put(ent.getKey().toString(), ent.getValue().toString());
			}
		}
		return map;
	}
	
	/**
	 * 含有前缀的属性中，取属性indexPre长度后的值作为key值，返回map
	 * 此方法等同于getValuesWithoutPre()方法。
	 * @param pre
	 * @param indexPre
	 * @return
	 * @author "ZouGuoPing"
	 * @date 2014-9-29 下午07:13:12
	 *
	 */
	public static Map<String, String> getValuesByPre(String pre,int indexPre){
		Map<String, String> map = new HashMap<String, String>();
		for(Entry<Object, Object> ent:props.entrySet()){
			if(ent.getKey()!=null&&ent.getKey().toString().trim().startsWith(pre)){//如果配置的KEY值与传入的前缀一致，则返回其值
				map.put(ent.getKey().toString().substring(indexPre), ent.getValue().toString().trim());
			}
		}
		return map;
	}
	
	/** ********************************************
	 * method name   : getValuesByPre 
	 * description   : 返回一系列带有指定前缀的配置项
	 * @return       : Map<String,String>
	 * @param        : @param pre
	 * @param        : @return
	 * modified      : LiuJiLong ,  2012-8-6  下午03:04:13
	 * @see          : 
	 * ********************************************/      
	public static Map<String, String> getValuesWithoutPre(String pre){
		Map<String, String> map = new HashMap<String, String>();
		for(Entry<Object, Object> ent:props.entrySet()){
			String entKey = null;
			if(ent.getKey()!=null){
				entKey = ent.getKey().toString();
			}else{
				continue;
			}
			if(entKey.trim().startsWith(pre)){//如果配置的KEY值与传入的前缀一致，则返回其值
				map.put(entKey.replaceFirst(pre+"\\.", ""), ent.getValue().toString().trim());
			}
		}
		return map;
	}
	
	/**
	 * 
	 * @param pre
	 * @param indexPre
	 * @return
	 */
	public static Map<String, String> getValues(){
		Map<String, String> map = new HashMap<String, String>();
		for(Entry<Object, Object> ent:props.entrySet()){
			if(ent.getKey()!=null){//如果配置的KEY值与传入的前缀一致，则返回其值
				map.put(ent.getKey().toString().trim(), ent.getValue().toString().trim());
			}
		}
		return map;
	}
	
	/** ********************************************
	 * method name   : setConfig 
	 * description   : 设置配置
	 * @return       : boolean
	 * @param        : @param key
	 * @param        : @param value
	 * @param        : @return
	 * modified      : LiuJiLong ,  2011-12-20  上午11:15:56
	 * @see          : 
	 * ********************************************/      
	public static boolean setConfig(String key, String value) {
		try {
			OutputStream fos = new FileOutputStream(configPath);
			props.setProperty(key, value);
			props.store(fos, "Update '" + key + "' value");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
}



