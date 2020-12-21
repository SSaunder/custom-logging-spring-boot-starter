package cn.com.coding4fun.logging.support;

import cn.com.coding4fun.logging.commons.OperationDetails;
import cn.com.coding4fun.logging.commons.RequestDetails;
import cn.com.coding4fun.logging.configuration.LoggingProperties;

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
