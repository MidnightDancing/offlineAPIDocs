# 刷卡支付-用户分账退回查询
**简要描述：**
- 退费状态查询

**请求URL：** 
- 商户->联动优势
`{交易服务根地址}/pay/ledgerAccountRefundQuery`

**请求方式：**
- POST 

**请求参数：** 

|	字段	|	名称	|	长度	|	必填	|   说明|
|:--------:|:--------:|:--------:|:--------:|:--------|
|	acqSpId	|	代理商编号	|	10	|	M	|	代理商编号(联动平台分配)	|
|	acqMerId	|	商户号	|	8	|	M	|	商户号(联动平台分配)	|
|	orderNo	|	退款流水号	|	64	|	M	|	本次退货交易的订单号	|
|	signature	|	签名	|	256	|	M	|参见签名机制	|	|



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
  |	ledgerRefundResult	|	分账结果	|	1024	|	O	|	联动优势的流水号|

|	字段	|	名称	|	长度	|	必填	|	说明	|
|--------|-------|--------|--------|--------|
|	respCode	|	返回码	|	8	|	M	|	返回码	|
|	respMsg	|	返回信息	|	128	|	M	|	返回信息	|
|	platDate	|	平台日期	|	16	|	M	|	平台日期   |
|	ledgerRefundOrderNo	|	分账退回订单号	|	64	|	O	|	商户的分账支付订单号	|
|	transLedgerRefundOrderNo	|	联动分账退回订单号	|	32	|	O	|	联动优势的订单号|
|	ledgerRefundInfo	|	分账退回信息	|	1024	|	O	|		|
|	signature	|	签名	|	256	|	M	|	参见签名机制	|
|	|
|	ledgerRefundInfo格式如下：		|
|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|	ledgerAcqMerId    	|	分账退回商户号	|	13	|	O	|		|
|	ledgerAccountType    	|	分账退回账户类型	|	13	|	O	|	1:担保账户 2:现金账户	|
|	ledgerRefundTxnAmt	|	分账退回金额	|	13	|	O	|	单位:分	|