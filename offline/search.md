# 服务商平台-商户资质查询

#### **简要描述：** 

- 商户资质文件查询。

#### **请求URL：** 
- 服务商->联动优势
`{交易服务根地址}/imgmanage/search`

#### **请求方式：**

- POST 

#### **请求参数：** 

|   字段    |    名称    |  长度  | 必填 | 说明                     |
| :-------: | :--------: | :----: | :--: | :----------------------- |
|  acqSpId  | 服务商编号 |   10   |  M   | 服务商编号(联动平台分配) |
|   merId   |  报备编号  |   17   |  M   |                          |
| signature |   签名串   | 无限制 |  M   | 见签名规则               |

####  **商户请求报文示例**

```json
{
    "acqSpId":"Y471790403",
    "merId":"M2019110100000555",
    "signature":"CBr2Dui55aRxyiUJoWCxckL8lWn7UeBxvAJFsV2hrtFDvVSOp4v4cgUPc1NP8K3rA="
}
```

####  **返回参数说明** 

一级字段说明

| 字段           | 名称             | 长度 | 必填 | 说明                                     |
| -------------- | ---------------- | ---- | ---- | ---------------------------------------- |
| respCode       | 返回码           | 8    | M    | 返回码                                   |
| respMsg        | 返回信息         | 128  | M    | 返回信息                                 |
| resDate        | 响应日期         | 8    | M    |                                          |
| resTime        | 响应时间         | 6    | M    |                                          |
| merLictypeList | 商户资质图片集合 |      | C    | 资质图片信息数组<br>成功必返回，失败不返 |

二级字段说明（资质图片信息json字段说明）

| 字段        | 名称             | 长度 | 必填 | 说明           |
| ----------- | ---------------- | ---- | ---- | -------------- |
| licCnName   | 资质图片中文名称 |      | M    | 见资质图片说明 |
| licEnName   | 资质图片英文名称 |      | M    | 见资质图片说明 |
| licFileName | 资质图片文件名   |      | M    |                |
| licState    | 图片状态         |      | M    | 2-成功         |

#### **联动响应报文示例**

```json
{
    "resDate": "20191101",
    "resTime": "170745",
    "respCode": "00",
    "respMsg": "图片查询成功.",
    "merLictypeList": [
        {
            "licCnName": "法人身份证正面",
            "licEnName": "idCardFront",
            "licFileName": "idCardFront.jpg",
            "licState": "2"
        },
        {
            "licCnName": "法人身份证反面",
            "licEnName": "idCardBack",
            "licFileName": "idCardBack.jpg",
            "licState": "2"
        },
        {
            "licCnName": "门店门头照",
            "licEnName": "storeHeadPhoto",
            "licFileName": "storeHeadPhoto.jpg",
            "licState": "2"
        },
        {
            "licCnName": "门店内景照",
            "licEnName": "storeHallPhoto",
            "licFileName": "storeHallPhoto.jpg",
            "licState": "2"
        },
        {
            "licCnName": "门店收银台照",
            "licEnName": "storeCashierPhoto",
            "licFileName": "storeCashierPhoto.jpg",
            "licState": "2"
        },
        {
            "licCnName": "商户收单协议照片",
            "licEnName": "acquiringAgreementPhoto",
            "licFileName": "acquiringAgreementPhoto_1.jpg",
            "licState": "2"
        },
        {
            "licCnName": "商户收单协议照片",
            "licEnName": "acquiringAgreementPhoto",
            "licFileName": "acquiringAgreementPhoto_2.jpg",
            "licState": "2"
        },
        {
            "licCnName": "商户收单协议照片",
            "licEnName": "acquiringAgreementPhoto",
            "licFileName": "acquiringAgreementPhoto_3.jpg",
            "licState": "2"
        },
        {
            "licCnName": "签约授权书",
            "licEnName": "signAuthLetterPhoto",
            "licFileName": "signAuthLetterPhoto.jpg",
            "licState": "2"
        },
        {
            "licCnName": "银行卡正面",
            "licEnName": "bankCardPhotoFront",
            "licFileName": "bankCardPhotoFront.jpg",
            "licState": "2"
        },
        {
            "licCnName": "银行卡反面",
            "licEnName": "bankCardPhotoBack",
            "licFileName": "bankCardPhotoBack.jpg",
            "licState": "2"
        }
    ]
}
```



#### 返回码说明

| 返回码 |        返回描述        |
| :----: | :--------------------: |
|   00   |      图片查询成功      |
|   17   |  未查询到对应资质图片  |
|   18   |      请求参数不全      |
|   19   |      图片查询异常      |
|   99   | 查询系统异常或其他异常 |

#### 资质图片说明

|     图片中文名称     |        图片英文名称        |
| :------------------: | :------------------------: |
|    法人身份证正面    |        idcardfront         |
|    法人身份证反面    |         idCardBack         |
|      银行卡正面      |     bankCardPhotoFront     |
|      银行卡反面      |     bankCardPhotoBack      |
|      开户许可证      | openingLicenseAccountPhoto |
|   商户收单协议照片   |  acquiringAgreementPhoto   |
|      签约授权书      |    signAuthLetterPhoto     |
|     营业执照照片     |    businessLicensePhoto    |
|      门店门头照      |       storeHeadPhoto       |
|      门店内景照      |       storeHallPhoto       |
|     门店收银台照     |     storeCashierPhoto      |
| 结算授权人身份证正面 |   settleAuthIdcardfront    |
| 结算授权人身份证反面 |    settleAuthIdCardBack    |
|      结算授权函      |   settleAuthLetterPhoto    |

