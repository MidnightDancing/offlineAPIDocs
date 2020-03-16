# 订单状态同步

**简要描述：** 
- 平台对服务商的支付请求数据处理完成后，响应为非明确状态。如：“01000000”返回码的情况下，需要调用同步接口，请求通道查询最终结果。

**请求URL：** 
- 服务商->联动优势
`{交易服务根地址}/pay/queryOrder`

**请求方式：**
- POST 

**请求参数：** 

|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|:--------:|:--------:|:--------:|:--------:|:--------|
|	acqSpId	|	代理商编号	|	10	|	M	|	代理商编号(联动平台分配)	|
|	acqMerId	|	商户号	|	8	|	M	|	商户号(联动平台分配)	|
|	orderNo	|	商户订单号	|	64	|	M	|	原来请求订单号	  |
|	transactionId	|	联动订单号	|	32	|	O	|	联动优势的订单号，建议优先使用	|
|	signature	|	签名	|	256	|	M	|	参见签名机制	|

 **商户请求报文示例**

```json
{
	"acqMerId": "41509208",
	"acqSpId": "Y471790403",
	"orderNo": "JD201909250317100002",
	"signature": "A973LB9jCo30ckA6ILL2hChhdUbt4krFoJlcFNufox7RN22Hmujey75bbvR6MQ/1U3q/Um/ivCaD45U3+xvka53gHnQPvz7q0d+R+RXA7BYdwnTjFgk883AjvMi5EM75ivHZA2Fua3SWDmSTTrlPGfEGxkqfaMnThLsYZYzri0U="
}
```

 **返回参数说明** 

|	字段	|	名称	|	长度	|	必填	|	说明	|
|--------|--------|--------|--------|--------|
|	respCode	|	返回码	|	8	|	M	|	返回码	|
|	respMsg	|	返回信息	|	128	|	M	|	返回信息	|
|	origRespCode	|	交易状态	|		|	C	| 00—明确支付成功 <br>01—转入退款 <br>02—交易结果未明 <br> 03- 已关闭 <br> 04- 已撤销(付款码支付) <br>06--明确支付失败(其他原因，如银行返回失败) |
|	origRespDescMsg	|	交易详细状态码	|	|	C	| 支付成功(0) <br>支付失败(1) <br>被撤销(2)<br>被退货(3)<br>被冲正(4)<br>被确认(5)<br>支付中(6)<br>无交易(7)<br>未明(8)<br>异常(9)<br>其他(99)<br>订单关闭(98)<br> |
|	platDate	|	平台日期	|		|	M	|	平台日期   |
|	payTime	|	交易时间	|	|	C	|	交易时间  格式：MMDDhhmmss |
|	txnAmt	|	订单金额	|		|	C	|	订单金额 (打印小票使用)	|
|	paySeq	|	支付流水号	|		|	C	|	支付流水号（条形码），成功返回	|
|	transactionId	|	联动流水号	|	32	|	M	|	联动优势的流水号|
|	signature	|	签名	|	256	|	M	|	参见签名机制	|
|	depBankSeq	|	第三方流水号	|		|	O	|	第三方流水号（微信/支付宝/银联的交易流水号）	|
|	isClosed	|	订单关闭标识	|		|	O	|	订单关闭标识（版本过期字段，不建议使用，后期删除）	|
|	merPriv	|	商户私有域	|		|	O	|	商户私有域	|
|	bankCardType	|	银行卡类型	|		|	O	|	1：借记卡；2：信用卡	|
|	payerInfo	|	支付信息	|		|	O	|	商户私有域	|

 **返回报文示例**

```json
{
    "respCode": "00",
    "respMsg": "处理成功",
    "signature": "lkD1PuEv/tb+f1JNWRAC8EHIStWi/smtpCZBXRYwbHJsO4Rt+wzNMtal4apAqvQqH8hVgJLJF7OxLby4pwTdfcbAfuOJQ8MR4K8oWoBXkeLIFKQJhSda3qqxHtZBVz5d0OGsqxgdNs0oSIC44W5Ep2TXkTGcopDfi8K+mi2v5es=",
    "platDate": "20190925",
    "payTime": "0925152411",
    "txnAmt": 1,
    "paySeq": "9268151710507213",
    "transactionId": "2019092515170000002850",
    "origRespCode": "00",
    "origRespDescMsg": "支付成功",
    "isClosed": "",
    "depBankSeq": "4200000395201909251384927907"
}
```



**备注** 

- 除了成功（00）的返回码需要做对应的逻辑处理，其他返回码均不需要做处理
- 返回码为成功（00），根据交易状态origRespCode判断交易是否成功。
- 原单为转入退费，请调用退费查询接口
