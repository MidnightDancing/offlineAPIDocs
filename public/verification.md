 # 报文格式说明及签名验签规则

**    **<br>您只需花五分钟时间，详细阅读本文档，可以节省1~10小时的联调时间。<br>**
    
## 1.3.1综述

​		平台采用java语言开发，信息交互采用HTTP协议，以json格式进行交互，基于（SHA1withRSA）的算法保障信息交互安全性。

## 1.3.2 术语说明

**	**请求发起方**：**一般指用户，用户（商户或服务商）使用平台功能，发起对平台接口调用时的主动发起者。

**	**请求接收方**：一般**指平台，平台在接收到发起方请求后，接收请求进行响应。

**	**待签串/明文串**:** 接口所需的参数按一定规则拼接的，需要进行签名的字符串。

**	**签名串/密文串:** **待签字符串通过商户私钥加签后形成的密文串。

## 1.3.3 交互说明

​	发起方拼接明文串。

​	发起方使用自己私钥进行签名生成请求密文串。

​	发起方发起接口调用请求。

​	接收方使用发起方公钥进行解密，并对请求进行响应。

​	接收方对响应信息使用平台私钥进行签名处理，并返回至发起方。

​	发起方使用平台公钥对签名进行验签，使用返回信息进行业务。

## 1.3.4 请求加签

【1】参数排序

​		对接口要求的业务参数的key进行排序，排序规则为字典序（A-Z顺序）

​		（注：此处不包括要求的签名参数，即key为signature的业务参数）

~~~json
```
{
    "acqMerId":"41509208",
    "acqSpId":"Y471790403",
    "funCode":"ALIVE",
    "orderNo":"a12ddasdasdad23sd",
    "rpid":"123456789"
}
```
~~~

【2】明文串拼接

​		将有序的业务参数按如下格式拼接为明文串待签，（空字段不要参与加签与传送）

​		（key1=value1&key2=value2&key3=value3）

```http
eg:
acqMerId=41509208&acqSpId=Y471790403&funCode=ALIVE&orderNo=a12ddasdasdad23sd&rpid=123456789
```

【3】加签

​		对待签明文串进行加签，得到签名密文串。

```
eg:
BdA3TpMiw1Jxec0WVxU0EsTEtgKQeUwLhuK95BTjx6h/4MQmHP4Uig/hze2lo36iy29AcvvuKkyxpzxRtO6fxFPPyibwoXzrnKxIzplC9O2kcPjC3e25MHydgNr4p7FOLC500IitrnsJ+yPzegW2GC86NKEei3l6Qj02RRAkNRg=
```



【4】包装请求

​		将加签之后的签名字段放入请求的json中，key为“signature”，value为之前的签名串。

```json
{
    "acqMerId":"41509208",
    "acqSpId":"Y471790403",
    "funCode":"ALIVE",
    "orderNo":"a12ddasdasdad23sd",
    "rpid":"123456789",
  "signature":"BdA3TpMiw1Jxec0WVxU0EsTEtgKQeUwLhuK95BTjx6h/4MQmHP4Uig/hze2lo36iy29AcvvuKkyxpzxRtO6fxFPPyibwoXzrnKxIzplC9O2kcPjC3e25MHydgNr4p7FOLC500IitrnsJ+yPzegW2GC86NKEei3l6Qj02RRAkNRg="
}
```



## 1.3.5 验签

【1】解析回返json串

​		解析返回结果，识别其中各字段

```json
{
    "respCode": "00",
    "respMsg": "处理成功",
    "signature": "ggIIULxcVDrLZzM2Xl0+aMRKlv4VgoZNw/eRWTHdn2w1YFcuoGjq23AadIezOMftMvYIvJ/m5P5X2z2+vsSm71vnnsKjgHsBdzSxRVQiYgS4IJg7vNNHEb1vbZY/LulqSFHwDtPp1yHg9syw8/qyFP6dAmBqO27wsLIGrJJvrPU=",
    "platDate": "",
    "txnAmt": 1,
    "paySeq": "",
    "transactionId": "2019072518100000000001",
    "origRespCode": null,
    "origRespDescCode": "99"
}
```

【2】获取签名串

​		从返回字段中取出签名串字段，备用。

