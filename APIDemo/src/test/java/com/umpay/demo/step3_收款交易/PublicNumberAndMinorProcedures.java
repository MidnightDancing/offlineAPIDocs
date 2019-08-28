package com.umpay.demo.step3_收款交易;

import java.util.Map;
import java.util.TreeMap;

import com.umpay.util.TimeUtil;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.umpay.addmer.AddSign;
import com.umpay.common.Common;
import com.umpay.demo.step1_基础参数准备.EnvConfig;

/**
 * @author: zhangjing
 * @date:2019年8月6日 下午2:21:05
 * @类说明:公众号/小程序
 * @产品号:微信 Js 支付,支付宝 Js 支付
 */
public class PublicNumberAndMinorProcedures {
	
	public String url = EnvConfig.serverURL + "/pay/unifiedOrder";

	@Test
	public void beSweptAPi() {
		try {
			TreeMap<String, Object> reqPay = new TreeMap<String, Object>();
			reqPay.put("orderTime", TimeUtil.datetime14());
			reqPay.put("acqSpId", "Y471790403");//代理商编号	10	M	代理商编号(联动平台分配)
			reqPay.put("acqMerId", "41509208");//商户号	8	M	商户号(联动平台分配)
			reqPay.put("orderNo", Common.genOrderId());//商户订单号	64	M	商户的支付订单号
			reqPay.put("txnAmt", "1");//交易金额	13	M	是人民币，且以分为单位
			reqPay.put("orderType", "wechatJs");//订单类型	12	M	wechatJs:微信 Js 支付  ，alipayJs:支付宝 Js 支付
			reqPay.put("goodsInfo", "aaa");//商品信息	128	O	可上送商品描述、商户订单号等信息，用户付款成功后会在微信账单页面展示
			reqPay.put("userId", "oUMnsvyNYu6pjDvpJzcXe3PvGXPY");//用户标识	28	M	微信上传用户openid；支付宝上传用户buyer_id；
			reqPay.put("appId", "wx740a066b0a94fa6d");//APPID	18	C	微信及支付宝的AppId，如获取OpenID所使用的AppID非下单商户主体资质，则该字段无需上传
			reqPay.put("subAppId", "");//子商户appid	18	O	二级商户的appId
			reqPay.put("paymentValidTime", "300");//订单有效时间(秒)	4	O	当传递小于300秒或大于1800秒或不传递时系统默认为300秒。订单有效时间从调起用户密码键盘开始算起，超时之后,用户无法继续支付。
			reqPay.put("backUrl", "http://www.baidu.com");//通知地址	256	O	结果通知地址. 必须以 http:// 或 https:// 开始,支持大小写字母,数字,'/','&','%','?','='. 暂不支持通知地址中包含其他字符,包含url编码后的结果 如%3C %3E等
			reqPay.put("signature", "");
			
			String reqpay = AddSign.addSign(reqPay);
			JSONObject jsonObject1 =JSONObject.parseObject(reqpay);
			String result = Common.runJsonPost(url, jsonObject1,"UTF-8");
			System.out.println("输出请求结果:"+result);
			
			//将响应报文转成map
			Map<String, Object> treeMap = JSON.parseObject(result, TreeMap.class);		
			Assert.assertEquals(treeMap.get("respCode"), "00");
			Assert.assertEquals(treeMap.get("respMsg"), "处理成功");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}
}
