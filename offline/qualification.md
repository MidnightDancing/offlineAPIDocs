# 资质上传接口
    
**简要描述：** 

- 商户->联动优势

- 申请资质信息上传接口

- 

**请求URL：** 

`{交易服务根地址}/upload/qualification
  
**请求方式：**

- POST 

**请求参数：** 


|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|:--------:|:--------:|:--------:|:--------:|:--------|
|	acqSpId	|	服务商编号	|	10	|	M	|	服务商编号(联动平台分配)	|
|	merId	|	报备编号	|	17	|	M	|	商户基本信息开户成功返回的商户报备编号	|
|	imageType	|	资质图片类型	|	64	|	M	|		|
|	imageSource	|	上传资质图片	|		|	M	|		|
|	imageName	|	上传资质的名称	|	32	|	M	|		|
|	|
|	图片类型：		|
|	个体：		|
|	字段标识	 |	字段含义	  |	备注说明	  |
|	idCardFront	|	法人身份证正面	|		|
|	idCardBack	|	法人身份证反面	|		|
|	idCardHandle	|	法人手持身份证	|		|
|	bankCardPhotoFront	|	银行卡正面	|	settleAccountType=2时必传	|
|	bankCardPhotoBack	|	银行卡反面	|	settleAccountType=2时必传	|
|	openingLicenseAccountPhoto	|	开户许可证	|	settleAccountType=1时必传	|
|	acquiringAgreementPhoto	|	商户收单协议照片	|		|
|			|
|	企业：		|
|	字段标识	 |	字段含义	  |	备注说明	  |
|	idCardFront	|	法人身份证正面	|		|
|	idCardBack	|	法人身份证反面	|		|
|	idCardHandle	|	法人手持身份证	|		|
|	bankCardPhotoFront	|	银行卡正面	|	settleAccountType=2时必传	|
|	bankCardPhotoBack	|	银行卡反面	|	settleAccountType=2时必传	|
|	openingLicenseAccountPhoto	|	开户许可证	|	settleAccountType=1时必传	|
|	acquiringAgreementPhoto	|	商户收单协议照片	|		|
|			|
|	小微：		|
|	字段标识	 |	字段含义	  |	备注说明	  |
|	idCardFront	|	法人身份证正面	|		|
|	idCardBack	|	法人身份证反面	|		|
|	idCardHandle	|	法人手持身份证	|		|
|	bankCardPhotoFront	|	银行卡正面	|		|
|	bankCardPhotoBack	|	银行卡反面	|		|
|	storeHeadPhoto	|	门店门头照	|		|
|	storeShopPhoto	|	门店外景照	|		|
|	storeHallPhoto	|	门店内景照	|		|
|	storeCashierPhoto 	|	门店收银台照	|		|
|	acquiringAgreementPhoto	|	商户收单协议照片	|		|


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
