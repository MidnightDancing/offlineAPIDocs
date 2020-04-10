# 查询D0结果

**简要描述：** 
- 平台对服务商（开通D0商户）的支付请求成功数据。可以调用查询实时结算结果接口，查询实时结算结果。

**请求URL：** 
- 服务商->联动优势
`{交易服务根地址}/pay/querySettle`

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
|	settleRes	|	结算结果	|	|	C	|	结算结果；00：结算成功，02：结算处理中，稍后查询，其他结算失败 |
|	settleDate	|	结算日期	|		|	C	|	结算日期（格式：yyyyMMdd）	|
|	settleAmt	|	结算金额	|		|	C	|	结算金额	|

 **返回报文示例**

```json
{
    "respCode": "00",
    "respMsg": "处理成功",
    "signature": "lkD1PuEv/tb+f1JNWRAC8EHIStWi/smtpCZBXRYwbHJsO4Rt+wzNMtal4apAqvQqH8hVgJLJF7OxLby4pwTdfcbAfuOJQ8MR4K8oWoBXkeLIFKQJhSda3qqxHtZBVz5d0OGsqxgdNs0oSIC44W5Ep2TXkTGcopDfi8K+mi2v5es=",
    "settleRes": "00",
    "settleDate": "20200410",
    "settleAmt": 1
}
```



**备注** 

- 返回码除了成功（00）需要做对应的逻辑处理，其他返回码均不需要做处理
- 返回码为成功（00），根据结算结果settleRes判断结算是否成功。
- 结算结果；00：结算成功，02：结算处理中，稍后查询，其他结算失败
