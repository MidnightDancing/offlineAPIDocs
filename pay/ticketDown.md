# 电子小票接口

**简要描述：** 

- 商户收取到POS交易通知后，可以依据交易时间与交易索引获取电子小票
- 此接口支持多次调用，首次会合成小票，多次调用返回第一次合成的小票。
- 如无上送电子签名则合成小票上不展示电子签名。
- 其余事项详见【注意事项】

**请求URL：** 

- 服务商->联动优势
  `{交易服务根地址}/ticket/ticketDown

**请求方式：**

- POST 

**请求参数：** 

|    字段    |    名称    | 长度 | 必填 | 说明                                             |
| :--------: | :--------: | :--: | :--: | :----------------------------------------------- |
|  acqSpId   | 服务商编号 |  10  |  M   | 服务商编号(联动平台分配)                         |
|  acqMerId  |  商户编号  |  8   |  M   | 商户编号（联动平台分配，通过商户信息查询口获取） |
| transIndex |  交易索引  |      |  M   | 商户交易通知接口返回                             |
|  payTime   |  交易时间  |  14  |  M   | 商户交易通知接口返回                             |



**返回参数说明** 

| 字段              | 名称             | 长度 | 必填 | 说明                                                         |
| ----------------- | ---------------- | ---- | ---- | ------------------------------------------------------------ |
| respCode          | 返回码           | 8    | M    | 返回码                                                       |
| respMsg           | 返回信息         | 128  | M    | 返回信息                                                     |
| ticketImgName     | 电子小票图片名称 |      | C    | 电子小票图片名称                                             |
| ticketImgData     | 电子小票图片数据 |      | C    | 电子小票图片数据（需进行base64解码后，存储为文件）           |
| ticketSignImgName | 电子签名图片名称 |      | O    | 电子签名图片名称（如未上传电子签名则返回为空字符串）         |
| ticketSignImgData | 电子签名图片数据 |      | O    | 电子签名图片数据（如未上传电子签名则返回为空字符串）（需进行base64解码后，存储为文件） |
| binBankName       | 发卡行名称       |      | C    |                                                              |
| binBankId         | 发卡行号         |      | C    | 见入网接口附件一中对应关系                                   |
| tradeType         | 交易类别         |      | C    | 枚举值见注意事项                                             |
| cardType          | 卡类别           | 2    | C    | 00：借记卡   01：贷记卡 99：其他                             |
| cardNo            | 卡号（脱敏）     |      | C    | 前六后四脱敏卡号                                             |
| batchCode         | 批次号           |      | C    |                                                              |
| certificateNo     | 凭证号           |      | C    |                                                              |
| refCode           | 参考号           |      | C    |                                                              |
| localSysDate      | 交易日期         | 8    | C    |                                                              |
| localSysTime      | 交易时间         | 6    | C    |                                                              |
| intTermCode       | 终端号           |      | C    |                                                              |
| innerReferno      | 内部参考号       |      | C    |                                                              |
| merchantName      | 商户简称         |      | C    |                                                              |
| amount            | 交易金额         |      | C    |                                                              |
| agentId           | 服务商编号       |      | C    |                                                              |
| acqMerId          | 商户编号         |      | C    |                                                              |

**示例**

```json
【请求示例】

{
	"acqMerId": "41509208",
	"acqSpId": "Y471790403",
	"transIndex": "0184143115482033",
    "payTime": "20200702143115",
	"signature": "……………………"
}

【响应示例】

{
	"respCode": "00",
	"respMsg": "处理成功",
	"signature": "…………",
	"ticketImgName": "0184143115482033.jpg",
	"ticketImgData": "…………",
	"ticketSignImgName": "0184143115482033.bmp",
	"ticketSignImgData": "…………",
	"binBankName": "平安银行",
	"binBankId": "B041",
	"tradeType": "消费（SALE）",
	"cardType": "01",
	"cardNo": "622155******4997",
	"batchCode": "000001",
	"certificateNo": "000001",
	"refCode": "",
	"localSysDate": "20200702",
	"localSysTime": "143115",
	"intTermCode": "61102242",
	"innerReferno": "000000009741",
	"merchantName": "xd1529最全商户02无D0",
	"amount": "2000.00",
	"agentId": "Y471790403",
	"acqMerId": "41509208"
}
```



【注意事项】

1、交易类别相对应枚举值如下

>"消费（SALE）"</br>
>"消费撤销（VOID）"</br>
>"银联二维码撤销"</br>
>"支付宝撤销"</br>
>"微信撤销"</br>
>"退货（REFUND）"</br>
>"银联二维码退款"</br>
>"支付宝退款"</br>
>"微信退款"</br>
>"微信被扫"</br>
>"支付宝被扫"</br>
>"银联二维码被扫"</br>
>"微信主扫"</br>
>"支付宝主扫"</br>
>"银联二维码主扫"</br>
>"预授权撤销（CANCEL）"</br>
>"预授权完成撤销（COMPLETE VOID）"</br>
>"预授权（AUTH）"</br></br>
>"预授权完成请求（AUTH COMPLETE）"</br>
>"预授权完成通知（AUTH SETTLEMENT）"</br>
>"其他"</br>





