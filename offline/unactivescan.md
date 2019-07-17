# 刷卡支付-用户被扫
    
**简要描述：** 

- 商户->联动优势

- 消费者通过微信/支付宝/银联钱包等出示付款码，商户通过扫码设备识别付款码并完成下单支付

- 请通过主动调用**3.4订单状态同步**接口来保证订单状态一致性

**请求URL：** 

`待定`
  
**请求方式：**

- POST 

**请求参数：** 


|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|:--------:|:--------:|:--------:|:--------:|:--------|
|	reqDate	|	请求日期	|	8	|	M	|	yyyyMMdd	|
|	reqTime	|	请求时间	|	6	|	M	|	HHmmss	|
|	merId	|	商户号	|	8	|	M	|	商户号(联动平台分配)	|
|	orderId	|	商户订单号	|	64	|	M	|	商户的支付订单号	|
|	amount	|	订单金额	|	10	|	M	|	单位:分	|
|	payType	|	支付类型	|	2	|	M	|	WX：微信刷卡支付 <br> AL：Alipay支付宝条码支付 <br> YL：银联二维码支付	|
|	mediaNo	|	付款码	|	32	|	M	|	|
|	goodsId	|	商品ID	|	16	|	M	|	商品ID	|
|	goodsInfo	|	商品信息	|	128	|	M	|	商品信息	|
|	notifyUrl	|	通知地址	|	256	|	O	| 	结果通知地址. 必须以 http:// 或 https:// 开始, 支持大小写字母,数字,'/','&','%','?','=' . 暂不支持通知地址中包含其他字符,包含url编码后的结果 如%3C %3E等	|
|	sign	|	签名	|	256	|	M	|	参见签名机制	|

 **商户请求报文示例**

```json

{
	"amount": "1",
	"orderId": "20180122LDYF00008940",
	"goodsId": "goods",
	"sign": "CBr2Dui55aRxyiUJoWCxckL8lWn7UeBxvAJFsV2hrtFDvVSOp4v4cgUPc1Nk3e1d+oitAhi9b3AAVSoAuEWV0fKKIQRwYTSPTzLbX9fLXq2KE423Km5GW5HWqpN8+guCH1UUpSlNVzVYax9h5D/n2YSWv/g6KWZYye+kEP8K3rA=",
	"reqTime": "105356",
	"merId": "30000064",
	"payType": "WX",
	"reqDate": "20180313",
	"mediaNo": "134919586213926735",
	"goodsInfo": "goods"
}

```

 **返回参数说明** 
 
|	字段	|	名称	|	长度	|	必填	|	说明	|
|--------|-------|--------|--------|--------|
|	retCode	|	返回码	|	8	|	M	|	返回码	|
|	retMsg	|	返回信息	|	128	|	M	|	返回信息	|
|	platDate	|	平台日期	|		|	M	|	平台日期   |
|	platTime	|	平台时间	|		|	M	|	平台时间   |
|	orderId	|	商户订单号	|	64	|	O	|		|
|	transactionId	|	联动订单号	|	32	|	M	|	联动优势的订单号|
|	paySeq	|	支付流水号	|	64	|	O	|	支付流水号，成功返回	|
|	amount	|	订单金额	|	10	|	M	|	订单金额 	|
|	sign	|	签名	|	256	|	M	|	参见签名机制	|
