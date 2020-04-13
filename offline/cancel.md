# 服务商平台-商户撤销交易

**简要描述：** 
- 针对交易当天的成功订单可以进行撤销，过了账期则需要走退费接口。**仅支持微信支付宝刷卡交易（即用户被扫）的撤销，其他支付成功订单如需实现相同功能请调用3.5退款请求接口**

**请求URL：** 
- 服务商->联动优势
`{交易服务根地址}/pay/cancelOrder`

**请求方式：**
- POST 

**请求参数：** 

|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|:--------:|:--------:|:--------:|:--------:|:--------|
|	acqSpId	|	代理商编号	|	10	|	M	|	代理商编号(联动平台分配)	|
|	acqMerId	|	商户号	|	8	|	M	|	商户号(联动平台分配)	|
|	orderNo	|	商户订单号	|	64	|	M	|	原来请求订单号	  |
|	signature	|	签名	|	256	|	M	|	参见签名机制	|

 **商户请求报文示例**

```json
{
	"acqMerId": "30000102",
	"acqSpId": "Y000000001",
	"orderNo": "1509518653211",
	"signature": "fgJpXQpFKPsLxlNJvFK3MPc5x+GHEQru1Q+69m65358e8WTge9Djv9qT6wkJOijPOESOZNaWM1mDCePA7WaeWwdR9CjjLTzf9gVKmFNcSehTbUl2JW8WSg09dPqkfbZq9SFrg6vGC5HHf/Z9YJF82gtVlzIt4SzwxGx//EzTyPM="
}
```

 **返回参数说明** 

|	字段	|	名称	|	长度	|	必填	|	说明	|
|--------|--------|--------|--------|--------|
|	respCode	|	返回码	|	8	|	M	|	返回码	|
|	respMsg	|	返回信息	|	128	|	M	|	返回信息	|
|	platDate	|	平台日期	|		|	M	|	平台日期   |
|	transactionId	|	联动流水号	|	32	|	M	|	联动优势的流水号|
|	signature	|	签名	|	256	|	M	|	参见签名机制	|

 **返回报文示例**

```json
{
    "respCode": "00",
    "respMsg": "处理成功",
    "signature": "IHOSV/kVhDCVnSLhGSklvsg8UhCG5sxHPv3zzpm6BferBiHccp1i+LHPe0OP2ZVoTrIVf39ihYTTNmAoezUpz6UwXcLD9/0rwt0UjsDSpM4K3diYnzQsLCp0g/Gde14TypB7gxyxLnC5EKDmPYecH+hIKctVTz1WlM+fbemItvw=",
    "platDate": "20190815",
    "transactionId": "2019072914580000000036"
}
```



**备注** 

- 除了成功（00）的返回码需要做对应的逻辑处理，其他返回码均不需要做处理
