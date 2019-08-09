# 商户退款请求
**简要描述：** 

- 商户->联动优势
- 商户可对支付成功的订单发起退款申请，支持部分退款。
- 商户退款的金额须小于或等于可退款金额，支持对90天以内的成功订单发起退款。

**请求URL：** 

`{交易服务根地址}/pay/refund`

**请求方式：**
- POST 

**请求参数：** 

|	字段	|	名称	|	长度	|	必填	|   说明|
|:--------:|:--------:|:--------:|:--------:|:--------|
|	orderTime	|	订单时间	|	16	|	M	|	yyyyMMddHHmmss	|
|	acqSpId	|	代理商编号	|	10	|	M	|	代理商编号(联动平台分配)	|
|	acqMerId	|	商户号	|	8	|	M	|	商户号(联动平台分配)	|
|	origOrderNo	|	原单流水号	|	64	|	M	|	原交易的订单号	|
|	origTxnAmt	|	原单交易金额	|	13	|	M	|	单位:原交易的总金额|
|	orderNo	|	退款流水号	|	64	|	M	|	本次退货交易的订单号	|
|	txnAmt	|	退款金额	|	13	|	M	|	本次退货的金额|
|	signature	|	签名	|	256	|	M	|参见签名机制	|	|

 **商户请求报文示例**
```json

{
    "orderTime": "20190808105455",
	"acqSpId": "30000102",
	"acqMerId": "Y000000001",
	"origOrderNo": "HSAPI1520425994610",
	"origTxnAmt": "9",
	"orderNo": "HSAPI1520425994612",
	"txnAmt": "9",
	"orderType": "wechat",
	"signature": "FI8kw10QzZICpj2xRjsYcmU+HI7oNFm4KaNSPeT4YbmG7izCV4m9jZJQ1gxkny0bt5xY8MZXXtzFeRR5KEyzp2YFYMC0AFjvsd/5HGlE6JxrVKNg/LhIba7aR7WMrX4FtEcmBm4ILMosgVhf665KgGtdHBuCd5qRfAs217iPWd0="
}

```

 **返回参数说明** 

|	字段	|	名称	|	长度	|	必填	|	说明	|
|----|----|----|----|----|
|	respCode	|	返回码	|	8	|	M	|	返回码	|
|	respMsg	|	返回信息	|	128	|	M	|	返回信息	|
|	platDate	|	平台日期	|		|	C	|	平台日期   |
|	transactionId	|	联动退款流水号	|	32	|	C	|	联动优势的流水号|
|	signature	|	签名	|	256	|	M	|	参见签名机制	||

**常见问题：**

【问题】：退款受理成功是否算是退款成功？
【答案】：退款接口返回“0000”即表示退款受理成功。
可以调用退款查询接口查询退款状态，实际以用户收到退款金额为准。

【问题】：退款成功后，钱什么时候到账？
【答案】：余额即时到账，银行卡3-5个工作日。

【问题】：退款受理成功，还可以再次发起退款吗？
【答案】：可以；

【问题】：什么情况算是退款失败？
【答案】：非“0000”的其他返回码算是退款失败。

【问题】：退款查询失败，还可以再次发起退款吗？
【答案】：可以。