package com.github.ssaunder.logging.support;

import com.github.ssaunder.logging.annotation.LoggingModule;
import com.github.ssaunder.logging.annotation.LoggingOperation;
import com.github.ssaunder.logging.commons.OperationDetails;
import com.github.ssaunder.logging.commons.RequestDetails;
import com.github.ssaunder.logging.configuration.LoggingProperties;
import com.github.ssaunder.logging.util.DateTimeFormatUtils;
import com.github.ssaunder.logging.util.ExceptionUtils;
import com.github.ssaunder.logging.util.GeoIPLite2Utils;
import com.github.ssaunder.logging.util.LoggingConstantUtils;
import com.github.ssaunder.logging.util.RemoteClientUtils;
import com.github.ssaunder.logging.util.TypeUtils;
import com.github.ssaunder.logging.util.WebUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class AbstractLoggingManager implements LoggingManager {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private LoggingProperties properties;

	private final ObjectMapper mapper = new ObjectMapper();

	public AbstractLoggingManager(LoggingProperties properties) {
		this.properties = properties;
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	public abstract void finish(RequestDetails request, OperationDetails operation, String name, Object result);

	public abstract void afterThrow(RequestDetails request, OperationDetails operation, String name, Throwable ex);

	/**
	 * 处理切入点
	 * @param invocation
	 * @return
	 */
	public Object aroundProcessing(MethodInvocation invocation) throws Throwable {
		RequestDetails details = new RequestDetails();
		try {
			processRequestDetails(details);
			processMethodParameter(invocation, details);
			if (exclude(details.getUri())) {
				return invocation.proceed();
			}
			processMethodAnnotation(invocation);
		} catch (Exception e) {
			logger.error("request details process failed", e);
		}
		Object result = invocation.proceed();
		try {
			processResponseDetails(result, details);
		} catch (Exception e) {
			logger.error("response result process failed", e);
		} finally {
			processFinish(result);
		}
		return result;
	}

	/**
	 * 错误后处理
	 * @param throwable
	 */
	public void afterThrowProcessing(Throwable throwable) {
		RequestDetails requestDetails = RequestDetailsSupport.get();
		OperationDetails operationDetails = OperationDetailsSupport.get();
		requestDetails.setStackTrace(ExceptionUtils.getStackTrace(throwable));
		afterThrow(requestDetails, operationDetails, properties.getName().name(), throwable);
		remove();
	}

	/**
	 * 处理请求信息
	 * @param details
	 */
	private void processRequestDetails(RequestDetails details) throws IOException, GeoIp2Exception {
		HttpServletRequest request = WebUtils.getHttpServletRequest();
		details.setClientIp(RemoteClientUtils.getRequestIp(request));
		details.setUri(request.getRequestURI());
		details.setMethod(request.getMethod());
		details.setContentType(request.getContentType());
		details.setUserAgent(request.getHeader(LoggingConstantUtils.USER_AGENT));
		details.setIpAddress(GeoIPLite2Utils.resolverIp(details.getClientIp()));
		details.setAuthorization(request.getHeader(LoggingConstantUtils.AUTHORIZATION));
		details.setRequestTime(DateTimeFormatUtils.format(LocalDateTime.now()));
		details.setUsername(getUsername());
	}

	/**
	 * 处理方法请求参数
	 * @param invocation 被调用的方法
	 * @return
	 */
	private void processMethodParameter(MethodInvocation invocation, RequestDetails details) throws JsonProcessingException {
		Method method = invocation.getMethod();
		Object[] args = invocation.getArguments();
		Parameter[] parameters = method.getParameters();
		Map<String, Object> params = new HashMap<>(parameters.length);
		for (int i = 0; i < parameters.length; i++) {
			Object arg = args[i];
			Parameter parameter = parameters[i];
			// 判断是以下类型直接跳过本次循环
			if (TypeUtils.isHttpServletRequest(arg) || TypeUtils.isHttpServletResponse(arg)
					|| TypeUtils.isMultipartFile(arg) || TypeUtils.isModelAndView(arg)
					|| TypeUtils.isRedirectView(arg) || TypeUtils.isByte(arg)) {
				continue;
			} else if (parameter.getType().isPrimitive()) {
				params.put(parameter.getName(), arg);
			} else {
				params.put(parameter.getName(), mapper.writeValueAsString(arg));
			}
		}
		if (!params.isEmpty()) {
			details.setRequestParameters(params);
			RequestDetailsSupport.set(details);
		}
	}

	/**
	 * 处理关于请求方法上面的注解相关信息
	 * @param invocation
	 */
	private void processMethodAnnotation(MethodInvocation invocation) {
		OperationDetails details = new OperationDetails();
		Method method = invocation.getMethod();
		Class<?> declaringClass = method.getDeclaringClass();
		String className = ClassUtils.getShortName(declaringClass);
		String methodName = method.getName();
		LoggingModule module = declaringClass.getAnnotation(LoggingModule.class);
		LoggingOperation operation = AnnotationUtils.getAnnotation(method, LoggingOperation.class);
		details.setClassName(className);
		details.setMethodName(methodName);
		details.setModuleName(module.name());
		details.setOperationName(operation.name());
		details.setLogType(operation.type().getCode());
		details.setRemark(operation.remark());
		OperationDetailsSupport.set(details);
	}

	/**
	 * 处理响应结果
	 * @param result
	 * @param details
	 */
	private void processResponseDetails(Object result, RequestDetails details) {
		details.setResponseResult(result);
		RequestDetailsSupport.set(details);
	}

	/**
	 * 处理完成
	 * @param result
	 */
	private void processFinish(Object result) {
		RequestDetails requestDetails = RequestDetailsSupport.get();
		OperationDetails operationDetails = OperationDetailsSupport.get();
		finish(requestDetails, operationDetails, properties.getName().name(), result);
		remove();
	}

	/**
	 * 默认打印日志方法
	 */
	public void defaultFinish() {
		RequestDetails requestDetails = RequestDetailsSupport.get();
		OperationDetails operationDetails = OperationDetailsSupport.get();
		if (properties.isFormat()) {
			try {
				logger.info("\nRequest Details：\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDetails)
						+ "\nOperation Details：\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(operationDetails)
				);
			} catch (JsonProcessingException e) {
				logger.error("logging print error", e);
			}
		} else {
			try {
				logger.info("\nRequest Details：\n" + mapper.writeValueAsString(requestDetails)
						+ "\nOperation Details：\n" + mapper.writeValueAsString(operationDetails)
				);
			} catch (JsonProcessingException e) {
				logger.error("logging print error", e);
			}
		}
	}


	/**
	 * 释放本地线程
	 */
	private void remove() {
		RequestDetailsSupport.remove();
		OperationDetailsSupport.remove();
	}

	/**
	 * 判断URL是否需要排除
	 * @param uri
	 * @return
	 */
	private boolean exclude(String uri) {
		Set<String> excludeUrl = properties.getExcludeUrl();
		return new AntPathMatcher(StringUtils.collectionToCommaDelimitedString(excludeUrl)).isPattern(uri);
	}
}
