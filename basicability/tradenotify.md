# 交易结果通知

**简要描述：** 
- 联动优势把交易结果同步通知给服务商
- 联动优势最多通知商户4次，每次间隔1分钟

**请求URL：** 
- 联动优势->服务商
`{商户接收通知地址}`以交易时传入的通知地址{backUrl}为准

**请求方式：**

- POST 

**请求报文参数：** 联动通知商户数据

|   字段   |      名称      | 长度 | 必填 | 说明                               |
| :------: | :------------: | :--: | :--: | :--------------------------------- |
| acqSpId  |   代理商编号   |  10  |  M   | 代理商编号(联动平台分配)           |
| acqMerId |     商户号     |  8   |  M   | 商户号(联动平台分配)               |
| orderNo  |   商户订单号   |  64  |  M   | 商户的支付订单号 |
|  txnAmt  |    订单金额    |  13  |  M   | 单位（分）                         |
|currencyCode|    交易币种  |  6   |  C   | 交易币种  (CNY：人民币)      |
|transIndex|    交易索引    |  64  |  C   | 交易索引                           |
| payerInfo|    付款方信息  |  64  |  C   |                          |
|  payAmt  |    买家付款金额|  13  |  C   | 单位（分）                         |
|settlementAmt| 应结订单金额|  13  |  C   | 单位（分）                         |
|  payTime |    交易支付时间|  16  |  C   | yyyyMMddHHmmss                     |
|couponInfo|    优惠信息    |  128 |  C   |                          |
|dctGoodsInfo| 单品优惠信息 |  128 |  C   |                          |
|transactionId|第三方支付订单号|32 |  C   | 联动支付流水号（条形码）      |
|bankType  |   付款银行     |   16 |  C   |                          |
|cardType  |   银行卡类型   |  2   |  C   |01 – 借记卡；02 – 贷记卡  |
|notifyType  |   通知类型   |  2   |  C   |1 – 消费；2 – 撤销；3 – 退款  |
|merPriv  |   商户私有域   |  128   |  C   |商户私有域  |
|orderType  |   订单类型   |  12   |  C   | alipay – 支付宝；wechat – 微信支付；unionpay – 银联二维码；mobilepos-手机pos；|
|sign  |   签名   |  256   |  C   |  |

 **商户请求报文示例**

```application/x-www-form-urlencoded
{
	"acqMerId": "30000102",
	"acqSpId": "Y000000001",
	"orderNo": "HSAPI1520425994612",
	"txnAmt": "1",
	"currencyCode": "",
	"transIndex": "",
	"payerInfo": "",
	"payAmt": "",
	"settlementAmt": "",
	"payTime": "",
	"couponInfo": "",
	"dctGoodsInfo": "",
	"transactionId": "",
	"bankType": "",
	"cardType": ""
}
```

 **返回参数说明：**商户返回数据

| 字段      | 名称     | 长度 | 必填 | 说明                                 |
| --------- | -------- | ---- | ---- | ------------------------------------ |
| respCode  | 返回码   | 8    | M    | 00：收到通知<br />其他：继续重复通知 |
| respMsg   | 返回信息 | 128  | M    | 返回信息                             |
| signature | 签名     | 256  | M    | 参见签名机制                         |

**常见问题**：

【问题】：为什么会快速收到多次交易结果通知？

【答案】：服务商返回的返回码不是00就会马上再次通知。如果服务商没有响应，在一分钟之内会进行重复通知，如果服务商返回00就不会再次通知了。



【问题】：哪些交易会发送通知？

【答案】：所有下单交易都会发送通知（3.1/3.2/3.3接口）。



【问题】：为什么交易成功收不到通知？

【答案】：

1、下单直接支付成功不会发送通知（例如被扫免密支付）；

2、用户支付成功后代理商主动调用3.4订单同步接口返回交易成功；

3、3.2接口中微信被扫，下单超过10分钟以上才支付成功不会发送通知。



【问题】：什么时候发送通知？

【答案】：正式环境，用户支付成功5s内会下发通知。



【问题】：是否会收到多笔交易结果通知？

【答案】：是。需要服务商进行防重处理。

1、联动提供自动差补能力，如正向交易通知延迟，会一定概率存在差补交易通知和正向交易交易通知重复发送的情况。

2、不排除由于网络延时原因，在商户响应00之后，仍接到通知的情况。





**接收说明**

【接收格式】HTTP   POST

【接收示例】servlet（JAVA版本）

```java
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("*************************【param接口】收到POST请求信息***start**************************");
        //解析接收到参数
		System.out.println("**【param数据】*********start***********");
		Enumeration<String> en = request.getParameterNames();
		while(en.hasMoreElements()) {
			String key = en.nextElement();
			System.out.println("**【param数据】[key]=["+key+"]  [value]=["+URLDecoder.decode(request.getParameter(key))+"]");
		}
		System.out.println("**【param数据】*********end***********");
        //解析组织返回参数
		JSONObject json = new JSONObject();
		json.put("respCode", "00");
		json.put("respMsg", "收到");
		response.setContentType("application/json; charset=utf-8");
		System.out.println("**【json数据】回复响应结果："+JSONUtil.toJsonStr(json));
	    response.getWriter().write(JSONUtil.toJsonStr(json));
		System.out.println("*************************【param接口】收到POST请求信息***end**************************");
	}                                                                                                                          
```

【示例对应日志】

```verilog
//示例日志打印如下
 *************************【param接口】收到POST请求信息***start**************************
 **【param数据】*********start***********                                            
 **【param数据】[key]=[sign]  [value]=[cv0CbgyZC/QUkGwiSDrqcm8c8JdI6lnE+yT6RPvoFyYbKYAKJkxdhsrtXQDklpJ3Uq7ZSFB7o0M9MtFkzla5pv3fzCwt9WkRxaqBNzYiDYLuvSUaCtWUPHWGBn8pPeDnnvdxUla9jNZdp60FtpKO9+Oxo2xny8+QoND5ZKEGJEY=]
 **【param数据】[key]=[transactionId]  [value]=[]
 **【param数据】[key]=[currencyCode]  [value]=[CNY] 
 **【param数据】[key]=[orderNo]  [value]=[JD202003180342460001] 
 **【param数据】[key]=[acqSpId]  [value]=[Y471790403] 
 **【param数据】[key]=[acqMerId]  [value]=[41509208]  
 **【param数据】[key]=[notifyType]  [value]=[3]   
 **【param数据】[key]=[transIndex]  [value]=[2020031815420000024575]
 **【param数据】[key]=[merPriv]  [value]=[]    
 **【param数据】[key]=[payTime]  [value]=[20200927111534]     
 **【param数据】[key]=[cardType]  [value]=[02]     
 **【param数据】[key]=[payerInfo]  [value]=[]   
 **【param数据】[key]=[txnAmt]  [value]=[1]    
 **【param数据】*********end***********   
 **【json数据】回复响应结果：{"respMsg":"收到","respCode":"00"}  
 *************************【param接口】收到POST请求信息***end**************************   
```

