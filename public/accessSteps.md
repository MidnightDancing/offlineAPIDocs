# 接入步骤

## 1.2.1下载所需工具包

​		下载所需的联调工具包，查看工具包中的readme文件，进行必要配置，运行代码示例查看效果。
        
|	环境	 |	文件说明	  |	下载  	|
|:--------:|:--------:|:--------:|
|	联调环境	|	测试demo以及联调测试数据说明	|	暂由开发提供	|
|	生产环境	|	测试demo以及生产测试数据获取说明	|	暂由开发提供	|


## 1.2.2  了解联动的开发规范

​		了解联动接口的签名机制以及验签机制：我司对应的产品接入规范中，对签名机制和验签都做了说明。以主扫为例，详见如下图：


## 1.3.3 交互说明

​	发起方拼接明文串。

​	发起方使用自己私钥进行签名生成请求密文串。

​	发起方发起接口调用请求。

​	接收方使用发起方公钥进行解密，并对请求进行响应。

​	接收方对响应信息使用平台私钥进行签名处理，并返回至发起方。

​	发起方使用平台公钥对签名进行验签，使用返回信息进行业务。

## 1.3.4 请求加签

【1】参数排序

​		对接口要求的业务参数的key进行排序，排序规则为字典序（A-Z顺序）

​		（注：此处不包括要求的签名参数，即key为signature的业务参数）

~~~json
```
{
    "acqMerId":"41509208",
    "acqSpId":"Y471790403",
    "funCode":"ALIVE",
    "orderNo":"a12ddasdasdad23sd",
    "rpid":"123456789"
}
```
~~~

【2】明文串拼接

​		将有序的业务参数按如下格式拼接为明文串待签

​		（key1=value1&key2=value2&key3=value3）

```http
eg:
acqMerId=41509208&acqSpId=Y471790403&funCode=ALIVE&orderNo=a12ddasdasdad23sd&rpid=123456789
```

【3】加签

​		对待签明文串进行加签，得到签名密文串。

```
eg:
BdA3TpMiw1Jxec0WVxU0EsTEtgKQeUwLhuK95BTjx6h/4MQmHP4Uig/hze2lo36iy29AcvvuKkyxpzxRtO6fxFPPyibwoXzrnKxIzplC9O2kcPjC3e25MHydgNr4p7FOLC500IitrnsJ+yPzegW2GC86NKEei3l6Qj02RRAkNRg=
```



【4】包装请求

​		将加签之后的签名字段放入请求的json中，key为“signature”，value为之前的签名串。

```json
{
    "acqMerId":"41509208",
    "acqSpId":"Y471790403",
    "funCode":"ALIVE",
    "orderNo":"a12ddasdasdad23sd",
    "rpid":"123456789",
  "signature":"BdA3TpMiw1Jxec0WVxU0EsTEtgKQeUwLhuK95BTjx6h/4MQmHP4Uig/hze2lo36iy29AcvvuKkyxpzxRtO6fxFPPyibwoXzrnKxIzplC9O2kcPjC3e25MHydgNr4p7FOLC500IitrnsJ+yPzegW2GC86NKEei3l6Qj02RRAkNRg="
}

```



## 1.3.5 验签

【1】解析回返json串

​		解析返回结果，识别其中各字段

```json
{
    "respCode": "00",
    "respMsg": "处理成功",
    "signature": "ggIIULxcVDrLZzM2Xl0+aMRKlv4VgoZNw/eRWTHdn2w1YFcuoGjq23AadIezOMftMvYIvJ/m5P5X2z2+vsSm71vnnsKjgHsBdzSxRVQiYgS4IJg7vNNHEb1vbZY/LulqSFHwDtPp1yHg9syw8/qyFP6dAmBqO27wsLIGrJJvrPU=",
    "platDate": "",
    "txnAmt": 1,
    "paySeq": "",
    "transactionId": "2019072518100000000001",
    "origRespCode": null,
    "origRespDescCode": "99"
}
```

【2】获取签名串

