package com.umpay.demo;
import com.umpay.demo.step1_基础参数准备.EnvConfig;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.umpay.demo.step2_商户入件.商户报备;
import com.umpay.demo.step2_商户入件.ImageUploadTest;
import com.umpay.demo.step2_商户入件.MerInfoQueryAPI;





@RunWith(Suite.class)
@Suite.SuiteClasses({
		EnvConfig.class,
	商户报备.class,
	ImageUploadTest.class,
	MerInfoQueryAPI.class})




public class TestAllSuite2 {

}