```
"signature": "ggIIULxcVDrLZzM2Xl0+aMRKlv4VgoZNw/eRWTHdn2w1YFcuoGjq23AadIezOMftMvYIvJ/m5P5X2z2+vsSm71vnnsKjgHsBdzSxRVQiYgS4IJg7vNNHEb1vbZY/LulqSFHwDtPp1yHg9syw8/qyFP6dAmBqO27wsLIGrJJvrPU="
```

【3】获取响应结果的待加签明文串

​		返回字段中将除签名串外其他字段全部排序，按字典序排序，将值不为空的各字段（“”或null）按如下规则进行拼接签名串，获得待加签明文串。

​		规则：value1|value2|value3

```
eg:
99|00|处理成功|2019072518100000000001|1
```

【4】响应结果加签

​		将【3】中获取的待加签明文串使用联动公钥进行加签，获得加签后的验签密文串。

```
eg:
ggIIULxcVDrLZzM2Xl0+aMRKlv4VgoZNw/eRWTHdn2w1YFcuoGjq23AadIezOMftMvYIvJ/m5P5X2z2+vsSm71vnnsKjgHsBdzSxRVQiYgS4IJg7vNNHEb1vbZY/LulqSFHwDtPp1yHg9syw8/qyFP6dAmBqO27wsLIGrJJvrPU=
```



【5】验签

​		比对加签后的验签密文串和响应的签名串是否一致，若一致则直接使用其他字段值进行后续操作，若不一致说明消息被篡改。

## 1.3.6 加签demo示例

```java
		TreeMap<String, Object> reqMap = new TreeMap<String, Object>();
		reqMap.put("orderNo","12345678987654321");
		reqMap.put("orderTime","20190725141912");
		reqMap.put("orderType","alipay");
		reqMap.put("txnAmt","1");
		
		reqMap.put("backUrl","http://www.baidu.com");
		reqMap.put("goodsInfo","一个月优酷会员");
		reqMap.put("paymentValidTime","600");
		
		reqMap.put("acqSpId","Y471790403");
		reqMap.put("acqMerId","41509208");

		
	    reqMap.remove("signature");
		//获取原请求签名
	    String signStr	= new StringBuilder(reqMap.toString().replace(", ", "&").replace("{", "").replace("}", "")).toString();
		// 验签
		//【待签名明文串】--signStr
		String sign = CertUtils.sign(signStr, CertUtils.getPrivateKey("D:/test.key.p8"),"UTF-8");
		//【签名密文串】--sign
		reqMap.put("signature", sign);
		//【最终报文串】--res
		String res=JSON.toJSONString(reqMap);

```

## 1.3.6.2 加签demo示例（数据嵌套）

```java
		TreeMap<String, Object> reqMer = new TreeMap<String, Object>();
		TreeMap<String, Object> reqRate = new TreeMap<String, Object>();
		TreeMap<String, Object> reqbankCardRateLevel1 = new TreeMap<String, Object>();
		reqMer.put("acqSpId", EnvConfig.acqSpId);
		reqMer.put("merchantName", "自然人测试商户2");
		reqMer.put("rate", reqRate);
		reqMer.put("wechatChannelId", "208493420");
		reqMer.put("alipayChannelId", "2088901023449763");

		//rate
		reqRate.put("feeRateAlipay", "0.51");	
		reqRate.put("feeRateWechatpay", "0.52");
		reqRate.put("bankCardRateLevel1", reqbankCardRateLevel1);
		
		//bankCardRateLevel1
		reqbankCardRateLevel1.put("feeRateUnionpayDebit", "0.50");
		reqbankCardRateLevel1.put("feeRateUnionpayDebitCap", "2000");
		reqbankCardRateLevel1.put("feeRateUnionpayCredit", "0.52");
		
	    reqMap.remove("signature");
		//获取原请求签名
	    String signStr	= new StringBuilder(reqMap.toString().replace(", ", "&").replace("{", "").replace("}", "")).toString();
		// 验签
		//【待签名明文串】--signStr
		String sign = CertUtils.sign(signStr, CertUtils.getPrivateKey("D:/test.key.p8"),"UTF-8");
		//【签名密文串】--sign
		reqMap.put("signature", sign);
		//【最终报文串】--res
		String res=JSON.toJSONString(reqMap);

```

## 1.3.7 验签demo

