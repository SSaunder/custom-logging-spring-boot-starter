package cn.com.coding4fun.logging.support;

import org.springframework.aop.ThrowsAdvice;

public class LoggingPointcutThrowingAdvisor implements ThrowsAdvice {

	private AbstractLoggingManager loggingManager;

	public LoggingPointcutThrowingAdvisor(AbstractLoggingManager loggingManager) {
		this.loggingManager = loggingManager;
	}

	public void afterThrowing(Exception e) throws Throwable {
		loggingManager.afterThrowProcessing(e);
	}
}
