package com.github.ssaunder.logging.filter;

import com.github.ssaunder.logging.support.RequestDetailsSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class RequestDetailsFilter implements Filter {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("RequestDetailsFilter initialize successful.");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		// 获取操作信息
		filterChain.doFilter(servletRequest, servletResponse);
		// 获取响应信息
		log.info("请求信息");
		RequestDetailsSupport.remove();
	}

	@Override
	public void destroy() {
		log.info("RequestDetailsFilter destroy");
	}
}
