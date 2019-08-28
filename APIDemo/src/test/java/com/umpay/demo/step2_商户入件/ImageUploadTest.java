package com.umpay.demo.step2_商户入件;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import com.umpay.util.HttpUtilClient;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSONObject;
import com.umpay.demo.step1_基础参数准备.EnvConfig;


/**
* @author 作者 :wangbeibei
* @version 创建时间：2019年7月25日 下午2:41:28
* desc:
*/
public class ImageUploadTest {
	
	String url = EnvConfig.serverURL + "/upload/qualification";
	
	
	@Test	
	public void uploadImg(){
		
		
		
		//String url = "http://10.10.81.177:7807/uploadWithCheck/uploadImg";

		//String url = "http://10.10.55.66:7807/uploadWithCheck/uploadImg";
		//String url = "http://10.10.55.130:7801/authentication/uploadimg";

		try {
			
			File originalFile = new File("D:\\UMPAY\\pic\\idCardFront.jpg");//指定要读取的图片
			FileInputStream in = new FileInputStream(originalFile);
			//写入相应的文件
			ByteArrayOutputStream outByte = new ByteArrayOutputStream();
			int n = 0;
			while((n=in.read())!=-1){
				 //写入相关文件
	            outByte.write(n);
			}
			
			
			System.out.println(outByte.toByteArray().length);
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("funCode", "PROVIDER_PERSONMERADD");
			paramMap.put("rpid", "12321aew1231");
			paramMap.put("reqDate", "20190728");
			paramMap.put("reqTime", "120012");
			paramMap.put("idNumber", "412701198707292019");
			paramMap.put("imageType", "idCardFront");
			paramMap.put("imageName", "idCard_zheng.jpg");
			paramMap.put("acqSpId", "Y608643555");//  Y608643555  Y052541751
			paramMap.put("merId", "M2019080100000057");//  M2019080100000057 M2019072600000202

			String bStr = Base64Utils.encodeToString(outByte.toByteArray());

			paramMap.put("imageSource", bStr);
			String resMSg = HttpUtilClient.doPostJson(url, new JSONObject(), paramMap);
			System.out.println( "okok" +  resMSg);
			//执行完以上后，磁盘下的该文件才完整，大小是实际大小
			in.close();
			outByte.close();
			org.junit.Assert.assertTrue(true);
		}catch (Exception e3) {
			e3.printStackTrace();
			Assert.fail();
			
		}
		
		
	}

}
