# 服务商平台-微信参数配置-支付授权目录

**简要描述：** 
- 申请支付授权目录


**请求URL：** 
- 服务商->联动优势
`{交易服务根地址}/merchants/jsApiPath`

**请求方式：**
- POST 

**请求参数：** 


|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|:--------:|:--------:|:--------:|:--------:|:--------|
|	acqSpId	|	服务商编号	|	10	|	M	|	服务商编号(联动平台分配)	|
|	acqMerId 	|	商户编号	|	8	|	M	|	 	|
|	jsapiPath 	|	支付授权目录	|	64	|	M	|	 	|


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


**备注** 

- 调用公众号或小程序支付前进行配置
- 所有使用JS API方式发起支付请求的链接地址，都必须在支付授权目录之下
- 最多设置5个支付授权目录, 且域名必须通过ICP备案
- 头部要包含http或https，以左斜杠“/”结尾
