package com.alex.javaLearn;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ClientTest2 {
	 public static CloseableHttpClient httpclient = HttpClients.createDefault();
	
	 public static String getUrl(String url) throws IOException {
	        HttpGet httpGet = new HttpGet(url);
	        CloseableHttpResponse response = null;
	        try {
	        	response = httpclient.execute(httpGet);
	            HttpEntity entity1 = response.getEntity();
	            return EntityUtils.toString(entity1, "UTF-8");
	        } finally {
	            if (null != response) {
	                response.close();
	            }
	        }
	   }
	  
        public static void main(String[] args) throws Exception {
        	System.out.println(getUrl("http://127.0.0.1:8808/"));	
        }
	   
}
