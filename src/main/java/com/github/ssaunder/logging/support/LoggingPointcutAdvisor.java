package com.github.ssaunder.logging.support;

import com.github.ssaunder.logging.annotation.LoggingOperation;
import com.github.ssaunder.logging.annotation.LoggingModule;
import com.github.ssaunder.logging.interceptor.LoggingInterceptor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractGenericPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

public class LoggingPointcutAdvisor extends AbstractGenericPointcutAdvisor {

	private Pointcut pointcut;

	public LoggingPointcutAdvisor(AbstractLoggingManager manager) {
		this.pointcut = new AnnotationMatchingPointcut(LoggingModule.class, LoggingOperation.class);
		this.setAdvice(new LoggingInterceptor(manager));
	}

	@Override
	public Pointcut getPointcut() {
		return this.pointcut;
	}

	public String toString() {
		return this.getClass().getName() + ": pointcut [" + this.getPointcut() + "]; advice [" + this.getAdvice() + "]";
	}
}
