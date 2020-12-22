package com.github.ssaunder.logging.support;

import com.github.ssaunder.logging.commons.OperationDetails;
import com.github.ssaunder.logging.commons.RequestDetails;
import com.github.ssaunder.logging.configuration.LoggingProperties;

public class LoggingManagerImpl extends AbstractLoggingManager {

	public LoggingManagerImpl(LoggingProperties properties) {
		super(properties);
	}

	@Override
	public void finish(RequestDetails request, OperationDetails operation, String systemName, Object result)  {
		super.defaultFinish();
	}

	@Override
	public void afterThrow(RequestDetails request, OperationDetails operation, String systemName, Throwable ex) {
		super.defaultFinish();
	}
}
