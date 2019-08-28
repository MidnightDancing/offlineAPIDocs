package com.umpay.demo.step2_商户入件;

import java.util.Map;
import java.util.TreeMap;

import com.umpay.demo.step1_基础参数准备.EnvConfig;
import com.umpay.util.HttpUtilClient;
import org.junit.Test;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONObject;
import com.umpay.addmer.AddSign;

/**
 * @author: weijieming
 * @date:2019年8月21日 上午10:37:51
 * @类说明:查询电子协议接口
 * @产品号:
 */
public class QueryElectronicAgreementAPI {

	String url = EnvConfig.serverURL + "/entry-provider-plat-client/merchants/queryElectronicAgreement";

    //String url = "http://10.10.178.87:6904/merchants/queryElectronicAgreement";
    @Test
    public void queryOrder() throws Exception{
    	try {
    		TreeMap<String, Object> reqPay = new TreeMap<String, Object>();
            reqPay.put("acqSpId", "Y471790403");//代理商编号	10	M	代理商编号(联动平台分配)
            reqPay.put("merId", "M2019082000000271");//报备编号	17	M
            reqPay.put("signature", "");

            //对请求报文做加签处理
            String reqpay = AddSign.addSign(reqPay);
            Map<String, Object> reqMap = JSONObject.parseObject(reqpay);

            //发送post请求
            System.out.println("1111=============1111");
            String result = HttpUtilClient.doPostJson(url, new JSONObject(), reqMap);
            System.out.println("2222=============2222");
            System.out.println("输出请求结果:"+result);
            
            Assert.isTrue(true);	
    	} catch(Exception e ){
    		e.printStackTrace();
    		Assert.isTrue(false);	
    	}
        

    }



}
