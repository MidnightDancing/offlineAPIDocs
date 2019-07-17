# 用户被扫

    
**简要描述：** 

- 商户->联动优势
- 消费者通过微信/支付宝/银联钱包等出示付款码，商户通过扫码设备识别付款码并完成下单支付
- 如微信/支付宝客户端打开密码键盘，则需要调用**2.6订单状态同步**来查询订单最终状态
- 交易时序图如下
![](../images/unactivescan.png)

**请求URL：** 
`https://mofunapi.umfintech.com/in-pay-client/api/unactivescan`
  
**请求方式：**
- POST 

**请求参数：** 


|	字段	|	名称	|	长度	|	必填	|	说明	|
|----|----|----|----|----|
|	partnerOrderId	|	商户订单号	|	32	|	M	|	商户的支付订单号	|
|	subMerId	|	商户号	|	8	|	M	|	商户号(联动平台分配)	|
|	amount	|	订单金额	|	10	|	M	|	单位:分	|
|	payType	|	支付类型	|	2	|	M	|	WX：微信刷卡支付<br>AL：Alipay支付宝条码支付	<br>YL：银联二维码|
|	mediaType	|	媒介类型	|	2	|	M	|	05:微信刷卡<br>06:支付宝条码<br>09:银联二维码	|
|	mediaNo	|	媒介编号	|	32	|	M	|	05:微信刷卡：以10\~15开头的长度18位的数字<br>06:支付宝条码：以25\~30开头的长度为16~24位的数字<br>09:银联二维码：以62开头长度19位数字	|
|	goodsId	|	商品ID	|	16	|	M	|	商品ID	|
|	goodsInfo	|	商品信息	|	128	|	M	|	商品信息	|
|	proxyId	|	集成商ID	|	4	|	M	|	集成商唯一标识（同魔方平台登录账号）	|
|	apiVersion	|	版本	|	3	|	M	|	定值:1.0	|
|	reqDate	|	请求日期	|	8	|	M	|	yyyyMMdd	|
|	reqTime	|	请求时间	|	6	|	M	|	HHmmss	|
|	sign	|	签名	|	256	|	M	|	参见签名机制	|
|	scene	|	场景	|	32	|	C	|	条码支付:  bar_code（支付宝必传）	|
|	subject	|	订单标题	|	256	|	C	|	订单标题 （支付宝必传）	|
|	disAmount	|	可打折金额	|	11	|	O	|	可打折金额（支付宝非必传）	|
|	undisAmount	|	不可打折金额	|	11	|	O	|	不可打折金额（支付宝非必传）	|
|	shopNo	|	门店编号	|		|	O	|	门店编号	|
|	operator	|	收银员	|		|	O	|	收银员账号	|
|	posSN	|	POSSN	|		|	O	|	POS唯一标识，可用于关联UPOS	|
|	notifyUrl	|	通知地址	|	256	|	O	| 	结果通知地址. 必须以 http:// 或 https:// 开始, 支持大小写字母,数字,'/','&','%','?','=' . 暂不支持通知地址中包含其他字符,包含url编码后的结果 如%3C %3E等	|
 **商户请求报文示例**

```json
context={
	"amount": "1",
	"partnerOrderId": "20180122LDYF00008940",
	"goodsId": "goods",
	"sign": "CBr2Dui55aRxyiUJoWCxckL8lWn7UeBxvAJFsV2hrtFDvVSOp4v4cgUPc1Nk3e1d+oitAhi9b3AAVSoAuEWV0fKKIQRwYTSPTzLbX9fLXq2KE423Km5GW5HWqpN8+guCH1UUpSlNVzVYax9h5D/n2YSWv/g6KWZYye+kEP8K3rA=",
	"mediaType": "05",
	"reqTime": "105356",
	"subMerId": "30000064",
	"payType": "WX",
	"apiVersion": "1.0",
	"reqDate": "20180313",
	"mediaNo": "134919586213926735",
	"goodsInfo": "goods",
	"proxyId": "0001"
}
```

 **返回参数说明** 
 
|	字段	|	名称	|	长度	|	必填	|	说明	|
|----|----|----|----|----|
|	retCode	|	返回码	|	8	|	M	|	返回码	|
|	Memo	|	返回信息	|	128	|	M	|	返回信息	|
|	paySeq	|	支付流水号	|	64	|	O	|	支付流水号，成功返回	|
|	payDay	|	支付日期	|	8	|	M	|	支付日期	|
|	amount	|	订单金额	|	10	|	M	|	订单金额 (打印小票使用)	|
|	coupAmount	|	优惠金额	|	10	|	M	|	优惠金额 单位:分	|
|	payAmount	|	实际支付金额	|	10	|	M	|	实际支付金额：单位:分	|
|	tradeNo	|	平台处理流水 	|	16	|	M	|	平台处理流水（对账及打印小票使用）	|
|	platDate	|	平台日期	|	8	|	M	|	平台日期（对账,退款及打印小票使用）	|
|	platTime	|	平台时间	|	6	|	M	|	平台时间HHmmss（打印小票使用）	|
|	partnerOrderId	|	商户订单号	|	32	|	M	|	订单号	|
|	sign	|	签名	|	256	|	M	|	参见签名机制	|


 **备注** 
- 更多返回错误代码请看[全局参数说明](单页面地址 : https://www.showdoc.cc/web/#/page/39370587223487 "全局参数说明")
