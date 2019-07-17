# 订单状态同步
  
**简要描述：** 

- 商户->联动优势
- 平台对商户的支付请求数据处理完成后，响应为非明确状态。如：“01000000”返回码的情况下，需要调用同步接口，请求通道查询最终结果。

**请求URL：** 

`{交易服务根地址}/pay/queryOrder`

**请求方式：**

- POST 

**请求参数：** 

|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|:--------:|:--------:|:--------:|:--------:|:--------|
|	agentId	|	代理商编号	|	8	|	M	|	代理商编号(联动平台分配)	|
|	merId	|	商户号	|	8	|	M	|	商户号(联动平台分配)	|
|	orderNo	|	商户订单号	|	64	|	M	|	原来请求订单号	  |
|	transactionId	|	联动订单号	|	32	|	O	|	联动优势的订单号，建议优先使用	|
|	signature	|	签名	|	256	|	M	|	参见签名机制	|

 **商户请求报文示例**

```json
{
	"merId": "30000102",
	"agentId": "Y000000001",
	"orderNo": "1509518653211",
	"signature": "fgJpXQpFKPsLxlNJvFK3MPc5x+GHEQru1Q+69m65358e8WTge9Djv9qT6wkJOijPOESOZNaWM1mDCePA7WaeWwdR9CjjLTzf9gVKmFNcSehTbUl2JW8WSg09dPqkfbZq9SFrg6vGC5HHf/Z9YJF82gtVlzIt4SzwxGx//EzTyPM="
}
```

 **返回参数说明** 
 
|	字段	|	名称	|	长度	|	必填	|	说明	|
|--------|--------|--------|--------|--------|
|	respCode	|	返回码	|	8	|	M	|	返回码	|
|	respMsg	|	返回信息	|	128	|	M	|	返回信息	|
|	origRespCode	|	交易状态	|		|	C	|  00—支付成功 <br>02—未支付 <br>06--支付失败(其他原因，如银行返回失败)|	
|	platDate	|	平台日期	|		|	M	|	平台日期   |
|	txnAmt	|	订单金额	|		|	C	|	订单金额 (打印小票使用)	|
|	paySeq	|	支付流水号	|		|	C	|	支付流水号，成功返回	|
|	transactionId	|	联动流水号	|	32	|	M	|	联动优势的流水号|
|	signature	|	签名	|	256	|	M	|	参见签名机制	|

 **备注** 
- 除了成功（0000）的返回码需要做对应的逻辑处理，其他返回码均不需要做处理
- 返回码为成功（0000），根据交易状态transactionId判断交易是否成功。
