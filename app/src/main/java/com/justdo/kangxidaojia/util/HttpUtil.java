package com.justdo.kangxidaojia.util;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {
	public static Response httpGet(String url) throws IOException{
	    OkHttpClient okHttpClient_get = new OkHttpClient();
	    Request request = new Request.Builder()
	        .get()
	        .url(url)
	        .build();
	    Response response = okHttpClient_get.newCall(request).execute();
		return response;
	}
}
