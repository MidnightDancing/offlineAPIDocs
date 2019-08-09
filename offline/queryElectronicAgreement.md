# 电子协议下载
    
**简要描述：** 

- 商户->联动优势

- 下载电子协议


**请求URL：** 

`{交易服务根地址}/merchants/queryElectronicAgreement
  
**请求方式：**

- POST 

**请求参数：** 


|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|:--------:|:--------:|:--------:|:--------:|:--------|
|	acqSpId	|	服务商编号	|	10	|	M	|	服务商编号(联动平台分配)	|
|	merId	|	报备编号	|	20	|	M	|		|
|	signature 	|	签名	|	256	|	M	|	参见签名机制	|


 **商户请求报文示例**

```json

{
	"merId": "30000064",
	"acqSpId": "S145871705",
	"signature": "CBr2Dui55aRxyiUJoWCxckL8lWn7UeBxvAJFsV2hrtFDvVSOp4v4cgUPc1Nk3e1d+oitAhi9b3AAVSoAuEWV0fKKIQRwYTSPTzLbX9fLXq2KE423Km5GW5HWqpN8+guCH1UUpSlNVzVYax9h5D/n2YSWv/g6KWZYye+kEP8K3rA="
}

```

 **返回参数说明** 
 
|	字段	|	名称	|	长度	|	必填	|	说明	|
|--------|-------|--------|--------|--------|
|	respCode	|	返回码	|	8	|	M	|	返回码	|
|	respMsg	|	返回信息	|	128	|	M	|	返回信息	|
|	contName	|	电子协议名称	|		|	C	|	成功时返回	|
|	elecAgreement	|	电子协议pdf文件	|		|	C	|	Base64格式，成功时返回	|

