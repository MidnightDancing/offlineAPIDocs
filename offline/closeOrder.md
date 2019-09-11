# 商户订单关闭请求
**简要描述：** 

- 可对支付失败、支付中的订单发起订单关闭申请。
- 关闭成功的订单不允许继续支付。

**请求URL：** 
- 服务商->联动优势
`{交易服务根地址}/pay/closeOrder`

**请求方式：**
- POST 

**请求参数：** 

|	字段	|	名称	|	长度	|	必填	|   说明|
|:--------:|:--------:|:--------:|:--------:|:--------|
|	acqSpId	|	代理商编号	|	10	|	M	|	代理商编号(联动平台分配)	|
|	acqMerId	|	商户号	|	8	|	M	|	商户号(联动平台分配)	|
|	orderNo	|	订单号 	|	64	|	M	|	订单号	|
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
|	platDate	|	平台日期	|		|	C	|	平台日期   |
|	signature	|	签名	|	256	|	M	|	参见签名机制	||

**常见问题：**

【问题】：什么状态可以发起订单关闭申请？
【答案】：支付中/支付失败。
可以调用订单状态同步接口查询订单状态，实际以用户收到订单金额为准。