​		从返回字段中取出签名串字段，备用。

```
"signature": "ggIIULxcVDrLZzM2Xl0+aMRKlv4VgoZNw/eRWTHdn2w1YFcuoGjq23AadIezOMftMvYIvJ/m5P5X2z2+vsSm71vnnsKjgHsBdzSxRVQiYgS4IJg7vNNHEb1vbZY/LulqSFHwDtPp1yHg9syw8/qyFP6dAmBqO27wsLIGrJJvrPU="
```

【3】获取响应结果的待加签明文串

​		返回字段中将除签名串外其他字段全部排序，按字典序排序，将值不为空的各字段（“”或null）按如下规则进行拼接签名串，获得待加签明文串。

​		规则：value1|value2|value3

```
eg:
99|00|处理成功|2019072518100000000001|1
```

【4】响应结果加签

​		将【3】中获取的待加签明文串使用联动公钥进行加签，获得加签后的验签密文串。

```
eg:
ggIIULxcVDrLZzM2Xl0+aMRKlv4VgoZNw/eRWTHdn2w1YFcuoGjq23AadIezOMftMvYIvJ/m5P5X2z2+vsSm71vnnsKjgHsBdzSxRVQiYgS4IJg7vNNHEb1vbZY/LulqSFHwDtPp1yHg9syw8/qyFP6dAmBqO27wsLIGrJJvrPU=
```



【5】验签

​		比对加签后的验签密文串和响应的签名串是否一致，若一致则直接使用其他字段值进行后续操作，若不一致说明消息被篡改。

## 1.3.6 加签demo示例

```java
		TreeMap<String, Object> reqMap =new TreeMap<String, Object>();
		reqMap.put("orderNo","12345678987654321");
		reqMap.put("orderTime","20190725141912");
		reqMap.put("orderType","alipay");
		reqMap.put("txnAmt","1");
		
		reqMap.put("backUrl","http://www.baidu.com");
		reqMap.put("goodsInfo","一个月优酷会员");
		reqMap.put("paymentValidTime","600");
		
		reqMap.put("acqSpId","Y471790403");
		reqMap.put("acqMerId","41509208");

		
	    reqMap.remove("signature");
		//获取原请求签名
	    String signStr	= new StringBuilder(reqMap.toString().replace(", ", "&").replace("{", "").replace("}", "")).toString();
		// 验签
		//【待签名明文串】--signStr
		String sign = CertUtils.sign(signStr, CertUtils.getPrivateKey("D:/test.key.p8"),"UTF-8");
		//【签名密文串】--sign
		reqMap.put("signature", sign);
		//【最终报文串】--res
		String res=JSON.toJSONString(reqMap);

```

## 1.3.7 验签demo

```java
public static boolean doCheckSign(String object) {
		Map<String, Object> treeMap = JSON.parseObject(JSON.toJSONString(JSON.parse(object)), TreeMap.class);
		// 【响应的签名】
		String signKey = (String) treeMap.get("signature");
		treeMap.remove("signature");
		// 【待签明文串】--去除响应签名后获取待签明文串
		StringBuilder sb = new StringBuilder();
		for (Entry<String, Object> entry : treeMap.entrySet()) {
			if (null != treeMap.get(entry.getKey()) && !"".equals(treeMap.get(entry.getKey()))) {
				sb.append(treeMap.get(entry.getKey())).append("|");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		String befSgin = sb.toString();
		//【验签】
		boolean signresult = false;
		try {
			signresult = SignTools.doCheckSign(certFilePath, serverCertFileName, befSgin, signKey);
		} catch (Exception e) {
			System.out.println("验签异常");
		}
		//【 验签结果】
		return signresult;
	}
```



## 1.3.8 注意事项

【1】字段顺序

字段顺序必须是按字典序升序排列，否则验签会失败。

【2】加签字段

参与签名字段必须是接口要求字段，不能多冗余字段，否则验签失败。

【3】必传字段

如果缺少必传字段，验签会失败
