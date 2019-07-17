# 公众号/小程序支付

**简要描述：** 
- 商户->联动优势
- 商户通过接口下单成功后，使用返回的预下单信息在微信公众号、微信小程序或支付宝服务窗中调起支付插件，消费者完成支付。调起支付插件方法参考如下：  

  微信：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=7_7  
  支付宝：https://docs.open.alipay.com/common/105591  
  
- 请通过主动调用**3.4订单状态同步**接口来保证订单状态一致性

**请求URL：**   

`https://mofunapi.umfintech.com/in-pay-client/api/unifiedcode`
  
**请求方式：**

- POST


**请求数据：** 

|	字段	|	名称	|	长度	|	必填	|   说明|	示例	|
| ---- |: ---- |:----- |:----- |:---- :|----- :|
|	reqDate	|	请求日期	|	8	|	M	|	yyyyMMdd	|
|	reqTime	|	请求时间	|	6	|	M	|	HHmmss	|
|	merId	|	商户号	|	8	|	M	|	商户号(联动平台分配)	|
|	orderId	|	商户订单号	|	64	|	M	|	商户的支付订单号	|
|	amount	|	订单金额	|	10	|	M	|	单位:分|	100|
|	payType	|	支付类型	|	2	|	M	|WX： 微信支付 </br> AL： 支付宝支付|	WX	|
|	openId	|	微信或支付宝用户标识	|	28	|	M	|微信上传用户openid；</br> 支付宝上传用户user_id；	|	微信openid：oUMnsvyNYu6pjDvpJzcXe3PvGXPY </br> 支付宝user_id：2088522626852000|
|	appId	|	APPID	|	18	|	C	|微信及支付宝的AppId，如获取OpenID所使用的AppID非下单商户主体资质，则该字段无需上传	|	wx102e43f81031d8d6|
|	sub_appid	|	子商户appid	|	18	|	O	|二级商户的appId	|	wx102e43f81031d8d6|
|	goodsInfo	|	商品信息	|	80	|	O	|可上送商品描述、商户订单号等信息，用户付款成功后会在微信账单页面展示|例：家政培训费（订单号2018080812635453）|
|	expairTime	|	订单有效时间(秒)	|	4	|	O	|当传递小于300秒或大于1800秒或不传递时系统默认为300秒。订单有效时间从调起用户密码键盘开始算起，超时之后,用户无法继续支付。	|600	|
|	notifyUrl	|	通知地址	|	256	|	O	|	结果通知地址. 必须以 http:// 或 https:// 开始,支持大小写字母,数字,'/','&','%','?','='. 暂不支持通知地址中包含其他字符,包含url编码后的结果 如%3C %3E等	| |
|	sign	|	签名	|	256	|	M	|参见签名机制	|	|

		


 **商户请求报文示例**
```json
{
	"merId": "30000102",
	"amount": "9",
	"payType": "WX",
	"orderId": "HSAPI1520425994612",
	"openId": "122010000151",
	"appId": "111",
	"sign": "FI8kw10QzZICpj2xRjsYcmU+HI7oNFm4KaNSPeT4YbmG7izCV4m9jZJQ1gxkny0bt5xY8MZXXtzFeRR5KEyzp2YFYMC0AFjvsd/5HGlE6JxrVKNg/LhIba7aR7WMrX4FtEcmBm4ILMosgVhf665KgGtdHBuCd5qRfAs217iPWd0="
}
```
 **返回参数说明** 


|字段|名称|长度|必填|说明|示例|
|---|---|---|---|---|---|
|	retCode	|	返回码	|	8	|	M	|0000支付成功	|	0000|
|	retMsg	|	返回信息	|	128	|	M	|	返回信息	|
|	platDate	|	平台日期	|		|	M	|	平台日期   |
|	platTime	|	平台时间	|		|	M	|	平台时间   |
|	payType	|	支付类型	|		|	M	|WX： 微信刷卡支付</br>AL: Alipay支付宝条码支付	|	WX|
|	transactionId	|	联动流水号	|	32	|	M	|	联动优势的流水号|
|	openId	|	用户授权标识	|		|	M	|		|122010000151|
|	appId	|	APPID	|		|	M	|	微信及支付宝的AppId|	2c91aa1843444d09a5a7ca811955bbe2|
|	prepayId	|	预支付ID	|		|	M	|调起支付插件需要	|	prepay_id=wx091722014693444a557c8f280689718193|
|	hostLsNo	|	交易流水号	|		|	M	|	|	8229172300153405|
|	timestamp	|	时间戳	|		|	C	|调起微信支付插件需要，支付宝可不填	|	1525857061537|
|	nonceStr	|	随机数	|		|	C	|调起微信支付插件需要，支付宝可不填	|	1525857061537|
|	signType	|	签名算法	|		|	C	|调起微信支付插件需要，支付宝可不填|		MD5|
|	paySign	|	H5签名	|		|	C	|调起微信支付插件需要，支付宝可不填	|	73DBF7459A11BCCED6D8F45EA660A416|
|	orderId	|	商户订单号	|		|	M	|同原请求	|180509171323918113335|
|	sign	|	签名	|	256	|	M	|参见签名机制	||

