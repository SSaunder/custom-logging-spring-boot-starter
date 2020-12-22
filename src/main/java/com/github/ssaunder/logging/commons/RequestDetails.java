package com.github.ssaunder.logging.commons;

import com.github.ssaunder.logging.support.LoggingManager;

import java.io.Serializable;

/**
 * 请求详细信息
 */
public class RequestDetails implements Serializable {

	/**
	 * 当前请求的真实用户名
	 * 详情查看{@link LoggingManager#getUsername()}接口获取用户名
	 */
	private String username;
	/**
	 * 客户端真实IP
	 */
	private String clientIp;
	/**
	 * IP解析后对应的地址信息
	 */
	private String ipAddress;
	/**
	 * 客户端请求URL
	 */
	private String uri;
	/**
	 * 客户端请求方式
	 */
	private String method;
	/**
	 * 客户端请求内容
	 */
	private String contentType;
	/**
	 * 用户代理字符串
	 */
	private String userAgent;
	/**
	 * 请求时间
	 */
	private String requestTime;
	/**
	 * 获取请求授权头
	 */
	private String authorization;
	/**
	 * 请求参数
	 */
	private Object requestParameters;
	/**
	 * 响应结果
	 */
	private Object responseResult;
	/**
	 * 错误栈信息
	 */
	private String stackTrace;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public Object getRequestParameters() {
		return requestParameters;
	}

	public void setRequestParameters(Object requestParameters) {
		this.requestParameters = requestParameters;
	}

	public Object getResponseResult() {
		return responseResult;
	}

	public void setResponseResult(Object responseResult) {
		this.responseResult = responseResult;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	@Override
	public String toString() {
		return "RequestDetails{" +
				"username='" + username + '\'' +
				", clientIp='" + clientIp + '\'' +
				", ipAddress='" + ipAddress + '\'' +
				", uri='" + uri + '\'' +
				", method='" + method + '\'' +
				", contentType='" + contentType + '\'' +
				", userAgent='" + userAgent + '\'' +
				", requestTime='" + requestTime + '\'' +
				", authorization='" + authorization + '\'' +
				", requestParameters=" + requestParameters +
				", responseResult=" + responseResult +
				", stackTrace='" + stackTrace + '\'' +
				'}';
	}
}