```java
public static boolean doCheckSign(String object) {
		Map<String, Object> treeMap = JSON.parseObject(JSON.toJSONString(JSON.parse(object)), TreeMap.class);
		// 【响应的签名】
		String signKey = (String) treeMap.get("signature");
		treeMap.remove("signature");
		// 【待签明文串】--去除响应签名后获取待签明文串
		StringBuilder sb = new StringBuilder();
		for (Entry<String, Object> entry : treeMap.entrySet()) {
			if (null != treeMap.get(entry.getKey()) && !"".equals(treeMap.get(entry.getKey()))) {
				sb.append(treeMap.get(entry.getKey())).append("|");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		String befSgin = sb.toString();
		//【验签】
		boolean signresult = false;
		try {
			signresult = SignTools.doCheckSign(certFilePath, serverCertFileName, befSgin, signKey);
		} catch (Exception e) {
			System.out.println("验签异常");
		}
		//【 验签结果】
		return signresult;
	}
```

## 1.3.7.2 字段验签demo（数据嵌套）

```java
public static boolean doCheckSign(String object) {
        Map<String, Object> treeMap = JSON.parseObject(JSON.toJSONString(JSON.parse(object)), TreeMap.class);
        // 【响应的签名】
        String signKey = (String) treeMap.get("signature");
        treeMap.remove("signature");
        // 【待签明文串】--去除响应签名后获取待签明文串
        StringBuilder sb = new StringBuilder();
        assemSign(treeMap, sb);

        sb.deleteCharAt(sb.length() - 1);
        String befSgin = sb.toString();
        //【验签】
        boolean signresult = false;
        try {
            signresult = SignTools.doCheckSign(certFilePath, befSgin, signKey);
        } catch (Exception e) {
            System.out.println("验签异常");
        }
        //【 验签结果】
        return signresult;
    }

private static void assemSign(Map<String, Object> treeMap, StringBuilder sb) {
        for (String key : treeMap.keySet()) {
            if(treeMap.get(key) instanceof JSONObject){
                JSONObject js=(JSONObject) treeMap.get(key);
                TreeMap<String, Object> tmap = JSON.parseObject(js.toJSONString(), TreeMap.class);
                assemSign(tmap, sb);
            }else {
                if(null != treeMap.get(key) && !"".equals(treeMap.get(key))){
                    sb.append(treeMap.get(key)).append("|");
                }

            }
        }
    }
```



## 1.3.8 注意事项

【1】字段顺序

字段顺序必须是按字典序升序排列，否则验签会失败。

【2】加签字段

参与签名字段必须是接口要求字段，不能多冗余字段，否则验签失败。非空字符串不要进行加签。

【3】必传字段

如果缺少必传字段，验签会失败

【4】嵌套json

对于嵌套json字段，采用map嵌套map存放后转json

【5】公私钥

注意检查您的公私钥是由联动优势邮件下发的，请注意上生产后替换测试环境公私钥。



## 1.3.9 首次联调示例

首次联调可以依据上述规则，拼接好如下json串之后进行加签请求。

【待签名明文串】

```
acqMerId=41509208&acqSpId=Y471790403&authCode=134579761426152164&goodsId=123&goodsInfo=口罩&orderNo=JD202003051057240001&orderTime=20200305105724&orderType=wechat&txnAmt=1
```

【签名密文串】

```verilog
SnOf25uc9ZJ+Opmze+o7dsAggxQCB7/aYqlsEZ89V5T3RhsGw6p7ISO1BcnxAMuAWNhq3UOl+Ei4PtsVMnoaCTjY0IDoBnCCWdv98O5GMHp70uAAB/cI5EQrt1HRlM6Lb9VyL1f5BE1FthlSZYKlSvS8hOn3ZaDzN7z4N+4ijHI=
```

【最终报文串】

```json
{"acqMerId":"41509208","acqSpId":"Y471790403","authCode":"134579761426152164","goodsId":"123","goodsInfo":"口罩","orderNo":"JD202003051057240001","orderTime":"20200305105724","orderType":"wechat","signature":"SnOf25uc9ZJ+Opmze+o7dsAggxQCB7/aYqlsEZ89V5T3RhsGw6p7ISO1BcnxAMuAWNhq3UOl+Ei4PtsVMnoaCTjY0IDoBnCCWdv98O5GMHp70uAAB/cI5EQrt1HRlM6Lb9VyL1f5BE1FthlSZYKlSvS8hOn3ZaDzN7z4N+4ijHI=","txnAmt":"1"}
```

