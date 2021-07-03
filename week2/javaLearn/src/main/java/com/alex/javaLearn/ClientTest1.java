package com.alex.javaLearn;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ClientTest1 {

	public static void main(String[] args) throws IOException {
		ClientTest1 clientTest = new ClientTest1();
		System.out.println(clientTest.run("http://127.0.0.1:8808/"));
	}

	String run(String url) throws IOException {
	  Request request = new Request.Builder()
	      .url("http://127.0.0.1:8808/")
	      .build();
	  OkHttpClient client = new OkHttpClient();
	  try (Response response = client.newCall(request).execute()) {
	    return response.body().string();
	  }
	}
	
}
