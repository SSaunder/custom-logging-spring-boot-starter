package com.github.ssaunder.logging.support;

import com.github.ssaunder.logging.commons.OperationDetails;

public class OperationDetailsSupport {

	private static ThreadLocal<OperationDetails> httpRequestDetailsThreadLocal = new ThreadLocal<>();

	/**
	 * 获取本地线程中的内容
	 * @return
	 */
	public static OperationDetails get() {
		return httpRequestDetailsThreadLocal.get();
	}

	/**
	 * 给线程设置内容
	 * @param details
	 */
	public static void set(OperationDetails details) {
		httpRequestDetailsThreadLocal.set(details);
	}

	/**
	 * 清空线程当中的信息
	 */
	public static void remove() {
		httpRequestDetailsThreadLocal.remove();
	}
}
