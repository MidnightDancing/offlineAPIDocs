# 刷卡支付-用户分账退回
    
**简要描述：** 

- 从子商户处进行交易退回

**请求URL：** 
- 服务商->联动优势
`{交易服务根地址}/pay/ledgerAccountRefund`

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
|	ledgerOrderNo	|	分账退款流水号	|	64	|	O	|	本次退货交易的分账订单号（原交易为分账交易必传）|
|	ledgerRefundInfo	|	分账退回信息	|	1024	|	O	| 不传默认主商户直接退	|
|	|
|	ledgerRefundInfo格式如下：		|
|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|	ledgerAcqMerId    	|	分账退回商户号	|	8	|	O	|		|
|	ledgerAccountType    	|	分账退回账户类型	|	2	|	O	|	1:担保账户 2:现金账户	|
|	ledgerRefundTxnAmt	|	分账退回金额	|	13	|	O	|	单位:分	|


 **返回参数说明** 
 
 |	字段	|	名称	|	长度	|	必填	|	说明	|
 |----|----|----|----|----|
 |	respCode	|	返回码	|	8	|	M	|	返回码	|
 |	respMsg	|	返回信息	|	128	|	M	|	返回信息	|
 |	platDate	|	平台日期	|		|	O	|	平台日期   |
 |	transactionId	|	联动退款流水号	|	32	|	O	|	联动优势的流水号|
|	signature	|	签名	|	256	|	M	|	参见签名机制	|
