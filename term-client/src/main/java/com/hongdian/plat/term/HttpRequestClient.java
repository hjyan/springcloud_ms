/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term;

import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
 * @date 2018-05-18 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
public class HttpRequestClient {

	public static void main(String[] args) throws Exception {
		System.out.println("测试开始。。。。。。");
		requestPost();
	}

	public static void requestPost() throws Exception {
		String path = "F:\\swic-svn\\H7760\\src\\back-end\\term-client\\src\\test\\java\\com\\hongdian\\plat\\term\\plat\\";
		String filename = path + "upgradeMp3.json";
		String url = "upgradeMp3";
		File file = new File(filename);
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		InputStream is = new FileInputStream(filename);
		is.read(filecontent);
		is.close();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:38254/devCtrl/" + url);
		JSONObject json = JSONObject.parseObject(new String(filecontent));
		System.out.println("content : " + json.getString("content"));
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("termSn", "6202010001"));
		urlParameters.add(new BasicNameValuePair("content", json.getString("content")));
		HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
		httpPost.setEntity(postParams);
		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
		System.out.println("POST Response Status:: " + httpResponse.getStatusLine().getStatusCode());
		BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = reader.readLine()) != null) {
			response.append(inputLine);
		}
		reader.close();

		// print result
		System.out.println(response.toString());
		httpClient.close();
	}
}
