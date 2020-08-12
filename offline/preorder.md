# 服务商平台-pos预下单
    
**简要描述：** 

- 商户根据消费者需求先预下单（刷卡/扫码）。下单成功后，根据商户对应绑定的pos机具进行支付。

**请求URL：** 
- 服务商->联动优势
`{交易服务根地址}/pay/preorder`
  
**请求方式：**
- POST 

**请求参数：** 


|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|:--------:|:--------:|:--------:|:--------:|:--------|
|	orderTime	|	订单时间	|	16	|	M	|	yyyyMMddHHmmss	|
|	acqSpId	|	代理商编号	|	10	|	M	|	代理商编号(联动平台分配)	|
|	acqMerId	|	商户号	|	8	|	M	|	商户号(联动平台分配)	|
|	orderNo	|	商户订单号	|	64	|	M	|	商户的支付订单号	|
|	txnAmt	|	订单金额	|	13	|	M	|	单位:分	|
|	payType	|	支付方式	|	12	|	M	|	bankcard:刷卡<br> micropay：被扫<br> qrpay：主扫	|
|	orderType	|	订单类型	|	12	|	O	|	payType为bankcard不需要填写。<br> alipay-支付宝 <br> wechat-微信支付 <br> unionpay-银联二维码	|
|	goodsInfo	|	商品信息	|	128	|	O	|	商品信息	|
|	backUrl	|	通知地址	|	256	|	O	|	结果通知地址. 必须以 http://开始,支持大小写字母,数字,'/','&','%','?','='. 暂不支持通知地址中包含其他字符,包含url编码后的结果 如%3C %3E等|
|	merPriv	|	商户私有域	|	128	|	O	|	商户私有域	|
|	ledgerAccountFlag	|	分账标识	|	2	|	O	|	1：分账订单	|
|	longitude	|	经度	|	16	|	O	|	商户交易所在位置经度，eg:-83.2345	|
|	latitude	|	纬度	|	16	|	O	|	商户交易所在位置纬度，eg:-24.3094	|
|	signature	|	签名	|	256	|	M	|	参见签名机制	|

 **商户请求报文示例**

```json

{
  "orderTime": "20180313105356",
  "acqSpId": "Y000000001",
  "acqMerId": "30000064",
	"orderNo": "20180122LDYF00008940",
  "txnAmt": "1",
  "payType": "micropay",
  "orderType": "wechat",
	"goodsInfo": "goods",
  "backUrl": "http://provider.umfintech.com",
	"signature": "CBr2Dui55aRxyiUJoWCxckL8lWn7UeBxvAJFsV2hrtFDvVSOp4v4cgUPc1Nk3e1d+oitAhi9b3AAVSoAuEWV0fKKIQRwYTSPTzLbX9fLXq2KE423Km5GW5HWqpN8+guCH1UUpSlNVzVYax9h5D/n2YSWv/g6KWZYye+kEP8K3rA="
}

```

 **返回参数说明** 
 
|	字段	|	名称	|	长度	|	必填	|	说明	|
|--------|-------|--------|--------|--------|
|	respCode	|	返回码	|	8	|	M	|	返回码	|
|	respMsg	|	返回信息	|	128	|	M	|	返回信息	|
|	platDate	|	平台日期	|		|	M	|	平台日期   |
|	orderNo	|	商户订单号	|	64	|	O	|		|
|	transactionId	|	联动订单号	|	32	|	M	|	联动优势的订单号|
|	txnAmt	|	订单金额	|	10	|	M	|	订单金额 	|
|	stlDate	|	对账结算日期	|		|	M	| 	格式：yyyyMMdd（例如：20200615）|
|	signature	|	签名	|	256	|	M	|	参见签名机制	|

**备注** 

- 返回码（00）为成功，用户支付成功后请调用3.4订单状态同步接口确认状态。
- 返回码为其他表示失败，请重新下单。
