# 商户增加终端
    
**简要描述：** 
- 商户入网机具来源为代理商自备的商户，并且审核通过后，调用该接口增加终端。


**请求URL：** 
- 服务商->联动优势
`{交易服务根地址}/merchants/addTerminals`
 
**请求方式：**

- POST 

**请求参数：** 


|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|:--------:|:--------:|:--------:|:--------:|:--------|
|	acqSpId	|	服务商编号	|	10	|	M	|	服务商编号(联动平台分配)	|
|	merId 	|	报备编号	|	17	|	M	|	 	|
|	terminals	|	终端SN	|		|	C	|	terminalType为2时，通过增机接口增加终端<br>merchantType=1、2时,终端个数最多为5个;<br>merchantType=3时,暂不支持终端;<br>格式为provId#modelId#snNumber-snNumber<br>=provId#modelId#snNumber-snNumber,<br>eg：8#02#0000010483433721-00001<br>=8#01#SMP2000test008	|



 **商户请求报文示例**

```json

{
    "acqSpId":"Y471790403",
    "merId":"M2020040900003222",
    "terminals":"1#14#ForSaasSNs1111111111000000000004",
    "signature":"lPtRFW5IhuUDQem69W3h24Me3FlC83t+GySuspDvCCXecqjYI380Q5NcMFMqaMtZDytyiYlkDu9XJv8hHzF3Smvtnsjr/88M41mPdDq/QcVu6vzoQKyPy5fKgf1AKWLGty/bC7OYk3JCkUGMdShMAftHBB6ByVjOt0Cxg0jfCUQ="
}

```

 **返回参数说明** 
 
|	字段	|	名称	|	长度	|	必填	|	说明	|
|--------|-------|--------|--------|--------|
|	respCode	|	返回码	|	8	|	M	|	返回码	|
|	respMsg	|	返回信息	|	128	|	M	|	返回信息	|

