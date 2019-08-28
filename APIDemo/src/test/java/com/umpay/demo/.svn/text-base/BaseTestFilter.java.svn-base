package com.umpay.demo;

import java.util.HashMap;
import java.util.Map;
import org.junit.runner.Description;
import org.junit.runner.manipulation.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTestFilter  extends Filter {
	private static final Logger log = LoggerFactory.getLogger(BaseTestFilter.class);
	private String methodname;
	private String [] methodnames;
	private Map<String,String> methodMap = new HashMap<String,String>();
			
	public BaseTestFilter(String methodname) {
		this.methodname = methodname;
		methodnames = methodname.split(";");
		for(String m : methodnames){
			methodMap.put(m,"Y");
		}
	}

	@Override
	public String describe() {
		return methodname + "should test.";
	}

	//methodname与class中的所有方法进行比较，命中改为执行，没命中的改为不执行，全局有效。
	//要执行多个方法methodname使用分号分割:test001;test002
	@Override
	public boolean shouldRun(Description arg0) {
		String name = arg0.getDisplayName().split("\\(")[0].trim();
		for(String key : methodMap.keySet()){
			if (name.equals((key.trim()))) {
				log.info(key+" should be run...");
				//如果map中的方法与类中的方法匹配了，则方法从map中移除，减少下次方法匹配执行的循环次数，为后续判断map是否为null做准备。
				methodMap.remove(key);
				return true;
			}
		}
		return false;
	}
	
	//判断要测试的方法都在类中是否匹配到，都匹配到返回true，否则false
	public boolean isAllMatch() {
		//如果map为空了，说明所有方法都在类中匹配上了，否则认为方法在类中不存在，需要修正代码
		if(!methodMap.isEmpty()){
			for(String key : methodMap.keySet()){
				log.info("method \""+key+"\" is not match,please check suiteMap");
			}
			return false;
		}else{
			return true;	
		} 
	}

}
