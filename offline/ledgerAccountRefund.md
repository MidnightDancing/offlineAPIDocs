# 刷卡支付-用户分账退回
    
**简要描述：** 

- 从子商户处进行交易退回

**请求URL：** 
- 服务商->联动优势
`{交易服务根地址}/pay/ledgerAccountRefund`

**请求方式：**
- POST 

**请求参数：** 


|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|:--------:|:--------:|:--------:|:--------:|:--------|
|	orderTime	|	订单时间	|	16	|	M	|	yyyyMMddHHmmss	|
|	acqSpId	|	代理商编号	|	10	|	M	|	代理商编号(联动平台分配)	|
|	acqMerId	|	商户号	|	8	|	M	|	商户号(联动平台分配)	|
|	ledgerOrderNo	|	分账订单号	|	64	|	M	|	商户的分账支付订单号	|
|	transLedgerOrderNo	|	联动分账订单号	|	32	|	M	|	联动优势的分账订单号|
|	ledgerRefundOrderNo	|	分账退回订单号	|	32	|	M	|	联动优势的分账订单号|
|	ledgerRefundInfo	|	分账退回信息	|	1024	|	M	|		|
|	signature	|	签名	|	256	|	M	|	参见签名机制	|
|	|
|	ledgerRefundInfo格式如下：		|
|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|	ledgerAcqMerId    	|	分账退回商户号	|	13	|	M	|		|
|	ledgerAccountType    	|	分账退回账户类型	|	13	|	M	|	1:担保账户 2:现金账户	|
|	ledgerRefundTxnAmt	|	分账退回金额	|	13	|	M	|	单位:分	|


 **返回参数说明** 
 
|	字段	|	名称	|	长度	|	必填	|	说明	|
|--------|-------|--------|--------|--------|
|	respCode	|	返回码	|	8	|	M	|	返回码	|
|	respMsg	|	返回信息	|	128	|	M	|	返回信息	|
|	platDate	|	平台日期	|	16	|	M	|	平台日期   |
|	ledgerRefundOrderNo	|	分账退回订单号	|	64	|	M	|	商户的分账支付订单号	|
|	transLedgerRefundOrderNo	|	联动分账退回订单号	|	32	|	O	|	联动优势的订单号|
|	ledgerRefundResult	|	分账结果信息	|	1024	|	O	|		|
|	signature	|	签名	|	256	|	M	|	参见签名机制	|
|	|
|	ledgerRefundResult格式如下：		|
|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|	refundResultCode	|	分账结果码	|	8	|	O	|	返回码	|
|	refundResultMsg	|	分账结果信息	|	128	|	O	|	返回信息	|
|	ledgerRefundTrans	|	分账流水号	|	64	|	O	|	商户的分账流水号	|
|	ledgerAcqMerId    	|	分账商户号	|	13	|	O	|		|
|	ledgerAccountType    	|	分账退回账户类型	|	13	|	O	|		|
|	ledgerTxnAmt	|	分账金额	|	13	|	O	|	单位:分	|