【请求方式】								POST

【请求header头约束】				*application*/*json*

【编码约束】								utf-8

【请求接口】								/pay/micropay



## 1.3.10 加签验签demo （PHP版本）



```php
<?php
header("Content-Type: text/html;charset=utf-8");
// 加签验签为联调环境秘钥
// 商户私钥 41509208mertest.key.pem 中间的一段私钥
define("PRIKEY", 'MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKrayv4Jwaf5cqjNjU5RsuImTkw/1BTXuTH5zjMXhaybbp20222eqbbyXqeA1niHGgtLpZn4P9YHSmKGGIyMLrbIS/3NSf3RTsaFUaoFW+sP6eXgshehkTio7ka+ddLFRnvo4/p/6oUU75odkhzr832EXJCfbKZYLzmTn1gleLXJAgMBAAECgYBodFk0VLyruErMPtcMMdCeiHLIFSHRGE4l6qaQ/+l+rIxFKKQaca0qpyUNXSxCBb77duUoq+hDLrPgZkEt9Co+HvdoNXFpxtohtzV7u6xS7lldtsFbrNSWIX32B3tighjPvdhqQ366XfUkd03OTpp+4QCNLKgocVfxmXn3bNnlTQJBAOCsQCClQgVY0AvL7+wW6LFxXhwthrqPhXSbecLBI6I5ROS2+Kiw3hmXPnTDhjvu/BM3Xj3OMzARBvEDLbbKQ3sCQQDCrX1UJ3UL3FnR2MTevKHZFgAly2A4mkobOCQn7gwcZFWCG51lKDkgURc4hQzfyqkFPxkPKBMdsCsF2fNtlJaLAkEA0kZEiolA3jI+HRxUw3i5dgFtnB+73fWfaQBumCkdiaHh6dGZroBCjl7wVGX+xy8n+3AxotTPMaht/7oC2fY56wJAF58VZfCjsxy5iUzUFnKs8LpKqktTEYsnEsZtHKs1rPw/o9nV70BCLjmop6L9POpKN+jAEaa7Kw8FKWCYuOMeoQJADoQfq568+TiCxFdveiiA+J+aKGBeihoe7qQu11cdjaiwK/VhMLyLF/S3krzy8SAqD48I7ZMKIIVbo8kSHo3s6g==');
// 联动公钥证书 testUmpay.pem 中间的一段证书
define("PUBKEY", 'MIICaTCCAdICAQowDQYJKoZIhvcNAQEEBQAwgZAxCzAJBgNVBAYTAkNOMRAwDgYDVQQIEwdCZWlqaW5nMRAwDgYDVQQHEwdCZWlqaW5nMRIwEAYDVQQKEwlVTVBBWSBMdGQxDjAMBgNVBAsTBVVNUEFZMRUwEwYDVQQDEwxVTVBBWSBNZXIgQ0ExIjAgBgkqhkiG9w0BCQEWE3dlYm1hc3RlckB1bXBheS5jb20wHhcNMDUwMTA2MDE1ODA5WhcNMDYwMTA2MDE1ODA5WjBpMQswCQYDVQQGEwJDTjEQMA4GA1UECBMHQmVpamluZzEQMA4GA1UEBxMHQmVpamluZzESMBAGA1UEChMJVU1QQVkgTHRkMQ4wDAYDVQQLEwVVTVBBWTESMBAGA1UEAxMJdGVzdFVtcGF5MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDAZhF6SMGSm3wg+vK8zJsZazyRh2a2SzQrF0sqVWK4M3TfI4+Ig7d6gjSJzz2SnG98Anz2/nedYTgfJs4ys9mJHoerRew5K4/PGy9OlRiF3lwQyCK/q5l9KJPAl+t6g/7QqWTq5otz0GFY8xrDreLFZEc2hjkdR4UujuFI7OdquQIDAQABMA0GCSqGSIb3DQEBBAUAA4GBAC6rzwbMxnw5/5bKfthpD5c4bGxD/mvSSRwKF0RSwaI07IuX8XJBsSbWnD4Sowv9Pr9v+/5dEzm6vIuQIzmxqv9NlHiWSHqSWmxd1Uu/4lxnGzquHghg0XcEGJxj0Nf28KZpll84iEzcEY6pgWR4+tupZ1k9Hv2oBI+LEtwOx80s');



/***
*	 商户加签验签工具类
*
*/
class SignUtil {
    /**
     * 商户秘钥加签
     * Author: umpayer
     *
     * @param string $data 之前生成好的需加密内容
     * @param string $key 私钥 (41509208mertest.key.pem)
     * @return 请求加签
     */
    public static function reqSign($data, $key=PRIKEY) {
		//请求加签
        $sign = self::rsaSign($data, $key);
        $data['signature'] = $sign;
		echo "*******【请求生成密文串】********" . PHP_EOL;;
        echo $sign . PHP_EOL;
        return $sign;
    }




