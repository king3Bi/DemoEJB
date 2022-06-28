package com.ejb.demo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

public class ServletUtill {
	
	public static HashMap<String, String> convertToQueryStringToHashMap(
            String source) throws Exception {

        HashMap<String, String> data = new HashMap<String, String>();

        final String[] arrParameters = source.split("&");
        for (final String tempParameterString : arrParameters) {

            final String[] arrTempParameter = tempParameterString
                    .split("=");

            if (arrTempParameter.length >= 2) {
                final String parameterKey = arrTempParameter[0];
                final String parameterValue = arrTempParameter[1];
                data.put(parameterKey, parameterValue);
            } else {
                final String parameterKey = arrTempParameter[0];
                data.put(parameterKey, "");
            }
        }

        return data;
    }
	
	private static String inputStreamToString(InputStream inputStream) {
//	      Scanner scanner = new Scanner(inputStream, "UTF-8");
	      Scanner scanner = new Scanner(inputStream);
	      String result = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
	      try {
	    	  String resultDecode = URLDecoder.decode(result, StandardCharsets.UTF_8.name());
	    	  return resultDecode;
	      } catch (Exception e) {
			// TODO: handle exception
	    	  e.printStackTrace();
	    	  return "";
		}
	      
	  }
	
	public static HashMap<String, String> getDataRequest(
			HttpServletRequest request) throws IOException, Exception {
		return convertToQueryStringToHashMap(
				inputStreamToString(request.getInputStream()));
	}
}
