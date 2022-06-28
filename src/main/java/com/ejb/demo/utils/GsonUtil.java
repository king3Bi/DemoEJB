package com.ejb.demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
	private static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	
	public static void sendAsJson(HttpServletResponse response, Object object) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String res = gson.toJson(object);
		PrintWriter out = response.getWriter();
		out.print(res);
		out.flush();
	}
	
	public static Object fromJson(HttpServletRequest request) throws IOException {
		StringBuilder buffer = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        buffer.append(line);
	    }
	    String payload = buffer.toString();
	    
	    return gson.fromJson(payload, Object.class);
	}
}