    /**
     * RSA签名
	 * Author: umpayer
     * @param $data 待签名数据
     * @param $private_key 私钥字符串
     *
     * @return string
     */
    private static function rsaSign($data, $private_key)
    {   //001 拼接请求明文串
        $sortData = SignUtil::sortReqFields($data);
		echo "*******【请求生成明文串】********" . PHP_EOL;;
        echo  $sortData . PHP_EOL;
		//002 读取请求加签使用私钥
        $res = self::loadPriKey($private_key);
		
        if ($res) {
			//003 使用私钥进行加签
            openssl_sign($sortData,$sign, $res, OPENSSL_ALGO_SHA1);
            openssl_free_key($res);
        } else {
            exit("私钥格式有误");
        }
		//004 对签名进行编码
        $sign = base64_encode($sign);
        return $sign;
    }


    /**
     * 拼接需要签名的内容
     * Author: ZhaoHaiTang
     *
     * @param array $data 需签名的字段内容
     *
     * @return string
     */
    private static function sortReqFields($data)
    {
        unset($data['signature']);
		//递归解json嵌套操作
        foreach ($data as $k => $v) {
            if (is_array($v)) {
                $Parameters[$k] = self::sortReqFields($v);
            } else {
                $Parameters[$k] = $v;
            }
        }

        //按字典序排序参数
        ksort($Parameters);

        $sign = '';

        foreach ($Parameters as $k => $v) {
			if ($v == null || $v == '') {
                continue;
            }
			//如果值非空则添加
			$sign .= $k . "=" . $v . "&";
        }

        $sign = rtrim($sign, '&');

        return $sign;
    }
	
	/**
	 * 加载请求使用商户私钥
     * @param $private_key
     * @return bool|resource
     */
    private static function loadPriKey($private_key)
    {
        $private_key = chunk_split($private_key, 64, "\n");
        $private_key = "-----BEGIN RSA PRIVATE KEY-----\n" . $private_key . "-----END RSA PRIVATE KEY-----\n";
		echo "*******【加签签使用密钥】********" . PHP_EOL;;
        echo  $private_key . PHP_EOL;
        $res = openssl_get_privatekey($private_key);
        return $res;
    }
	
	
    /**
     * 联动公钥验签
     * Author: ZhaoHaiTang
     *
     * @param $data 要验证的签名
     * @param $pubCertKey  公钥证书 (testUmpay.pem文件)
     *
     * @throws
     * @return 验签结果
     */
    public static function respVerifySign($data, $pubCertKey=PUBKEY){
        $sortData = SignUtil::sortRespFields($data);
		echo "*******【验签解析明文串】********" . PHP_EOL;;
        echo  $sortData . PHP_EOL;
        $signature = $data['signature'];
		echo "*******【验签解析密文串】********" . PHP_EOL;;
        echo  $signature . PHP_EOL;
        $signature = base64_decode($signature);
        $keys = self::loadCert($pubCertKey);
		echo "*******【验签使用密钥】********" . PHP_EOL;;
        echo  $keys . PHP_EOL;
        $ok = openssl_verify($sortData, $signature, $keys);
		echo "*******【验签结果】********" . PHP_EOL;;
		if($ok == 1){
			echo "验签通过" . PHP_EOL;
		}else{
			echo "验签失败" . PHP_EOL;
		}
        return $ok;
    }
	
	
    private static function sortRespFields($data)
    {
        foreach ($data as $k => $v) {
            if ($k == 'signature') {
                continue;
            }
            if (is_array($v)) {
                $Parameters[$k] = self::sortRespFields($v);
            } else {
                $Parameters[$k] = $v;
            }
        }

        //按字典序排序参数
        ksort($Parameters);

        $sign = '';

        foreach ($Parameters as $k => $v) {
            if ($v == null || $v == '') {
                continue;
            }
			$domain = strstr($v, '"');
			if($domain != false){
				$v = str_replace('"',"",$v);
			}
			
            $sign .= $v . "|";
        }

        $sign = rtrim($sign, '|');

        return $sign;
    }




