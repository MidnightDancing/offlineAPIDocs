# 验证签章验证码

**简要描述：** 

- 商户->联动优势
- 商户获取签章验证码之后，通过该接口验证签章验证码，进行签章。

**请求URL：** 

`{交易服务根地址}/sign/doVerifyCodeSign`

**请求方式：**

- POST 

**请求报文参数：** 

|    字段    |    名称    | 长度 | 必填 | 说明                                                         |      |
| :--------: | :--------: | :--: | :--: | :----------------------------------------------------------- | ---- |
|  acqSpId   | 代理商编号 |  10  |  M   | 代理商编号(联动平台分配)                                     |      |
|   merId    |  报备编号  |  20  |  M   | 商户信息录入成功，联动返回的                                 |      |
| transCaId  | 缓存事务ID | 128  |  M   | 获取签章验证码返回                                           |      |
| verifyCode | 短信验证码 |  6   |  M   | 联动获取签章验证码时给签约手机号下发短信，回填对应的短信，短信有效时间10分钟 |      |
| signature  |    签名    | 256  |  M   | 参见签名机制                                                 |      |

 **商户请求报文示例**

```json
{
	"acqSpId": "Y000000001",
	"merId": "B20190808111111111",
    "transCaId": "83828a51-e638-4ef2-9944-5fe85e50c6c5",
	"verifyCode": "125645",
	"sign": "MMokEDpwU7vHy2AP2o4esBBLHRdsn3BorIdSHkbGpEOEAr9USwj++l9K8lyder2Yy/WmtEhyEL9xKiX4mS14ds7OKdzX6tGzy4qc2lsdRRSe5l9I9Gj7NdCLsq1TUccr2gnGibvu9UaAsCUCNmJqBrSW0YUl7+mVND9FFGecBe0="
}
```

 **返回参数说明** 

| 字段      | 名称     | 长度 | 必填 | 说明                          |
| --------- | -------- | ---- | ---- | :---------------------------- |
| respCode  | 返回码   | 8    | M    | 返回码                        |
| respMsg   | 返回信息 | 128  | M    | 返回信息                      |
| pdf       | pdf文件  | 变长 | C    | 成功时，pdf文件byte[]方式返回 |
| signature | 签名     | 256  | M    | 参见签名机制                  |

