# 请求商户入网
    
**简要描述：** 
- 通过接口(API)形式进行商户入网
- 请通过主动调用**2.3 商户信息查询**接口来保证商户状态一致性

**请求URL：** 
- 服务商->联动优势
`{交易服务根地址}/merchants/apply
  
**请求方式：**
- POST 

**请求参数：** 


|	字段	 |    名称  |	  长度  	|	必填  	|	说明	  |
|:--------:|:---------------:|:--------:|:--------:|:--------|
|	acqSpId	|	服务商编号	|	10	|	M	|	服务商编号(联动平台分配)	|
|	merchantName	|	商户简称	|	16	|	M	|	商户交易显示名称	|
|	paper	|	商户详细信息	|		|	M	|	json 格式字符串	|
|	rate	|	手续费费率	|		|	M	|	json 格式字符串	|
|	wechatChannelId	|	服务商微信渠道号	|	64	|	M	|	微信报备的服务商渠道号	|
|	alipayChannelId	|	服务商支付宝渠道号	|	64	|	M	|	支付宝报备的服务商渠道号	|
|	backUrl	|	回调通知地址	|	200	|	O	|	商户入网成功回调地址	|
|	|
|	paper格式如下：		|
|	商户基本信息：		|
|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|	merchantType	|	商户类型	|	1	|	M	|	1:个体 2:企业 3:小微	|
|	businessLicenseCode	|	营业执照编号	|	32	|	C	|	merchantType= 1、2必传	|
|	businessLicenseName	|	商户经营名称	|	64	|	C	|	merchantType= 1、2必传	|
|	businessLicenseEffective|	营业执照生效日期	|	8	|	C	|	merchantType= 1、2必传	|
|	businessLicenseExpired	|	营业执照失效日期	|	8	|	C	|	merchantType= 1、2必传	|
|	industryCategoryId	|	经营类目	|	4	|	M	|	参见附件一	|
|	商户基本信息：		|
|	businessAddress	|	所属地址详细地址	|	256	|	M	|		|
|	province	|	所属地址省	|	4	|	M	|		|
|	city	|	所属地址市	|	4	|	M	|		|
|	area	|	所属地址区	|	4	|	M	|		|
|	法人资料信息：		|
|	lawyerName	|	法人姓名	|	32	|	C	|	merchantType= 1、2必传	|
|	lawyerCertType	|	法人证件类型	|	1	|	C	|	merchantType= 1、2必传，1：身份证	|
|	lawyerCertNo	|	法人证件号	|	20	|	C	|	merchantType= 1、2必传	|
|	certNoEffective	|	证件生效日期	|	8	|	C	|merchantType=1、2时，为企业法人证件有效日期<br> merchantType=3时，为结算人证件有效日期<br>Ex:20190101	|
|	certNoExpired	|	证件失效日期	|	8	|	C	|merchantType=1、2时，为企业法人证件失效日期<br> merchantType=3时，为结算人证件失效日期<br>Ex:20390101或永久	|
|	联系人信息：		|
|	contactPerson	|	联系人姓名	|	32	|	M	|		|
|	contactPersonId	|	联系人身份证号	|	18	|	M	|		|
|	contactPhone	|	联系人手机号	|	11	|	M	|		|
|	serviceTel	|	客服电话	|	12	|	M	|		|
|	email	|	邮箱	|	64	|	M	|		|
|	签约信息：	
|	signType	|	签约协议类型	 |	1	|	O	| 0:纸质协议 1:电子签章协议,默认为电子签章协议	| 
|	signName	|	签约人姓名	 |	32	|	C	|	电子签章协议需要必填	|
|	signMobileNo	|	签约人手机号	 |	11     |	C	|	电子签章协议需要必填	|
|	signCertNo	|	签约人身份证号	 |	18     |	C	|	电子签章协议需要必填	|
|	appName		|	APP名称 	    |	   32	  |	C	|	appName和webSite任选其一,电子签章协议需要必填	|
|	webSite		|	商户网址	  |	256	|	C	|	appName和webSite任选其一,电子签章协议需要必填	|
|	结算账户信息：		|
|	settleAccountType	|	结算账户类型	|	1	|	M	|	1:对公账户 2:法人账户3：被授权人账户<br>merchantType=3时必填2	|
|	settleAccountNo	|	结算账号	|	30	|	M	|		|
|	settleAccount	|	结算户名	|	256	|	M	|		|
|	settlePeriod	|	结算周期	|	1	|	M	|	1:T1 2:D0 3:T0 4:D1	|
|	settleIdNo	|	身份证号	|	18	|	M	|		|
|	settleIdEffective|	身份证生效日期	|	8	|	M	|	Ex:20190101	|
|	settleIdExpired	 |	身份证失效日期	|	8	|	M	|	Ex:20390101或永久	|
|	openBank	|	开户银行名称	|	4	|	C	|	对公必填	|
|	openSubBank	|	开户支行名称	|	64	|	M	|		|
|	终端信息：		|
|	terminalType	|	机具来源	|	1	|	C	|	2：代理商自备；<br> 3：暂无终端；<br> 默认为暂无终端	|
|	installProvinceCode	|	装机地址省编码	|	4	|	C	|	terminalType为2时必填	|
|	installCityCode	|	装机地址市编码	|	4	|	C	|	terminalType为2时必填	|
|	installAreaCode	|	装机地址区(县)编码	|	4	|	C	|	terminalType为2时必填	|
|	installDetailAddr	|	装机详细地址	|	32	|	C	|	terminalType为2时必填	|
|	terminals	|	终端SN	|		|	C	|	terminalType为2时必填，<br>merchantType=1、2时,终端个数最多为5个;<br>merchantType=3时,终端个数最多为1个;<br>格式为provId#modelId#snNumber-snNumber=provId#modelId#snNumber-snNumber,<br>eg：8#02#0000010483433721-00001=8#01#SMP2000test008	|
|	|
|	rate格式如下：		|
|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|	feeRateAlipay 	|	支付宝费率	|	13	|	C	|	费率为0.25%上送0.25	|
|	feeRateWechatpay  	|	微信费率	|	13	|	C	|	费率为0.25%上送0.25	|
|	bankCardRateLevel1   	|	银行卡费率一档	|		|	C	|	1000元以上费率	|
|	bankCardRateLevel2   	|	银行卡费率二档	|		|	C	|	1000元以下费率	|
|	feeRateD0   	|	D0手续费	|	13	|	C	|	费率为0.25%上送0.25	|
|	feeRateWithdraw   	|	提现手续费	|	13	|	C	|	单位：分	|
|	posRateLevel1   	|	银行卡收单费率一档	|		|	C	|	1000元以上费率；<br>NFC 1000元以上取该费率；<br>借贷记取该费率	|
|	posRateLevel2   	|	银行卡收单费率二档	|		|	C	|	1000元（含1000元）以下费率；<br>小额双免取该费率；<br>NFC 1000元(含)取该费率  |
|	|
|	bankCardRateLevel1、bankCardRateLevel2、posRateLevel1和posRateLevel2格式如下：		|
|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|	feeRateUnionpayDebit    	|	银联手续费率(借记)	|	13	|	C	|	费率为0.25%上送0.25	|
|	feeRateUnionpayDebitCap    	|	银联手续费率(借记封顶)	|	13	|	C	|	一挡1000元以上借记卡封顶费率必填，单位：分|
|	feeRateUnionpayCredit    	|	银联手续费率(贷记)	|	13	|	C	|	费率为0.25%上送0.25	|


 **商户请求报文示例**

```json

{
	"txnAmt": "1",
	"orderNo": "20180122LDYF00008940",
	"goodsId": "goods",
	"signature": "CBr2Dui55aRxyiUJoWCxckL8lWn7UeBxvAJFsV2hrtFDvVSOp4v4cgUPc1Nk3e1d+oitAhi9b3AAVSoAuEWV0fKKIQRwYTSPTzLbX9fLXq2KE423Km5GW5HWqpN8+guCH1UUpSlNVzVYax9h5D/n2YSWv/g6KWZYye+kEP8K3rA=",
	"orderTime": "20180313105356",
	"merId": "30000064",
	"agentId": "Y000000001",
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
|	merId	|	报备编号	|		|	N	|	报备成功后会返回   |
