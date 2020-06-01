# 服务商平台-查询T1/D1结算结果

**简要描述：** 
- 服务商可以通过该接口，查询商户（T1,D1）对应的结算结果。

**请求URL：** 
- 服务商->联动优势
`{交易服务根地址}/pay/queryT1Settle`

**请求方式：**
- POST 

**请求参数：** 

|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|:--------:|:--------:|:--------:|:--------:|:--------|
|	acqSpId	|	代理商编号	|	10	|	M	|	代理商编号(联动平台分配)	|
|	acqMerId	|	商户号	|	8	|	M	|	商户号(联动平台分配)	|
|	settleDate	|	结算日期	|	8	|	M	|	结算日期，格式yyyyMMdd	  |
|	signature	|	签名	|	256	|	M	|	参见签名机制	|

 **商户请求报文示例**

```json
{
	"acqMerId": "41509208",
	"acqSpId": "Y471790403",
	"settleDate": "20200401",
	"signature": "TJ1Ibz5jkLMVnWRw4TCbasm8y1OQWaYe9pvv95Y/KTt3o++dQ97/eimWkHin8yUsZtHbzNGkT7l0tZCZ0EY/oBBRzftp6qPGa0kSj/vVrq/sfUJccgfwpSwuhdW1Zwiy/M/hudtTc4u3taeTjekYwnuZSpEnvGPXF4GNhFFPT4o="
}
```

 **返回参数说明** 
 
 一级字段说明

|	字段	|	名称	|	长度	|	必填	|	说明	|
|--------|--------|--------|--------|--------|
|	respCode	|	返回码	|	8	|	M	|	返回码	|
|	respMsg	|	返回信息	|	128	|	M	|	返回信息	|
|	settleList	|	结算集合	|		|	C	|	结算集合	|

二级字段说明（结算集合json字段说明）

| 字段        | 名称             | 长度 | 必填 | 说明           |
| ----------- | ---------------- | ---- | ---- | -------------- |
| settleTime   | 结算时间 |      | C    | 结算时间（格式：yyyy-MM-dd HH:mm:ss） |
| settleState   | 结算状态 |      | C    | 00:支付成功；<br>01:支付失败；<br>02:处理中；<br>03:退款中；<br>04:交易撤销；<br>05:待确认；<br>06:已冻结；<br>07:待解冻；<br>08:交易失败退款中；<br>09:交易失败退单成功；<br>10:余额不足；<br>11:付款防重已拦截；<br>99:未知状态； |
| settleAmt | 结算金额   |      | C    |       单位：分         |

 **返回报文示例**

```json
{
    "respCode": "00",
    "respMsg": "处理成功",
    "signature": "lkD1PuEv/tb+f1JNWRAC8EHIStWi/smtpCZBXRYwbHJsO4Rt+wzNMtal4apAqvQqH8hVgJLJF7OxLby4pwTdfcbAfuOJQ8MR4K8oWoBXkeLIFKQJhSda3qqxHtZBVz5d0OGsqxgdNs0oSIC44W5Ep2TXkTGcopDfi8K+mi2v5es=",
    "settleList":[{"settleTime":"2020-04-01 08:56:31","settleState":"01","settleAmt":"100"},{"settleTime":"2020-04-01 09:01:29","settleState":"01","settleAmt":"100"}]
}
```



**备注** 

- 返回码除了成功（00）需要做对应的逻辑处理，其他返回码均不需要做处理
- 返回码为成功（00），根据结算状态settleState判断结算是否成功。

