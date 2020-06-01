# 商户信息修改

**简要描述：** 

- 在审核驳回状态下，商户可以调用此接口对入网的商户信息进行修改
- 此接口只支持一次调用，调用成功之后即切换至待修改图片状态。
- 如无需修改图片，调用期间需将【结束标识】设置为0，即可直接提交至修改待审核状态

**修改流程**

入网环节流程与修改流程如下，本接口为图中【商户修改信息】部分。

![avatar](http://www.renjiework.com/umpay/liucheng.png )

**请求URL：** 

- 服务商->联动优势
  `{交易服务根地址}/merchants/modifyMerInfo

**请求方式：**

- POST 

**请求参数：** 


|           字段           |       名称       | 长度 | 必填 | 说明                                                         |
| :----------------------: | :--------------: | :--: | :--: | :----------------------------------------------------------- |
|         endFlag          | 图片上传结束标识 |  1   |  M   | 0--（结束标识）修改信息后直接提交至修改待审核状态<br>1--（继续标识）修改信息后切换至资质修改状态。（默认为1） |
|         acqSpId          |    服务商编号    |  10  |  M   | 服务商编号(联动平台分配)                                     |
|         acqMerId         |     商户编号     |  8   |  M   | 商户查询接口返回                                             |
|       merchantName       |     商户简称     |  16  |  C   | 商户交易显示名称                                             |
|         backUrl          |   回调通知地址   | 200  |  C   | 商户入网成功回调地址                                         |
|   businessLicenseCode    |   营业执照编号   |  32  |  C   |                                                              |
|   businessLicenseName    |   商户经营名称   |  64  |  C   |                                                              |
| businessLicenseEffective | 营业执照生效日期 |  8   |  C   |                                                              |
|  businessLicenseExpired  | 营业执照失效日期 |  8   |  C   | 长期有效身份证此处请填写“永久”！！！                         |
|     businessAddress      | 所属地址详细地址 | 256  |  C   |                                                              |
|        lawyerName        |     法人姓名     |  32  |  C   |                                                              |
|      lawyerCertType      |   法人证件类型   |  1   |  C   | 1：身份证	;5:护照                                         |
|       lawyerCertNo       |    法人证件号    |  20  |  C   |                                                              |
|     certNoEffective      |   证件生效日期   |  8   |  C   | 只有merchantType=1、2时方可传入此字段，为企业法人证件有效日期<br> Ex:20190101 |
|      certNoExpired       |   证件失效日期   |  8   |  C   | 只有merchantType=1、2时方可传入此字段，为企业法人证件失效日期<br> Ex:20390101，长期有效身份证此处请填写“永久”！！！ |
|      contactPerson       |    联系人姓名    |  10  |  C   |                                                              |
|     contactCertType      |  联系人证件类型  |  1   |  C   | 1：身份证；5：护照，默认1:身份证                             |
|     contactPersonId      |   联系人证件号   |  18  |  C   |                                                              |
|       contactPhone       |   联系人手机号   |  11  |  C   |                                                              |
|        serviceTel        |     客服电话     |  12  |  C   |                                                              |
|          email           |       邮箱       |  64  |  C   |                                                              |
|    settleAccountType     |   结算账户类型   |  1   |  C   | 1:对公账户 2:法人账户3：被授权人账户<br>merchantType=3时必填2 |
|     settleAccountNo      |     结算账号     |  30  |  C   |                                                              |
|      settleAccount       |     结算户名     | 256  |  C   |                                                              |
|        settleIdNo        |     身份证号     |  18  |  C   |                                                              |
|    settleIdEffective     |  身份证生效日期  |  8   |  C   | 只有merchantType=3时方可传入此字段Ex:20190101                |
|     settleIdExpired      |  身份证失效日期  |  8   |  C   | 只有merchantType=3时方可传入此字段Ex:20390101，长期有效身份证此处请填写“永久”！！！ |
|         openBank         |   开户银行名称   |  4   |  C   | 附件中编码，如修改为对公账户必填                             |
|       openSubBank        |   开户支行名称   |  64  |  C   |                                                              |


 **商户请求报文示例**

```json
{

}

```

 **返回参数说明** 

| 字段     | 名称         | 长度 | 必填 | 说明                                                         |
| -------- | ------------ | ---- | ---- | ------------------------------------------------------------ |
| respCode | 返回码       | 8    | M    | 返回码                                                       |
| respMsg  | 返回信息     | 128  | M    | 返回信息                                                     |
| modifyId | 修改编号     |      | C    | 成功受理修改后返回                                           |
| endFlag  | 修改结束标识 | 1    | M    | 成功受理修改后返回<br>0--（结束标识）最后一张待修改图片上传完毕，提交至待审核状态<br>1--（继续标识）后续仍然有图片需要修改。（默认为1） |



**注意事项**

1、如果修改（结算类型，结算卡号，开户行）三者之一，则这三者必须全部上传

2、如果修改（结算户名，身份证号）两者之一，则这两者必须全部上传

3、如果卡号和户名仅修改其一，需要控制修改字段与原入网另一字段匹配，否则三要素校验不过

4、关于身份证/证件生效日期和失效日期：

如果商户类型为小微商户，不被允许修改certNoEffective字段和certNoExpired字段，请修改settleIdEffective字段和settleIdExpired字段；

如果商户类型为企业或个体商户，不被允许修改settleIdEffective字段和settleIdExpired字段，请修改certNoEffective字段和certNoExpired字段。