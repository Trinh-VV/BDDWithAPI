package common;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Dictionary;
import java.util.Enumeration;

public class Request {
	private String requestBody;
	private HttpRequest httpRequest;
	private String responseBody;
	private HttpResponse<String> httpResponse;
	private int statusCode;

	public Request() {
		this.requestBody = "";
		this.httpRequest = null;
		this.responseBody = "";
		this.httpResponse = null;
		this.statusCode = 0;
	}
	
	
	
	public Request addHeader(Dictionary<String, String> headers) {
		httpRequest = HttpRequest.newBuilder().headers(addMultipleHeaders(headers)).build();
		return this;
	}
	
	public Request addMethod(String methodType) {
		httpRequest = HttpRequest.newBuilder().method(methodType, null).build();
		return this;
	}
//	public Request loadRequestBodyByJson(String methodType) {
//		//httpRequest = httpRequest.POST
//	}
	
	
	
	public static String addMultipleHeaders (Dictionary<String, String> headers) {
		Enumeration<String> keys = headers.keys();
		String headerResult = "";
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			headerResult = key + headers.get(key);
		}
		return headerResult;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public HttpRequest getHttpRequest() {
		return httpRequest;
	}

	public void setHttpRequest(HttpRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public HttpResponse<String> getHttpResponse() {
		return httpResponse;
	}

	public void setHttpResponse(HttpResponse<String> httpResponse) {
		this.httpResponse = httpResponse;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}