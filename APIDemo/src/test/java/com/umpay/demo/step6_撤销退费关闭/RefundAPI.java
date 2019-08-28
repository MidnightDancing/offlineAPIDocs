package com.umpay.demo.step6_撤销退费关闭;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.umpay.addmer.AddSign;
import com.umpay.common.Common;
import com.umpay.demo.step1_基础参数准备.EnvConfig;
import com.umpay.util.HttpUtilClient;

import java.util.Map;
import java.util.TreeMap;

import com.umpay.util.TimeUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * description: 退费接口测试类
 * author: zhanghuanqi
 * date: 2019/8/19
 * time: 下午3:59
 */
public class RefundAPI {
	//public String queryUrl ="http://106.120.215.230:8011/entry-provider-plat-client/pay/refund";
	public String url = EnvConfig.serverURL + "/pay/refund";
	

	@SuppressWarnings("unchecked")
	@Test
	public void refund() throws Exception{
		try {
			TreeMap<String, Object> reqPay = new TreeMap<String, Object>();
			reqPay.put("orderTime", TimeUtil.datetime14());//订单时间	16	M	yyyyMMddHHmms
			reqPay.put("acqSpId", "Y471790403");//代理商编号	10	M	代理商编号(联动平台分配)
			reqPay.put("acqMerId", "41509208");//商户号	8	M	商户号(联动平台分配)
			reqPay.put("origOrderNo", "JD201908190402060001");//原单流水号	64	M	原交易的订单号
			reqPay.put("origTxnAmt", "1");//原单交易金额	13	M	单位:原交易的总金额
			reqPay.put("orderNo", Common.genOrderId());//退款流水号	64	M	本次退货交易的订单号
			reqPay.put("txnAmt", "1");//退款金额	13	M	本次退货的金额
			reqPay.put("signature", "");
			
			//对请求报文做加签处理
			String reqpay = AddSign.addSign(reqPay);
			Map<String, Object> reqMap = JSONObject.toJavaObject(JSONObject.parseObject(reqpay), Map.class);

			//发送post请求
			String result = HttpUtilClient.doPostJson(url, new JSONObject(), reqMap);
			System.out.println("输出请求结果:"+result);
			
			Map<String, Object> treeMap = JSON.parseObject(result, TreeMap.class);		
			Assert.assertEquals("00", treeMap.get("respCode"));
			Assert.assertEquals("处理成功",treeMap.get("respMsg"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
		
	}

}
