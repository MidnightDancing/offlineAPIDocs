package com.umpay.demo.step2_商户入件;

import java.util.Map;
import java.util.TreeMap;

import com.umpay.util.HttpUtilClient;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.umpay.addmer.AddSign;
import com.umpay.demo.step1_基础参数准备.EnvConfig;

/**
 * @author: zhangjing
 * @date:2019年8月6日 下午4:40:51
 * @类说明:商户查询接口
 * @产品号:
 */
public class MerInfoQueryAPI {
	public String url = EnvConfig.serverURL + "/entry-provider-plat-client/merchants/queryMerchantInfo";
	

	@Test
	public void queryOrder() {
		try {
			TreeMap<String, Object> reqPay = new TreeMap<String, Object>();
			reqPay.put("acqSpId", "Y471790403");//代理商编号	10	M	代理商编号(联动平台分配)
			//reqPay.put("acqMerId", "42515801");//商户编号	8	C	merId和acqMerId至少存在一个
			reqPay.put("merId", "M2019082000000270");//报备编号	20	C	merId和acqMerId至少存在一个M2019080900000066
			reqPay.put("signature", "");	
			
			//对请求报文做加签处理
			String reqpay = AddSign.addSign(reqPay);
//		Map<String, Object> reqMap = JSONObject.toJavaObject(JSONObject.parseObject(reqpay), Map.class);
			Map<String, Object> reqMap = JSONObject.parseObject(reqpay);
			
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
