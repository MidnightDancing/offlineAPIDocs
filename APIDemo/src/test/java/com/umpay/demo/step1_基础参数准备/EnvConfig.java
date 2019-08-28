package com.umpay.demo.step1_基础参数准备;

import org.junit.Assert;
import org.junit.Test;

public class EnvConfig {
	public static String serverURL = "http://106.120.215.230:8011/";
	public static String certFileRootPath = ".\\target\\classes\\cert\\";

    @Test
	public void testInitEnvironment(){

		System.out.println("需要设置服务器url");

		System.out.println("需要设置服务器公钥");

		System.out.println("需要设置商户私钥");

		Assert.assertTrue( true );
	}
}
