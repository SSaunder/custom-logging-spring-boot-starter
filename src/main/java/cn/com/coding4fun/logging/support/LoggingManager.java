package cn.com.coding4fun.logging.support;

import cn.com.coding4fun.logging.commons.OperationDetails;
import cn.com.coding4fun.logging.commons.RequestDetails;
import cn.com.coding4fun.logging.configuration.LoggingProperties;

public class LoggingManager extends AbstractLoggingManager {

	public LoggingManager(LoggingProperties properties) {
		super(properties);
	}

	@Override
	public void finish(RequestDetails request, OperationDetails operation, Object result) {

	}

	@Override
	public void afterThrow(RequestDetails request, OperationDetails operation, Throwable ex) {

	}
}