    /**
     * 获取响应验签使用联动公钥
     * @param $pubCertKey 公钥证书 (.cert.pem文件)
     *
     * @return mixed
     * @throws Exception
     */
    private static function loadCert($pubCertKey)
    {

        $cert = chunk_split($pubCertKey, 64, "\n");
        $cert = "-----BEGIN CERTIFICATE-----\n" . $cert . "-----END CERTIFICATE-----\n";

        $res = openssl_pkey_get_public($cert);
        $detail = openssl_pkey_get_details($res);
        openssl_free_key($res);

        if (!$detail) {
            throw new Exception('loadX509Cert::openssl_pkey_get_details ERROR');
        }
        return $detail['key'];
    }



}
	
	


/***
* demo使用主方法
* 如切换接口可以变更内部地址接口以及初始的json串
* 在线验证地址：https://www.toolfk.com/tool-online-runphp
***/
function demo(){
    $json = '{"acqMerId":"41509208","acqSpId":"Y471790403","authCode":"134781377084567613","goodsId":"123","goodsInfo":"口罩","orderNo":"JD202003060435350002","orderTime":"20200306163535","orderType":"wechat","txnAmt":"1"}';
    $data = json_decode($json,true);
	//加签
    $sign = SignUtil::reqSign($data);
	$data['signature'] = $sign;
    echo "*******【请求完整json串】********" . PHP_EOL;;
	echo json_encode($data,JSON_UNESCAPED_SLASHES|JSON_UNESCAPED_UNICODE) ;
    $baseUrl = 'http://114.113.159.198/entry-provider-plat-client';
	//echo json_encode($data) ;
    $response = http_post($baseUrl.'/pay/micropay', json_encode($data));
	echo "*******【返回信息】********" . PHP_EOL;
	echo $response . PHP_EOL;
	echo "**********请求结束，至此说明网络无误**************" . PHP_EOL;
	//返回信息验签
	$respJsonObj = json_decode($response, true);
    SignUtil::respVerifySign($respJsonObj);
	echo "**********demo结束，至此若均成功，说明加签验签无误**************" . PHP_EOL;
}
demo();

