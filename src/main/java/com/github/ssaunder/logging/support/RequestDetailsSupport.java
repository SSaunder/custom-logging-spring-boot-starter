package com.github.ssaunder.logging.support;

import com.github.ssaunder.logging.commons.RequestDetails;

public class RequestDetailsSupport {

	private static ThreadLocal<RequestDetails> httpRequestDetailsThreadLocal = new ThreadLocal<>();

	/**
	 * 获取本地线程中的内容
	 * @return
	 */
	public static RequestDetails get() {
		return httpRequestDetailsThreadLocal.get();
	}

	/**
	 * 给线程设置内容
	 * @param details
	 */
	public static void set(RequestDetails details) {
		httpRequestDetailsThreadLocal.set(details);
	}

	/**
	 * 清空线程当中的信息
	 */
	public static void remove() {
		httpRequestDetailsThreadLocal.remove();
	}
}
