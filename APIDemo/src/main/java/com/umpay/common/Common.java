package com.umpay.common;

import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONObject;
/**
 * @author: zhangjing
 * @date:2019年8月5日 下午3:43:13
 * @类说明:
 * @产品号:
 */

public class Common {
	
	protected static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
	protected static AtomicInteger seq = new AtomicInteger();
	public static String genOrderId() {
		return String.format("JD%s%04d", sdf.format(System.currentTimeMillis()), seq.incrementAndGet());
	}
	
	/**
	 * post请求，请求参数是Map
	 * @param URL
	 * @param data
	 * @return
	 */
//	public static Map<String, Object> runPrint(String URL, Map<String, Object> data) {
//		System.out.println("\n请求报文= " + JsonUtil.objectToMap(data));
//		Map<String, Object> result = new HashMap<String, Object>();
//		try {
//			result = HttpUtil.httpPostForm2Xml(URL, data, "gbk");
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("\n响应报文= " + result);
//		return result;
//	}
	
	/**
	 * post请求，请求参数是jsonObject
	 * @param url
	 * @param jsonObject
	 * @param encoding
	 * @return
	 */
	public static String runJsonPost(String url, JSONObject jsonObject, String encoding) {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		String response = null;
		try {
			StringEntity s = new StringEntity(jsonObject.toString());
			s.setContentEncoding(encoding);
			s.setContentType("application/json");// 发送json数据需要设置contentType
			post.setEntity(s);
			System.out.println("post请求"+post);
			HttpResponse res = httpclient.execute(post);
			System.out.println(res.getStatusLine().getStatusCode());
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String result = EntityUtils.toString(res.getEntity());// 返回json格式：
				response = result;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}
	
	public static String runJsonPost1(String url, JSONObject jsonObject, String encoding) {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		String response = null;
		try {
			EntityBuilder en=EntityBuilder.create();
			en.setText(jsonObject.toString());
			en.setContentEncoding(encoding);
			post.setHeader("Content-Type", "application/json");// 发送json数据需要设置contentType
			post.setEntity(en.build());
			System.out.println("post请求:"+ post.getAllHeaders()+"实体"+post.getEntity());
			System.out.println("请求参数body："+jsonObject);
			HttpResponse res = httpclient.execute(post);
			System.out.println(res.getStatusLine().getStatusCode());
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String result = EntityUtils.toString(res.getEntity());// 返回json格式：
				response = JSONObject.toJSONString(result);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}
	
	
}
