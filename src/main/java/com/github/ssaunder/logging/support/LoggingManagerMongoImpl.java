package com.github.ssaunder.logging.support;

import com.github.ssaunder.logging.commons.OperationDetails;
import com.github.ssaunder.logging.commons.RequestDetails;
import com.github.ssaunder.logging.configuration.LoggingProperties;
import com.github.ssaunder.logging.support.mongo.LoggingResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

public class LoggingManagerMongoImpl extends AbstractLoggingManager  {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private MongoTemplate template;

	public LoggingManagerMongoImpl(LoggingProperties properties, MongoTemplate template) {
		super(properties);
		this.template = template;
	}

	@Override
	public void finish(RequestDetails request, OperationDetails operation, String systemName, Object result) {
		try {
			template.insert(LoggingResult.of(request, operation, systemName, "INFO"));
		} catch (JsonProcessingException e) {
			logger.error("日志保存失败", e);
		} catch (Exception e) {
			logger.error("日志保存失败", e);
		}
	}

	@Override
	public void afterThrow(RequestDetails request, OperationDetails operation, String systemName, Throwable ex) {
		try {
			template.insert(LoggingResult.of(request, operation, systemName, "ERROR"));
		} catch (JsonProcessingException e) {
			logger.error("日志保存失败", e);
		} catch (Exception e) {
			logger.error("日志保存失败", e);
		}
	}
}
