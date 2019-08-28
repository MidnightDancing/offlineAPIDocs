package com.umpay.demo.step3_收款交易;

import java.util.TreeMap;

import com.umpay.util.TimeUtil;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.umpay.addmer.AddSign;
import com.umpay.common.Common;
import com.umpay.demo.step1_基础参数准备.EnvConfig;

/**
 * @author: zhangjing
 * @date:2019年8月6日 下午12:51:08
 * @类说明:被扫交易
 * @产品号:微信被扫，支付宝被扫，银联二维码被扫
 */
public class BeSweptAPI {

	public String url = EnvConfig.serverURL + "/entry-provider-plat-client/pay/micropay";

	@Test
	public void beSweptAPi() {
		try {
			TreeMap<String, Object> reqPay = new TreeMap<String, Object>();
			reqPay.put("orderTime", TimeUtil.datetime14());
			reqPay.put("acqSpId", "Y471790403");//代理商编号	10	M	代理商编号(联动平台分配)
			reqPay.put("acqMerId", "41509208");//商户号	8	M	商户号(联动平台分配)
			reqPay.put("orderNo", Common.genOrderId());//商户订单号	64	M	商户的支付订单号
			reqPay.put("txnAmt", "1");//交易金额	13	M	是人民币，且以分为单位
			reqPay.put("orderType", "alipay");//订单类型	12	M alipay-支付宝,wechat-微信支付 ,unionpay-银联二维码
			reqPay.put("goodsInfo", "aaa");//商品信息	128	O	可上送商品描述、商户订单号等信息，用户付款成功后会在微信账单页面展示
			reqPay.put("authCode", "284979573206911019");//付款码	32	M	05:微信刷卡：以10~15开头的长度18位的数字,06:支付宝条码：以25~30开头的长度为16~24位的数字,09:银联二维码：以62开头长度19位数字
			reqPay.put("goodsId", "123");//商品ID	16	M	商品ID
			reqPay.put("signature", "");
			
			String reqpay = AddSign.addSign(reqPay);
			JSONObject jsonObject1 =JSONObject.parseObject(reqpay);
			String result = Common.runJsonPost(url, jsonObject1,"UTF-8");
			System.out.println("输出请求结果:"+result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}
	
}
