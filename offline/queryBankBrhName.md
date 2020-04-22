# 服务商平台-查询支行全称

**简要描述：**
- 根据支行联行号，查询对应的支行全称。


**请求URL：**
- 服务商->联动优势
`{交易服务根地址}/merchants/queryBankBrhName`

**请求方式：**

- POST

**请求参数：**


|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|:--------:|:--------:|:--------:|:--------:|:--------|
|	acqSpId	|	服务商编号	|	10	|	M	|	服务商编号(联动平台分配)	|
|	cnapsCode 	|	支行联行号	|	12	|	M	|	 	|



 **商户请求报文示例**

```json

{
    "acqSpId":"Y471790403",
    "cnapsCode":"105110052281",
    "signature":"jN4owAEraE7SoUUPVFAkOnP/nSVdnJtaIghH2oVy2yhqnLKD/GbTzGbVpmPfyJpAEVS5pc97XjnXm5ZrNkrSYYVuE+NIUZ/4hmIm2+9LUPahC7HP1gAJFdA5eJUeK0oNdQ96N/6Z2FHoqOXbTMZjIwwKkQNKfrFDlN2Qq/EhTok="
}

```

 **返回参数说明**

|	字段	|	名称	|	长度	|	必填	|	说明	|
|--------|-------|--------|--------|--------|
|	respCode	|	返回码	|	8	|	M	|	返回码	|
|	respMsg	|	返回信息	|	128	|	M	|	返回信息	|
|	bankBrhName	|	支行全称	|	128	|	C	|	支行全称	|

