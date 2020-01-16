# 刷卡支付-用户分账
    
**简要描述：** 

- 服务商帮助商户完成订单收单成功后的资金分配。
- 收单交易成功后调用此接口完成分账能力

**请求URL：** 
- 服务商->联动优势
`{交易服务根地址}/pay/ledgerAccount`

**请求方式：**
- POST 

**请求参数：** 


|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|:--------:|:--------:|:--------:|:--------:|:--------|
|	orderTime	|	订单时间	|	16	|	M	|	yyyyMMddHHmmss	|
|	acqSpId	|	代理商编号	|	10	|	M	|	代理商编号(联动平台分配)	|
|	acqMerId	|	商户号	|	8	|	M	|	商户号(联动平台分配)	|
|	orderNo	|	商户订单号	|	64	|	M	|	商户的支付订单号	|
|	ledgerOrderNo	|	分账订单号	|	64	|	M	|	商户的分账支付订单号	|
|	txnAmt	|	订单金额	|	13	|	M	|	单位:分	|
|	ledgerInfo	|	分账信息	|	1024	|	M	|		|
|	signature	|	签名	|	256	|	M	|	参见签名机制	|
|	|
|	ledgerInfo格式如下：		|
|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|	ledgerAcqMerId    	|	分账商户号	|	13	|	M	|		|
|	ledgerTxnAmt	|	分账金额	|	13	|	M	|	单位:分	|
|	ledgerMerPriv	|	分账商户私有域	|	128	|		|	商户私有域	|


 **返回参数说明** 
 
|	字段	|	名称	|	长度	|	必填	|	说明	|
|--------|-------|--------|--------|--------|
|	respCode	|	返回码	|	8	|	M	|	返回码	|
|	respMsg	|	返回信息	|	128	|	M	|	返回信息	|
|	platDate	|	平台日期	|	16	|	M	|	平台日期   |
|	orderNo	|	商户订单号	|	64	|	M	|		|
|	ledgerOrderNo	|	分账订单号	|	64	|	M	|	商户的分账支付订单号	|
|	transLedgerOrderNo	|	联动分账订单号	|	32	|	O	|	联动优势的订单号|
|	ledgerResult	|	分账结果信息	|	1024	|	O	|		|
|	signature	|	签名	|	256	|	M	|	参见签名机制	|
|	|
|	ledgerResult格式如下：		|
|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|	resultCode	|	分账结果码	|	8	|	M	|	返回码	|
|	resultMsg	|	分账结果信息	|	128	|	M	|	返回信息	|
|	ledgerTrans	|	分账流水号	|	64	|	O	|	商户的分账流水号	|
|	ledgerAcqMerId    	|	分账商户号	|	13	|	M	|		|
|	ledgerTxnAmt	|	分账金额	|	13	|	M	|	单位:分	|
|	ledgerMerPriv	|	分账商户私有域	|	128	|		|	商户私有域	|
