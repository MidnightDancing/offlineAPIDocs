# 获取电子签约挑战码

**简要描述：** 

- 商户->联动优势
- 商户通过上传完资质之后，通过该接口获取电子签约挑战码

**请求URL：** 

`{交易服务根地址}/sign/getVerifyCode`

**请求方式：**

- POST 

**请求报文参数：** 

|   字段    |    名称    | 长度 | 必填 | 说明                         |
| :-------: | :--------: | :--: | :--: | :--------------------------- |
|  acqSpId  | 代理商编号 |  10  |  M   | 代理商编号(联动平台分配)     |
|   merId   |  报备编号  |  20  |  M   | 商户信息录入成功，联动返回的 |
| signature |    签名    | 256  |  M   | 参见签名机制                 |

 **商户请求报文示例**

```json
{
	"acqSpId": "Y000000001",
	"merId": "B20190808111111111",
	"sign": "MMokEDpwU7vHy2AP2o4esBBLHRdsn3BorIdSHkbGpEOEAr9USwj++l9K8lyder2Yy/WmtEhyEL9xKiX4mS14ds7OKdzX6tGzy4qc2lsdRRSe5l9I9Gj7NdCLsq1TUccr2gnGibvu9UaAsCUCNmJqBrSW0YUl7+mVND9FFGecBe0="
}
```

 **返回参数说明** 

| 字段      | 名称       | 长度 | 必填 | 说明                                 |
| --------- | ---------- | ---- | ---- | :----------------------------------- |
| respCode  | 返回码     | 8    | M    | 返回码                               |
| respMsg   | 返回信息   | 128  | M    | 返回信息                             |
| transCaId | 缓存事务ID | 128  | C    | 成功时返回，用于验证签章验证码时标识 |
| signature | 签名       | 256  | M    | 参见签名机制                         |

