# 刷脸支付-获取调用凭证

**简要描述：** 
- 针对微信刷脸支付获取SDK调用凭证

**请求URL：** 
- 服务商->联动优势
`{交易服务根地址}/pay/wxface`

**请求方式：**
- POST 

**请求参数：** 

|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|:--------:|:--------:|:--------:|:--------:|:--------|
|	acqSpId	|	代理商编号	|	10	|	M	|	代理商编号(联动平台分配)	|
|	acqMerId	|	商户号	|	8	|	M	|	商户号(联动平台分配)	|
|	rawDate	|	商户订单号	|	64	|	M	|	初始化数据	  |
|	appid	|	appid	|	32	|	O	|	商户号绑定的公众号/小程序appid 	  |
|	mchId	|	微信商户号	|	32	|	O	|	微信商户号	  |
|	subMchId	|	微信子商户号(服务商模式)	|	32	|	O	|	微信子商户号(服务商模式)	  |
|	signature	|	签名	|	256	|	M	|	参见签名机制	|

 **商户请求报文示例**

```json
{
	"acqMerId": "30000102",
	"acqSpId": "Y000000001",
	"rawDate": "rdzIw3JDkWcwDxCf17WGcHFJnLEDa6VYpRfa30lCoT+RjG7uuf0hWNJKjtrGNhDA1g8A/qFRTXMvhW3SkW3Cvu9r9xF7z8YSYkV+xpxL4ZmgSZIipjye1Z1gwgoBtHT/hbdBhJ4CQGW2mEl9fT+/XYXRWAQEzW8KHKLS3eleMzL/rNIuuijvGzJq3hURc4g2u+qnv3D29t6WT3aCZdtcrIhjB/1rVE31k2RmdDlrLMeoHbg99iFdpAuNF3gKvU1Rben0WxjqR01jbZ1J0Z391Ke3y9a3p5wlCMmJGb1qaqPS5ytSWbUgzFKxGIX7Emsw1iQi9No5u3xolAPtg1XBtUdY+aWV6gYGczrvoSHUK3FekLXcuRj6VPy69sMN1/YOxKET1JdrFGt+WVKtb0PI9MYTHZQPkBFCzNXTSz6OtxCNlHS9Q3ryDJtdlMmyHxt0kV0BtFW9cgFjhUVd1qFgF/ZNfVs/Xc6QGd6AKNsdyj3RP6vcmj+Xqpr7K/sesHxc//m4ZTQr8e9VgKW8ILm3Zf7Y0IZ7MZi186oAyN85VFXldhols9wVlN64QnktFDSnyd3DvYHNqyygrWJS02wbvXAluMDiaJ/GVAdNSw7aY2epYJxEk2L0Puzs64VadE/mWjPw/UNKk22eMphkxZBrE1Z1jC/v1NC9BPHcAY57oZ8wExMrsbLt/pvOCGjrmQLMrZienFr5qkO0OO3UN/lJ7YUWRM12AJy58KtMBkFpm6o05cdwSOD6UMMO1FsmgVKNYxgKOE2y/TDbOA9jPbcojicY9Hym6X3Hi/YZ2II1p5u9OiIqsd/Hf7DDqW1mYds=",
	"signature": "fgJpXQpFKPsLxlNJvFK3MPc5x+GHEQru1Q+69m65358e8WTge9Djv9qT6wkJOijPOESOZNaWM1mDCePA7WaeWwdR9CjjLTzf9gVKmFNcSehTbUl2JW8WSg09dPqkfbZq9SFrg6vGC5HHf/Z9YJF82gtVlzIt4SzwxGx//EzTyPM="
}
```

 **返回参数说明** 

|	字段	|	名称	|	长度	|	必填	|	说明	|
|--------|--------|--------|--------|--------|
|	respCode	|	返回码	|	8	|	M	|	返回码	|
|	respMsg	|	返回信息	|	128	|	M	|	返回信息	|
|	appid	|	appid	|	32	|	O	|	商户号绑定的公众号/小程序appid 	  |
|	mchId	|	微信商户号	|	32	|	O	|	微信商户号	  |
|	subMchId	|	微信子商户号(服务商模式)	|	32	|	O	|	微信子商户号(服务商模式)	  |
|	authinfo	|	sdk调用凭证	|	4096	|	O	|	sdk调用凭证	|
|	expiresIn	|	有效期	|		|	O	|	authinfo的有效时间, 单位秒。 例如: 3600
在有效时间内, 对于同一台终端设备，相同的参数的前提下(如：相同的公众号、商户号、 门店编号等），可以用同一个authinfo，多次调用	|
|	signature	|	签名	|	256	|	M	|	参见签名机制	|

 **返回报文示例**

```json
{
    "respCode": "00",
    "respMsg": "处理成功",
    "signature": "IHOSV/kVhDCVnSLhGSklvsg8UhCG5sxHPv3zzpm6BferBiHccp1i+LHPe0OP2ZVoTrIVf39ihYTTNmAoezUpz6UwXcLD9/0rwt0UjsDSpM4K3diYnzQsLCp0g/Gde14TypB7gxyxLnC5EKDmPYecH+hIKctVTz1WlM+fbemItvw=",
}
```



**备注** 

- 除了成功（00）的返回码需要做对应的逻辑处理，其他返回码均不需要做处理
