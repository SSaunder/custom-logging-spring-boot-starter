package com.github.ssaunder.logging.commons;

public enum LoggingSystemEnum {
	/**
	 * 未知系统
	 */
	UNKNOWN,
	LEARN,
	AGENCY,
	RESOURCE,
	DATA_CENTER,
	COMMONS_SERVICE,
	WECHAT;

	@Override
	public String toString() {
		return "LoggingSystemEnum{} " + super.toString();
	}
}
