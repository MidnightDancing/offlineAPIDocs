# 商户入网审核结果通知

**简要描述：** 
- 联动优势把商户开户审核结果通知服务商
- 联动优势最多通知商户4次，每次间隔1分钟

**请求URL：** 
- 联动优势->服务商
`{商户接收通知地址}`，通知地址以商户入网接口传入回调通知地址{backUrl}为准

**请求方式：**

- POST 

**请求报文参数：** 联动通知商户数据

|   字段   |      名称      | 长度 | 必填 | 说明                               |
| :------: | :------------: | :--: | :--: | :--------------------------------- |
| acqSpId  |   代理商编号   |  10  |  M   | 代理商编号(联动平台分配)           |
| acqMerId |     商户号     |  8   |  M   | 商户号(联动平台分配)               |
| merId    |     报备编号   |  17  |  M   | 报备编号               		|
| retCode  | 审核结果通知码 |  8   |  M   | 0000：审核成功<br />0001：审核驳回 |
|  retMsg  |  审核结果消息  | 128  |  M   |                                    |

 **商户请求报文示例**

```json
{
	"acqMerId": "30000102",
	"acqSpId": "Y000000001",
	"retCode": "0000",
	"retMsg": "审核成功"
}
```

 **返回参数说明：**商户返回数据

| 字段      | 名称     | 长度 | 必填 | 说明                                   |
| --------- | -------- | ---- | ---- | -------------------------------------- |
| acqMerId  | 商户号   | 8    | M    | 商户号(联动平台分配)                   |
| respCode  | 返回码   | 8    | M    | 0000：收到通知<br />其他：继续重复通知 |
| respMsg   | 返回信息 | 128  | M    | 返回信息                               |
| signature | 签名     | 256  | M    | 参见签名机制                           |





**接收说明**

【接收格式】HTTP   POST  

【接收示例】servlet（JAVA版本）

```java
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("*************************【param接口】收到POST请求信息***start**************************");
        //解析接收到参数
		System.out.println("**【param数据】*********start***********");
		Enumeration<String> en = request.getParameterNames();
		while(en.hasMoreElements()) {
			String key = en.nextElement();
			System.out.println("**【param数据】[key]=["+key+"]  [value]=["+URLDecoder.decode(request.getParameter(key))+"]");
		}
		System.out.println("**【param数据】*********end***********");
        //解析组织返回参数
		JSONObject json = new JSONObject();
		json.put("respCode", "0000");
		json.put("respMsg", "收到审核通知");
        json.put("acqMerId", URLDecoder.decode(request.getParameter("acqMerId")));
		response.setContentType("application/json; charset=utf-8");
		System.out.println("**【json数据】回复响应结果："+JSONUtil.toJsonStr(json));
	    response.getWriter().write(JSONUtil.toJsonStr(json));
		System.out.println("*************************【param接口】收到POST请求信息***end**************************");
	}                                                                                                                          
```

【示例对应日志】

```verilog
//示例日志打印如下
 *************************【param接口】收到POST请求信息***start**************************
**【param数据】*********start***********                      
**【param数据】[key]=[retCode]  [value]=[0001]                
**【param数据】[key]=[acqSpId]  [value]=[Y471790403]          
**【param数据】[key]=[acqMerId]  [value]=[42521943]           
**【param数据】[key]=[retMsg]  [value]=[merId1参数有误]       
**【param数据】[key]=[merId]  [value]=[M2020031900002754]     
**【param数据】*********end***********                        
**【json数据】回复响应结果：{"respMsg":"收到审核通知","respCode":"0000","acqMerId":"42521943"}
 *************************【param接口】收到POST请求信息***end**************************   
```

