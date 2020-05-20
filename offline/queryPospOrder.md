# 服务商平台-查询交易信息

**简要描述：** 
- 平台对服务商的交易信息提供查询能力

**请求URL：** 
- 服务商->联动优势
`{交易服务根地址}/pay/queryPospOrder 

**请求方式：**
- POST 

**请求参数：** 

|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|:--------:|:--------:|:--------:|:--------:|:--------|
|	acqSpId	|	代理商编号	|	10	|	M	|	代理商编号(联动平台分配)	|
|	acqMerId	|	商户号	|	8	|	M	|	商户号(联动平台分配)	|
|	startTime	|	交易开始时间	|	14	|	M	|	yyyyMMddHHmmSS(20200519000000)	|
|	endTime	|	交易结束时间	|	14	|	C	|	yyyyMMddHHmmSS(20200519000024(不传默认当前时间)	|
|	signature	|	签名	|	256	|	M	|	参见签名机制	|

 **商户请求报文示例**

```json
{
	"acqMerId": "41509208",
	"acqSpId": "Y471790403",
	"signature": "A973LB9jCo30ckA6ILL2hChhdUbt4krFoJlcFNufox7RN22Hmujey75bbvR6MQ/1U3q/Um/ivCaD45U3+xvka53gHnQPvz7q0d+R+RXA7BYdwnTjFgk883AjvMi5EM75ivHZA2Fua3SWDmSTTrlPGfEGxkqfaMnThLsYZYzri0U="
}
```

 **返回参数说明** 

|	字段	|	名称	|	长度	|	必填	|	说明	|
|--------|--------|--------|--------|--------|
|	respCode	|	返回码	|		|	M	|		|
|	respMsg	|	返回码	|		|	M	|		|
|	orderList	|	交易数据	|		|	M	|		|
|	orderList格式如下：		|
|	hostLsNo	|	交易流水号	|		|	C	|		|
|	tranType 	|	交易类型    	|		|	C	|	1:消费 5:预授权确认 51:缴费 12:撤销 9:退费 835:退费 833:退费	|
|	payMode	|	支付方式	|		|	C	|	0:银行卡收单 1:NFC 2:小额双免 3:银联二维码 4:扫码 5:其他	|
|	settleCycle	|	结算类型	|	|	C	|	T1:工作日结算 D0:当日结算 D1:自然日结算 |
|	acqMerId	|	商户编号	|		|	C	|		|
|	merchantName	|	商户名称	|		|	C	|		|
|	terminalId	|	终端号	|		|	C	|		|
|	respCode	|	交易应答码	|		|	C	|		|
|	errinfo	|	应答描述	|		|	C	|		|
|	tranRrn	|	渠道参考号	|		|	C	|		|
|	cupsMerId	|	银联商户编号	|		|	C	|		|
|	cupsMerIdName	|	银联商户名称	|		|	C	|		|
|	cupsTid	|	银联终端号	|		|	C	|		|
|	localSysDate	|	交易日期	|		|	C	|		|
|	localSysTime	|	交易时间	|		|	C	|		|
|	cardNo	|	交易卡号	|		|	C	|		|
|	tranAmt	|	交易金额	|		|	C	|		|

#### **联动响应报文示例**

```json
{
	"respCode": "0000",
	"respMsg": "处理成功.",
	"orderList": [
		{
			"hostLsNo": "0100151212",
			"tranType": "1",
			"payMode": "0",
			"settleCycle": "T1",

		},
		{
			"hostLsNo": "0100151214",
			"tranType": "5",
			"payMode": "4",
			"settleCycle": "T1",

		},
		{
			"hostLsNo": "0100151216",
			"tranType": "835",
			"payMode": "4",
			"settleCycle": "T1",

		}
	]
}
```


