package com.github.ssaunder.logging.util;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 数据类型判断
 */
public class TypeUtils {

	/**
	 * 是byte[]类型
	 * @param object
	 * @return
	 */
	public static boolean isByte(Object object){
		return object instanceof byte[] || object instanceof Byte[];
	}
	/**
	 * 是HTTP请求对象
	 * @param object
	 * @return
	 */
	public static boolean isHttpServletRequest(Object object) {
		return object instanceof HttpServletRequest;
	}
	/**
	 * 是Http响应对象
	 * @param object
	 * @return
	 */
	public static boolean isHttpServletResponse(Object object) {
		return object instanceof HttpServletResponse;
	}

	/**
	 * 是文件上传对象
	 * @param object
	 * @return
	 */
	public static boolean isMultipartFile(Object object) {
		return object instanceof MultipartFile;
	}

	/**
	 * 是数据模型和视图对象
	 * @param object
	 * @return
	 */
	public static boolean isModelAndView(Object object) {
		return object instanceof ModelAndView;
	}

	/**
	 * 是重定向对象
	 * @param object
	 * @return
	 */
	public static boolean isRedirectView(Object object) {
		return object instanceof RedirectView;
	}
}
