# 用户主扫

**简要描述：** 

- 商户->联动优势
- 商户通过下单接口获取指定支付方式的二维码并展示给消费者，消费者使用微信/支付宝/银联钱包等扫码完成支付
- 请通过主动调用**3.4订单状态同步**接口来保证订单状态一致性

**请求URL：** 

`https://mofunapi.umfintech.com/in-pay-client/api/activescan`

**请求方式：**

- POST 

**请求报文参数：** 

|	字段	|	名称	|	长度	|	必填	|	说明	|
|--------|--------|--------|--------|--------|
|	reqDate	|	请求日期	|	8	|	M	|	yyyyMMdd	|
|	reqTime	|	请求时间	|	6	|	M	|	HHmmss	|
|	merId	|	商户号	|	8	|	M	|	商户号(联动平台分配)	|
|	orderId	|	商户订单号	|	64	|	M	|	商户的支付订单号	|
|	amount	|	付款金额	|	13	|	M	|	是人民币，且以分为单位	|
|	payType	|	支付类型	|	2	|	M	|	WX：微信刷卡支付<br>AL：Alipay支付宝条码支付<br>YL：银联二维码支付	|
|	goodsInfo	|	商品信息	|	128	|	O	|	可上送商品描述、商户订单号等信息，用户付款成功后会在微信账单页面展示	|
|	expairTime	|	订单有效时间(秒)	|	600	|	O	|	当传递小于300秒或大于1800秒或不传递时系统默认为300秒<br>订单有效时间从调起用户密码键盘开始算起，超时之后,用户无法继续支付。	|
|	notifyUrl	|	通知地址	|	256	|	O	| 	结果通知地址. 必须以 http:// 或 https:// 开始, 支持大小写字母,数字,'/','&','%','?','=' . 暂不支持通知地址中包含其他字符,包含url编码后的结果 如%3C %3E等	|
|	sign	|	签名	|	256	|	M	|	参见签名机制	|

 **商户请求报文示例**

```json
{
	"amount": "99999",
	"merId": "30000102",
	"payType": "WX",
	"reqDate": "20180313",
	"orderId": "HSAPI1520909695265",
	"sign": "MMokEDpwU7vHy2AP2o4esBBLHRdsn3BorIdSHkbGpEOEAr9USwj++l9K8lyder2Yy/WmtEhyEL9xKiX4mS14ds7OKdzX6tGzy4qc2lsdRRSe5l9I9Gj7NdCLsq1TUccr2gnGibvu9UaAsCUCNmJqBrSW0YUl7+mVND9FFGecBe0=",
	"reqTime": "105455"
}
```

 **返回参数说明** 

|	字段	|	名称	|	长度	|	必填	|	说明	|
|----|----|----|----|----|
|	retCode	|	返回码	|	8	|	M	|	返回码	|
|	retMsg	|	返回信息	|	128	|	M	|	返回信息	|
|	platDate	|	平台日期	|		|	M	|	平台日期   |
|	platTime	|	平台时间	|		|	M	|	平台时间   |
|	orderId	|	商户订单号	|	64	|	O	|		|
|	transactionId	|	联动流水号	|	32	|	M	|	联动优势的流水号|
|	paySeq	|	平台处理流水 	|		|	M	|	平台处理流水 	|
|	amount	|	付款金额	|	13	|	M	|	是人民币，且以分为单位	|
|	payType	|	支付类型	|		|	M	|	WX：微信刷卡支付<br>AL：Alipay支付宝条码支付<br>YL：银联二维码支付	|
|	feeUrl	|	缴费二维码地址	|		|	M	|	成功时返回内容，失败返回空字符串	|
|	orderId	|	商户订单号	|	64	|	O	|	支付订单号	|
|	sign	|	签名	|	256	|	M	|	参见签名机制	||
