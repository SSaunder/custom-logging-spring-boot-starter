package com.github.ssaunder.logging.configuration;

import com.github.ssaunder.logging.commons.LoggingSystemEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

import java.util.Set;
import java.util.TreeSet;

@ConfigurationProperties(prefix = "custom.logging")
public class LoggingProperties implements InitializingBean {

	/**
	 * 是否启用日志
	 */
	private boolean enable;
	/**
	 * 运行环境
	 */
	private String runtimeEnv = "default";
	/**
	 * 系统名称
	 */
	private LoggingSystemEnum name;
	/**
	 * 操作日志配置
	 */
	private Operation operation;
	/**
	 * 登录日志配置
	 */
	private Login login;
	/**
	 * 是否格式化日志输出
	 */
	private boolean format;
	/**
	 * 要排除的URL
	 */
	private Set<String> excludeUrl;

	public LoggingProperties() {
		this.enable = false;
		this.runtimeEnv = "default";
		this.name = LoggingSystemEnum.UNKNOWN;
		this.operation = new Operation();
		this.login = new Login();
		this.format = false;
		this.excludeUrl = new TreeSet<>();
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getRuntimeEnv() {
		return runtimeEnv;
	}

	public void setRuntimeEnv(String runtimeEnv) {
		this.runtimeEnv = runtimeEnv;
	}

	public LoggingSystemEnum getName() {
		return name;
	}

	public void setName(LoggingSystemEnum name) {
		this.name = name;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public boolean isFormat() {
		return format;
	}

	public void setFormat(boolean format) {
		this.format = format;
	}

	public Set<String> getExcludeUrl() {
		return excludeUrl;
	}

	public void setExcludeUrl(Set<String> excludeUrl) {
		this.excludeUrl = excludeUrl;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.name, "logging name is not null");
	}

	/**
	 * 操作日志配置
	 */
	public static class Operation {
		/**
		 * 是否启用日志
		 */
		private boolean enable = false;
		/**
		 * 排除URL
		 */
		private Set<String> excludeUrls;

		public boolean isEnable() {
			return enable;
		}

		public void setEnable(boolean enable) {
			this.enable = enable;
		}

		public Set<String> getExcludeUrls() {
			return excludeUrls;
		}

		public void setExcludeUrls(Set<String> excludeUrls) {
			this.excludeUrls = excludeUrls;
		}
	}

	/**
	 * 登录日志配置
	 */
	public static class Login {
		/**
		 * 是否启用日志
		 */
		private boolean enable = false;
		/**
		 * 登录地址
		 */
		private String loginUrl = "/login";
		/**
		 * 退出地址
		 */
		private String logoutUrl = "/logout";

		public boolean isEnable() {
			return enable;
		}

		public void setEnable(boolean enable) {
			this.enable = enable;
		}

		public String getLoginUrl() {
			return loginUrl;
		}

		public void setLoginUrl(String loginUrl) {
			this.loginUrl = loginUrl;
		}

		public String getLogoutUrl() {
			return logoutUrl;
		}

		public void setLogoutUrl(String logoutUrl) {
			this.logoutUrl = logoutUrl;
		}
	}
}
