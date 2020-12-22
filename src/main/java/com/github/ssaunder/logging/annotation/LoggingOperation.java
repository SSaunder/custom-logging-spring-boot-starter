package com.github.ssaunder.logging.annotation;

import com.github.ssaunder.logging.commons.LoggingTypeEnum;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志说明
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggingOperation {
	/**
	 * 日志名称
	 * @return
	 */
	String name() default "";

	/**
	 * 日志名称
	 *
	 * @return
	 */
	@AliasFor("name")
	String value() default "";
	/**
	 * 日志类型
	 * @return
	 */
	LoggingTypeEnum type() default LoggingTypeEnum.QUERY;
	/**
	 * 日志备注
	 *
	 * @return
	 */
	String remark() default "";
}
