# 服务商平台-退款订单查询
**简要描述：**
- 退费状态查询

**请求URL：** 
- 商户->联动优势
`{交易服务根地址}/pay/refundQuery`

**请求方式：**
- POST 

**请求参数：** 

|	字段	|	名称	|	长度	|	必填	|   说明|
|:--------:|:--------:|:--------:|:--------:|:--------|
|	acqSpId	|	代理商编号	|	10	|	M	|	代理商编号(联动平台分配)	|
|	acqMerId	|	商户号	|	8	|	M	|	商户号(联动平台分配)	|
|	orderNo	|	退款流水号	|	64	|	M	|	本次退货交易的订单号	|
|	signature	|	签名	|	256	|	M	|参见签名机制	|	|


 **商户请求报文示例**

```json

{
	"acqSpId": "30000102",
	"acqMerId": "Y000000001",
	"orderNo": "HSAPI1520425994612",
	"signature": "FI8kw10QzZICpj2xRjsYcmU+HI7oNFm4KaNSPeT4YbmG7izCV4m9jZJQ1gxkny0bt5xY8MZXXtzFeRR5KEyzp2YFYMC0AFjvsd/5HGlE6JxrVKNg/LhIba7aR7WMrX4FtEcmBm4ILMosgVhf665KgGtdHBuCd5qRfAs217iPWd0="
}

```

 **返回参数说明** 
 
|	字段	|	名称	|	长度	|	必填	|	说明	|
|----|----|----|----|----|
|	respCode	|	返回码	|	8	|	M	|	返回码	|
|	respMsg	|	返回信息	|	128	|	M	|	返回信息	|
|	platDate	|	平台日期	|		|	M	|	平台日期   |
|	origRespCode	|	交易应答码	|	2	|	C	|当返回码respCode为00时返回，代表退货交易的状态 </br> 00- 退款成功 </br>  01- 退款失败 </br> 02- 退款处理中	|
|	orderNo	|	退款流水号	|	64	|	C	| 	|
|	txnAmt	|	退款金额	|	13	|	C	| 	|
|	transactionId	|	联动流水号	|	32	|	M	|	联动优势的流水号|
|	signature	|	签名	|	256	|	M	|	参见签名机制	||

 **备注** 
- 响应报文中，retCode=00,请求退款查询成功，retCode!=00,请求退款查询失败。
- <span style="color:red">查询成功后，获取交易应答码（origRespCode），根据origRespCode判定这笔退款订单成功或者失败。</span>
