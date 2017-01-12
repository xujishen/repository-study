package com.youdy.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HttpContext;

@SuppressWarnings("deprecation")
public class HttpClientUtil {
	
	private static Integer SC_OK = 200;
	private static Integer SC_NOT_EXISTS = 404;
	private static Integer SC_SERVER_EXCEPTION = 500;
	
	private static int DEFAULT_TIME_OUT = 5000;
	private static String DEFAULT_ENCODE = "UTF-8";
	
	public static String doPost(String url, List<NameValuePair> params) {
		return doPost(url, params, DEFAULT_TIME_OUT, DEFAULT_ENCODE);
	}
	
	public static String doGet(String url) {
		return doGet(url, DEFAULT_TIME_OUT, DEFAULT_ENCODE);
	}
	
	public static String doPost(String url, List<NameValuePair> params, int timeout, String encode) 
	{
		String responseString = "";
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(url);
		try
		{
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);
			httpPost.setEntity(new UrlEncodedFormEntity(params, encode));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == SC_OK)
			{
				HttpEntity httpEntity = httpResponse.getEntity();
				BufferedReader br = new BufferedReader(new InputStreamReader(httpEntity.getContent(),encode));
				StringBuffer backData = new StringBuffer();
				String line = null;
				while((line = br.readLine())!=null){
					backData.append(line);
				}
				responseString = backData.toString();
				if(httpEntity != null) 
					httpEntity.consumeContent();
				
				httpEntity = null;
				httpResponse = null;
				httpPost = null;
				httpClient = null;
			}
			else if(statusCode == SC_NOT_EXISTS)
			{
				System.out.println("====================对方服务器未找到=============================");
			}
			else if(statusCode == SC_SERVER_EXCEPTION)
			{
				System.out.println("====================对方服务器内部异常============================");
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return responseString;
	}
	
	
	/**
	 * Handle get request
	 * @author 	  Su Jishen
	 * @DateTime 2015年7月24日 下午2:14:45
	 */
	public static String doGet(String url, int timeout, String encode) 
	{
		String responseString = "";
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(url);
		try
		{
			RequestConfig requestConfig = RequestConfig.custom()  
			        .setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)  
			        .setSocketTimeout(timeout).build();
			httpGet.setConfig(requestConfig);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == SC_OK)
			{
				HttpEntity httpEntity = httpResponse.getEntity();
				BufferedReader br = new BufferedReader(new InputStreamReader(httpEntity.getContent(),encode));
				StringBuffer backData = new StringBuffer();
				String line = null;
				while ((line = br.readLine())!=null) {
					backData.append(line);
				}
				responseString = backData.toString();
				if (httpEntity != null) {
					httpEntity.consumeContent();
				}
				httpEntity = null;
				httpResponse = null;
				httpGet = null;
				httpClient = null;
			}
			else if (statusCode == SC_NOT_EXISTS)
			{
				System.out.println("====================对方服务器未找到=============================");
			}
			else if(statusCode == SC_SERVER_EXCEPTION)
			{
				System.out.println("====================对方服务器内部异常============================");
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return responseString;
	}
	
	public static void main(String[] args) {
		String url = "http://10.128.9.160:8090/api/v2/applications/response%20time?openid=9&application_name=MyTier_lxs_demo_Informix_0001&span_time=3600000&interval=300000";
		String str = HttpClientUtil.doGet(url);
		System.out.println(str);
	}
}
