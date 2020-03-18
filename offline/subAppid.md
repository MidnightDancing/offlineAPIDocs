# 微信参数配置-子商户appId
    
**简要描述：** 
- 申请子商户appId


**请求URL：** 
- 服务商->联动优势
`{交易服务根地址}/merchants/subAppid`
  
**请求方式：**
- POST 

**请求参数：** 


|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|:--------:|:--------:|:--------:|:--------:|:--------|
|	acqSpId	|	服务商编号	|	10	|	M	|	服务商编号(联动平台分配)	|
|	acqMerId 	|	商户编号	|	8	|	M	|	 	|
|	subAppid 	|	子商户SubAPPID	|	64	|	M	|	 在渠道资质下申请的APPID，如：wx1234566b0a912345	|


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

**常见问题：**

- 调用公众号或小程序支付前进行配置
- subAppid在发起JSAPI支付（包括公众号，小程序），Native支付、App支付等交易时，需传入特约商户对应类型的AppID，以完成支付，并获取sub_openid
- 所配置的APPID需通过微信认证
- 支持与特约商户主体一致的服务号
- 支持与特约商户或服务商主体一致的小程序

