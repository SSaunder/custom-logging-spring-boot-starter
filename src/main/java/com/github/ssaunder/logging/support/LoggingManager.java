package com.github.ssaunder.logging.support;

public interface LoggingManager {

	default String getUsername() {
		return "anonymous";
	}
}