function http_post($url, $data){
    $curl = curl_init($url);

    curl_setopt($curl, CURLOPT_HEADER, false);
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($curl, CURLOPT_HTTPHEADER, array("Content-type: application/json"));
    curl_setopt($curl, CURLOPT_POST, true);
    curl_setopt($curl, CURLOPT_POSTFIELDS, $data);

    $response = curl_exec($curl);
    curl_close($curl);
    return $response;
}
/**

【执行后控制台输入如下】
*******【请求生成明文串】********
acqMerId=41509208&acqSpId=Y471790403&authCode=134781377084567613&goodsId=123&goodsInfo=口罩&orderNo=JD202003060435350002&orderTime=20200306163535&orderType=wechat&txnAmt=1
*******【加签签使用密钥】********
-----BEGIN RSA PRIVATE KEY-----
MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKrayv4Jwaf5cqjN
jU5RsuImTkw/1BTXuTH5zjMXhaybbp20222eqbbyXqeA1niHGgtLpZn4P9YHSmKG
GIyMLrbIS/3NSf3RTsaFUaoFW+sP6eXgshehkTio7ka+ddLFRnvo4/p/6oUU75od
khzr832EXJCfbKZYLzmTn1gleLXJAgMBAAECgYBodFk0VLyruErMPtcMMdCeiHLI
FSHRGE4l6qaQ/+l+rIxFKKQaca0qpyUNXSxCBb77duUoq+hDLrPgZkEt9Co+Hvdo
NXFpxtohtzV7u6xS7lldtsFbrNSWIX32B3tighjPvdhqQ366XfUkd03OTpp+4QCN
LKgocVfxmXn3bNnlTQJBAOCsQCClQgVY0AvL7+wW6LFxXhwthrqPhXSbecLBI6I5
ROS2+Kiw3hmXPnTDhjvu/BM3Xj3OMzARBvEDLbbKQ3sCQQDCrX1UJ3UL3FnR2MTe
vKHZFgAly2A4mkobOCQn7gwcZFWCG51lKDkgURc4hQzfyqkFPxkPKBMdsCsF2fNt
lJaLAkEA0kZEiolA3jI+HRxUw3i5dgFtnB+73fWfaQBumCkdiaHh6dGZroBCjl7w
VGX+xy8n+3AxotTPMaht/7oC2fY56wJAF58VZfCjsxy5iUzUFnKs8LpKqktTEYsn
EsZtHKs1rPw/o9nV70BCLjmop6L9POpKN+jAEaa7Kw8FKWCYuOMeoQJADoQfq568
+TiCxFdveiiA+J+aKGBeihoe7qQu11cdjaiwK/VhMLyLF/S3krzy8SAqD48I7ZMK
IIVbo8kSHo3s6g==
-----END RSA PRIVATE KEY-----

*******【请求生成密文串】********
e1g1VhifPzAMdDMleSgTGJBxzds+/zJa9q4hGOBouqDZWn4i5zLbE2POgA6P/BBEc+H2e2DrehvUJ93VRZAL44CruZVa1ugGEFJYDbyaSVGboeQiOlKYcNz5gIBZxfyNI2q9ZeXXJI7Y/Een1wh9WHzIqE39MqEJsRlmvDo113I=
*******【请求完整json串】********
{"acqMerId":"41509208","acqSpId":"Y471790403","authCode":"134781377084567613","goodsId":"123","goodsInfo":"口罩","orderNo":"JD202003060435350002","orderTime":"20200306163535","orderType":"wechat","txnAmt":"1","signature":"e1g1VhifPzAMdDMleSgTGJBxzds+/zJa9q4hGOBouqDZWn4i5zLbE2POgA6P/BBEc+H2e2DrehvUJ93VRZAL44CruZVa1ugGEFJYDbyaSVGboeQiOlKYcNz5gIBZxfyNI2q9ZeXXJI7Y/Een1wh9WHzIqE39MqEJsRlmvDo113I="}*******【返回信息】********
{"respCode":"001100WN","respMsg":"101 每个二维码仅限使用一次，请刷新再试","signature":"YkBsQ97Q1bCwgg5r7UCxFmz+le+YUKz637NB4H3KDivrszkbBQAgc2yIq+bvPMgI90Yifv/aHkreMedXXBShTz6hi0tt0Kt1CxBIbAZutXLetRW9ejX8fXft3BhoyWhhNWCYVkPhH1EKzvYGbsjdAt1bpuavBQfpFhnvu2VR0Vw=","platDate":null,"orderNo":"JD202003060435350002","transactionId":null,"txnAmt":"1","paySeq":null}
**********请求结束，至此说明网络无误**************
*******【验签解析明文串】********
JD202003060435350002|001100WN|101 每个二维码仅限使用一次，请刷新再试|1
*******【验签解析密文串】********
YkBsQ97Q1bCwgg5r7UCxFmz+le+YUKz637NB4H3KDivrszkbBQAgc2yIq+bvPMgI90Yifv/aHkreMedXXBShTz6hi0tt0Kt1CxBIbAZutXLetRW9ejX8fXft3BhoyWhhNWCYVkPhH1EKzvYGbsjdAt1bpuavBQfpFhnvu2VR0Vw=
*******【验签使用密钥】********
-----BEGIN PUBLIC KEY-----
MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDAZhF6SMGSm3wg+vK8zJsZazyR
h2a2SzQrF0sqVWK4M3TfI4+Ig7d6gjSJzz2SnG98Anz2/nedYTgfJs4ys9mJHoer
Rew5K4/PGy9OlRiF3lwQyCK/q5l9KJPAl+t6g/7QqWTq5otz0GFY8xrDreLFZEc2
hjkdR4UujuFI7OdquQIDAQAB
-----END PUBLIC KEY-----

*******【验签结果】********
验签通过
**********demo结束，至此若均成功，说明加签验签无误**************


**/

?>

```

