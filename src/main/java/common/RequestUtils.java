package common;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;

import org.javatuples.Pair;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;


public class RequestUtils {
	
	public static Pair<Integer, String> sendRequest(String url, String method, Dictionary<String, String> headers, String requestBody ){
		Pair<Integer, String> response = new Pair<Integer, String>(0, "");
		if(method.equals(Method.POST.toString())) {
			response = sendPostRequest(url, headers, requestBody);
		}else if(method.equals(Method.GET.toString())){
			response = sendGetRequest(url, headers);
		}else {
			//Todo
		}
		return response;
	}
	
	
	private static Pair<Integer, String> sendPostRequest(String url, Dictionary<String, String> headers, String requestBody) {
		int statusCode = 0;
		String responseBody;
		String[] herdddd = addMultipleHeaders(headers);
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.headers(herdddd)
				.POST(BodyPublishers.ofString(requestBody))
		        .build();	
		HttpClient httpClient = HttpClient.newBuilder().build();
		try {
		HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
		statusCode = response.statusCode();
		responseBody = response.body();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Response is null");
			responseBody="";
		}
		return new Pair<Integer, String>(statusCode, responseBody);
	}
	
	private static Pair<Integer, String> sendGetRequest (String url, Dictionary<String, String> headers){
		int statusCode = 0;
		String responseBody;

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.headers(addMultipleHeaders(headers))
				.GET()
		        .build();	
		HttpClient httpClient = HttpClient.newBuilder().build();
		try {
		HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
		statusCode = response.statusCode();
		responseBody = response.body();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Response is null");
			responseBody="";
		}
		return new Pair<Integer, String>(statusCode, responseBody);
	}
	
	public static String[] addMultipleHeaders (Dictionary<String, String> headers) {
		Enumeration<String> keys = headers.keys();
		StringBuffer headerResult = new StringBuffer();
		
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			headerResult.append(key +" "+ headers.get(key));
			headerResult.append(" ");
		}
		System.out.println(headerResult);
		return headerResult.toString().split(" ");
	}

}
