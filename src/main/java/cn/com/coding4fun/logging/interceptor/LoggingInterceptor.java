package cn.com.coding4fun.logging.interceptor;

import cn.com.coding4fun.logging.support.AbstractLoggingManager;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.io.Serializable;

public class LoggingInterceptor implements MethodInterceptor, Serializable {

	private AbstractLoggingManager loggingManager;

	public LoggingInterceptor(AbstractLoggingManager loggingManager) {
		this.loggingManager = loggingManager;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		return loggingManager.aroundProcessing(invocation);
	}
}
