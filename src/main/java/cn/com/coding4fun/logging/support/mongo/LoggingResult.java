package cn.com.coding4fun.logging.support.mongo;

import cn.com.coding4fun.logging.commons.OperationDetails;
import cn.com.coding4fun.logging.commons.RequestDetails;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.system.ApplicationPid;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class LoggingResult {

	@Id
	private String id;

	private String systemName;

	private String processId;

	private String threadName;

	private String username;

	private String levelName;

	private String remoteIp;

	private String remoteAddress;

	private String remoteUrl;

	private String method;

	private String contentType;

	private String userAgent;

	private LocalDateTime requestTime;

	private String requestParameters;

	private String responseResult;

	private String stackTrace;

	private String className;

	private String methodName;

	private String moduleName;

	private String operationName;

	private Integer operationType;

	private String remark;

	public static LoggingResult of(RequestDetails requestDetails, OperationDetails operationDetails, String systemName, String levelName) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		LoggingResult loggingResult = new LoggingResult();
		loggingResult.setSystemName(systemName);
		loggingResult.setProcessId(new ApplicationPid().toString());
		loggingResult.setThreadName(Thread.currentThread().getName());
		loggingResult.setRemoteIp(requestDetails.getClientIp());
		loggingResult.setUsername(requestDetails.getUsername());
		loggingResult.setLevelName(levelName);
		loggingResult.setRemoteAddress(requestDetails.getIpAddress());
		loggingResult.setRemoteUrl(requestDetails.getUri());
		loggingResult.setMethod(requestDetails.getMethod());
		loggingResult.setContentType(requestDetails.getContentType());
		loggingResult.setUserAgent(requestDetails.getUserAgent());
		loggingResult.setRequestTime(LocalDateTime.now());
		loggingResult.setRequestParameters(mapper.writeValueAsString(requestDetails.getRequestParameters()));
		loggingResult.setResponseResult(mapper.writeValueAsString(requestDetails.getResponseResult()));
		loggingResult.setStackTrace(requestDetails.getStackTrace());
		loggingResult.setClassName(operationDetails.getClassName());
		loggingResult.setMethodName(operationDetails.getMethodName());
		loggingResult.setModuleName(operationDetails.getModuleName());
		loggingResult.setOperationName(operationDetails.getOperationName());
		loggingResult.setOperationType(operationDetails.getLogType());
		loggingResult.setRemark(operationDetails.getRemark());

		return loggingResult;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public String getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public String getRemoteUrl() {
		return remoteUrl;
	}

	public void setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
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

	public LocalDateTime getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(LocalDateTime requestTime) {
		this.requestTime = requestTime;
	}

	public String getRequestParameters() {
		return requestParameters;
	}

	public void setRequestParameters(String requestParameters) {
		this.requestParameters = requestParameters;
	}

	public String getResponseResult() {
		return responseResult;
	}

	public void setResponseResult(String responseResult) {
		this.responseResult = responseResult;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public Integer getOperationType() {
		return operationType;
	}

	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "LoggingResult{" +
				"id='" + id + '\'' +
				", systemName='" + systemName + '\'' +
				", processId='" + processId + '\'' +
				", threadName='" + threadName + '\'' +
				", username='" + username + '\'' +
				", levelName='" + levelName + '\'' +
				", remoteIp='" + remoteIp + '\'' +
				", remoteAddress='" + remoteAddress + '\'' +
				", remoteUrl='" + remoteUrl + '\'' +
				", method='" + method + '\'' +
				", contentType='" + contentType + '\'' +
				", userAgent='" + userAgent + '\'' +
				", requestTime=" + requestTime +
				", requestParameters='" + requestParameters + '\'' +
				", responseResult='" + responseResult + '\'' +
				", stackTrace='" + stackTrace + '\'' +
				", className='" + className + '\'' +
				", methodName='" + methodName + '\'' +
				", moduleName='" + moduleName + '\'' +
				", operationName='" + operationName + '\'' +
				", operationType=" + operationType +
				", remark='" + remark + '\'' +
				'}';
	}
}
