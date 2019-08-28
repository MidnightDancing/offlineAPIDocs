package com.umpay.demo.step6_撤销退费关闭;

import com.alibaba.fastjson.JSONObject;
import com.umpay.addmer.AddSign;
import com.umpay.demo.step1_基础参数准备.EnvConfig;

import com.umpay.util.HttpUtilClient;
import junit.framework.Assert;

import org.junit.Test;
//import org.testng.Assert;

import java.util.Map;
import java.util.TreeMap;

/**
 * description: 关闭订单测试类
 * author: zhanghuanqi
 * date: 2019/8/19
 * time: 下午4:27
 */
public class OrderCloseAPI {
	
	
	public String url = EnvConfig.serverURL + "entry-provider-plat-client/pay/closeOrder";
	
	@Test
	public void orderClose() {
		try {
			TreeMap<String, Object> reqPay = new TreeMap<String, Object>();
			reqPay.put("acqSpId", "Y471790403");//代理商编号	10	M	代理商编号(联动平台分配)
			reqPay.put("acqMerId", "41509208");//商户号	8	M	商户号(联动平台分配)
			reqPay.put("orderNo", "JD201908190426430001");//商户订单号	64	M	商户的支付订单号
			reqPay.put("signature", "");
			
			//对请求报文做加签处理
			String reqpay = AddSign.addSign(reqPay);
			Map<String, Object> reqMap = JSONObject.toJavaObject(JSONObject.parseObject(reqpay), Map.class);

			//发送post请求
			String result = HttpUtilClient.doPostJson(url, new JSONObject(), reqMap);
			System.out.println("输出请求结果:"+result);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

}
