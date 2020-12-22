package com.github.ssaunder.logging.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggingModule {

	/**
	 * 模块名称
	 * @return
	 */
	String name() default "";

	/**
	 * 模块名称别名
	 * @return
	 */
	@AliasFor("name")
	String value() default "";
}
