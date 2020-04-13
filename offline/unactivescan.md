# 服务商平台-刷卡支付-用户被扫
    
**简要描述：** 

- 消费者通过微信/支付宝/银联钱包等出示付款码，通过扫码设备识别付款码并完成下单支付
- 请通过主动调用**3.4订单状态同步**接口来保证订单状态一致性

**请求URL：** 
- 服务商->联动优势
`{交易服务根地址}/pay/micropay`
  
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
|	orderType	|	订单类型	|	12	|	M	|	alipay-支付宝 <br> wechat-微信支付 <br> unionpay-银联二维码	|
|	authCode	|	付款码	|	32	|	M	|	05:微信刷卡：以10\~15开头的长度18位的数字</br>06:支付宝条码：以25\~30开头的长度为16~24位的数字</br>09:银联二维码：以62开头长度19位数字	|
|	goodsId	|	商品ID	|	16	|	M	|	商品ID	|
|	goodsInfo	|	商品信息	|	128	|	M	|	商品信息	|
|	backUrl	|	通知地址	|	256	|	O	|	结果通知地址. 必须以 http://开始,支持大小写字母,数字,'/','&','%','?','='. 暂不支持通知地址中包含其他字符,包含url编码后的结果 如%3C %3E等|
|	merPriv	|	商户私有域	|	128	|	O	|	商户私有域	|
|	faceFlag	|	刷脸支付标识	|	2	|	O	|	刷脸支付标识，1：刷脸支付	|
|	ledgerAccountFlag	|	分账标识	|	2	|	O	|	1：分账订单	|
|	signature	|	签名	|	256	|	M	|	参见签名机制	|

 **商户请求报文示例**

```json

{
	"txnAmt": "1",
	"orderNo": "20180122LDYF00008940",
	"goodsId": "goods",
	"signature": "CBr2Dui55aRxyiUJoWCxckL8lWn7UeBxvAJFsV2hrtFDvVSOp4v4cgUPc1Nk3e1d+oitAhi9b3AAVSoAuEWV0fKKIQRwYTSPTzLbX9fLXq2KE423Km5GW5HWqpN8+guCH1UUpSlNVzVYax9h5D/n2YSWv/g6KWZYye+kEP8K3rA=",
	"orderTime": "20180313105356",
	"acqMerId": "30000064",
	"acqSpId": "Y000000001",
	"orderType": "wechat",
	"authCode": "134919586213926735",
	"goodsInfo": "goods"
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
|	paySeq	|	支付流水号	|	64	|	O	|	支付流水号（条形码），成功返回	|
|	txnAmt	|	订单金额	|	10	|	M	|	订单金额 	|
|	depBankSeq	|	第三方流水号	|   |	O  |	第三方流水号（微信/支付宝/银联的交易流水号） 	|
|	payerInfo	|	支付信息	|		|	O	|	支付信息 	|
|	signature	|	签名	|	256	|	M	|	参见签名机制	|

**备注** 

- 返回码（00）为成功，若当前返回码为（02）则为交易结果未明（支付结果未知），请调用查询订单接口确认状态。
- 如果当前交易返回的支付状态是明确的错误原因造成的支付失败（支付确认失败），请重新下单支付。
