# 可用终端查询

#### **简要描述：** 

- 查询可用终端的厂商、终端型号和SN号。

#### **请求URL：** 
- 服务商->联动优势
`{交易服务根地址}/merchants/selectAgentSnNumbers`

#### **请求方式：**

- POST 

#### **请求参数：** 


|	字段	 |	名称	  |	长度  	|	必填  	|	说明	  |
|:--------:|:--------:|:--------:|:--------:|:--------|
|  acqSpId  | 服务商编号 |   10   |  M   | 服务商编号(联动平台分配) |
|   provId   |  厂商编号  |   3   |  O   |                          |
|   modelId   |  终端型号编号  |   4   |  O   |                          |
| signature |   签名串   | 无限制 |  M   | 见签名规则               |

####  **商户请求报文示例**

```json
{
    "acqSpId":"Y471790403",
    "signature":"LH9tnH9Jvn6X0UW5dSAKv2OFCFghsZNIQ8bZOu4Ay4JBss9if9ctPNg+z1BVrCiZL2XbrD2twZA0neC/TP6APtVrylTpa1Y0Udy9Kj1s2sLjbLDciOrN0unFH4VTNImBpuy/Q7+m1Nswy+0ThxP2RIAZkYJaUrs6cCy7MsFBkVI="
}
```

####  **返回参数说明** 

一级字段说明

| 字段           | 名称             | 长度 | 必填 | 说明                                     |
| -------------- | ---------------- | ---- | ---- | ---------------------------------------- |
| respCode       | 返回码           | 8    | M    | 返回码                                   |
| respMsg        | 返回信息         | 128  | M    | 返回信息                                 |
| terminalList | 可用终端集合 |      | C    | 可用终端数组 |
| signature | 签名     | 256  | M    | 参见签名机制                  |

二级字段说明（可用终端集合json字段说明）

| 字段        | 名称             | 长度 | 必填 | 说明           |
| ----------- | ---------------- | ---- | ---- | -------------- |
| agentModelList   | 终端型号集合 |      | M    | 同个厂商下终端型号数组 |
| provId   | 厂商编号 |      | M    |  |
| provName | 厂商名称   |      | M    |                |

三级字段说明（终端厂商集合json字段说明）

| 字段        | 名称             | 长度 | 必填 | 说明           |
| ----------- | ---------------- | ---- | ---- | -------------- |
| modelId   | 终端型号编号 |      | M    | 终端型号编号 |
| modelName   | 终端型号名称 |      | M    |  |
| snNumbers | 终端SN集合  |      | M    |                |


#### **联动响应报文示例**

```json
{
	"respCode": "0000",
	"respMsg": "处理成功.",
	"signature":"e+i3/vWTamt4gqdAfwOMSnDsiPKvP31Q1seDO/be/CHGsP4ujfUsrqe+Vc6gFWXM+abuUbKlXiywenpqO3C4W5Adv9dC+60pSexHrV1ItaSvFTx6sPyZGV8gu+173VVpdbgECN0KbCSXJgHESFFciU/dfyHxAvxlauqvmE9WNN0=",
	"terminalList": [
		{
			"agentModelList": [
				{
					"modelId": "14",
					"modelName": "新大陆SP50公司",
					"snNumbers": [
						"XDL800008",
						"XDL800022",
						"XDL800019",
						"XDL800018",
						"XDL800017",
						"XDL800016",
						"XDL800014",
						"XDL800015",
						"XDL800012",
						"XDL800011",
						"XDL800013",
						"XDL800010",
						"XDL800009"
					]
				}
			],
			"provId": "0",
			"provName": "王世院"
		},
		{
			"agentModelList": [
				{
					"modelId": "16",
					"modelName": "旺POS3",
					"snNumbers": [
						"WangsPOS300003",
						"WangsPOS300004",
						"WangsPOS300005"
					]
				},
				{
					"modelId": "20",
					"modelName": "世麦P2000",
					"snNumbers": [
						"SMP2000test007",
						"SMP2000test008"
					]
				}
			],
			"provId": "2",
			"provName": "智能POS"
		},
		{
			"agentModelList": [
				{
					"modelId": "12",
					"modelName": "D1迁移新大陆",
					"snNumbers": [
						"00000001",
						"abbcdabb"
					]
				}
			],
			"provId": "C",
			"provName": "D1迁移新大陆"
		}
	]
}
```



#### 返回码说明

| 返回码 |        返回描述        |
| :----: | :--------------------: |
|   00   |      查询成功      |

