package com.umpay.demo;

import java.util.HashMap;
import java.util.Map;

import com.umpay.demo.step2_商户入件.商户报备;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TestAllSuite1 extends BaseTestSuite{

	
	
	public Test suite() throws Exception {
	
		TestSuite suite = new TestSuite("开始执行全部测试用例！！！");
		//组装要测试的测试类
		Map<Class<?>, String> suiteMap = new HashMap<Class<?>, String>();
		suiteMap.put(商户报备.class, "allrun");
		suite = getTestSuite(suite,suiteMap);
		return suite;
		
		
	}
	
	
}
