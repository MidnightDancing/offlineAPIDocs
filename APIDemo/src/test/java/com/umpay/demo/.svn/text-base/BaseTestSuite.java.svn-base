package com.umpay.demo;

import java.util.Map;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import junit.framework.JUnit4TestAdapter;
import junit.framework.TestSuite;

public class BaseTestSuite {
	private static final Logger log = LoggerFactory.getLogger(BaseTestSuite.class);
	public static TestSuite getTestSuite(TestSuite suite, Map<Class<?>, String> suiteMap) throws Exception {
		// 测试某个类
		JUnit4TestAdapter testAdapter;
	     //通过过滤器，添加基础测试到TestSuite中
        for(Class<?> key : suiteMap.keySet()){
                 testAdapter = new JUnit4TestAdapter(key);
                 //如果方法为空，则报错
                 if("".equals(suiteMap.get(key).trim())||suiteMap.get(key).trim()==null||suiteMap.get(key).trim().length()==0){
                	 throw new Exception("suiteMap中没有指定"+key+"中的要执行的测试方法，请检查suiteMap代码");
                 }else{
                	 //如果方法不是allrun字样，则该类执行指定方法，否则该类的所有方法都执行
                	 if(!"allrun".equals(suiteMap.get(key).trim())){
                    	 try {
                    		BaseTestFilter filter = new BaseTestFilter(suiteMap.get(key).trim());
							testAdapter.filter(filter);
							//判断要执行的方法是否都在类中匹配到，如果有多余的方法没匹配到，则报错提示
							if(!filter.isAllMatch()){
								throw new Exception("suiteMap中"+key+"要执行的部分测试方法在"+key+"中未找到，请检查suiteMap代码");
							}
						} catch (NoTestsRemainException e) {
							//如果所有方法都没有在类中匹配到，则junit框架会报错，提示信息不全，捕获后打印提示在抛出：没有测试方法可以被执行的异常
							e.printStackTrace();
							throw new Exception("suiteMap中要执行的"+key+"的测试方法都不在"+key+"中，请检查suiteMap代码");
						}
                     }else{
                    	//如果方法不是allrun字样，则该类执行指定方法，否则该类的所有方法都执行
                    	 log.info("All methods should be run...");
                     }
                	 
                 }
                 suite.addTest(testAdapter);
        }
		return suite;
	}	
	
}
