package com.umpay.addmer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.umpay.demo.step1_基础参数准备.EnvConfig;
import com.umpay.util.CertUtils;
import com.umpay.util.SignTools;

import static com.umpay.demo.step1_基础参数准备.EnvConfig.certFileRootPath;

/**
 * Hello world!
 *
 */
public class AddSign
{


	public static String privateKey = EnvConfig.certFileRootPath + "testSpay.key.p8";
    public static String serverCertFileName = "testUmpay.cert.crt";


    public static void main( String[] args ) 
    {
        String aa ="{\"respCode\":\"00\",\"respMsg\":\"处理成功\",\"signature\":\"JZHqglo7/ovc2LgRlsosYGnUSwyVu1ApUsQFgstuXxLNwqpdsDRqTSFoA0mnFqS4m+5pFdjn1ujSCuo2sWit3jVTf12hDUDD2aQvgfVByzPDNWJNY6iPUi432+i9MI3R6/QGxlTlNBl0MbwiVMkjYQJhu+pwBjkXXDU45u6N7xo=\",\"platDate\":\"20190806144534\",\"orderNo\":\"JD201908060244020001\",\"transactionId\":\"2019080614450000000082\",\"txnAmt\":\"1\",\"qrCode\":\"https://qr.alipay.com/bax00501hslllkvqqha060d5\"}";
    			//    	String aa="{\"respCode\": \"00\",\"respMsg\": \"处理成功\",\"signature\": \"SG/0FkkbeH6kYP6jr2ZrT+fYxVeiyKZymFbaaczIRPuBBIWC5wI65khmxphiig6gsMCjQje2Zk9paVUg4qmryg3JjtCCiCeQMExdRIBuGo5h/06LvcU6xMVjl7AzF9Ya6t1FSf1TLNRTake7sgl3KZ7cE0u57nN/j0n16LI/dYs=\",\"platDate\": \"20190806111312\",\"orderNo\": \"JD201908061111200001\",\"transactionId\": \"2019080611130000000053\",\"txnAmt\": \"1\",\"qrCode\": \"weixin://wxpay/bizpayurl?pr=CLefq7x\"}";
//    	String aa="{\"signature\": \"C3tRHeIrLcTuteI7nznbqjvIqOAf6LPcwuNil5vt1+MBq9FGzjlm7/CviniJcZYTFtDEVKlIqADfwTCIZGB+2fiOp1Nqvl3GQJO9R5KqywCm221XBTDdmivARZGHPJA2YpAwJCirR+buE3sxZTERu8R9VqtDvsdDh+rQXPc7GKo=\",}";
//    	String aa ="C3tRHeIrLcTuteI7nznbqjvIqOAf6LPcwuNil5vt1+MBq9FGzjlm7/CviniJcZYTFtDEVKlIqADfwTCIZGB+2fiOp1Nqvl3GQJO9R5KqywCm221XBTDdmivARZGHPJA2YpAwJCirR+buE3sxZTERu8R9VqtDvsdDh+rQXPc7GKo=";
    	boolean ss =doCheckSign(aa);
    	System.out.println(ss);
    }
    
    /**
     * 加签
     * @param reqMap
     * @return
     * @throws UnsupportedEncodingException
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public static String addSign(TreeMap<String, Object> reqMap) throws UnsupportedEncodingException, GeneralSecurityException, IOException{


        reqMap.remove("signature");
        //获取原请求签名
        String signStr    = new StringBuilder(reqMap.toString().replace(", ", "&").replace("{", "").replace("}", "")).toString();//【待签名明文串】--signStr
        System.out.println("待签名明文串:" + signStr);
        // 验签
        
        String sign = CertUtils.sign(signStr, CertUtils.getPrivateKey(privateKey),"UTF-8");//【签名密文串】--sign
        System.out.println("签名密文串:" + sign);
        
        reqMap.put("signature", sign);
        //【最终报文串】--res
        String res=JSON.toJSONString(reqMap);
        System.out.println("最终报文串："+ res);
        return res;
    }
    
    /**
     * 验签
     * @param object
     * @return
     */
    @SuppressWarnings("unchecked")
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
            signresult = SignTools.doCheckSign(certFileRootPath, serverCertFileName, befSgin, signKey);
        } catch (Exception e) {
            System.out.println("验签异常");
        }
        //【 验签结果】
        return signresult;
    }
}
