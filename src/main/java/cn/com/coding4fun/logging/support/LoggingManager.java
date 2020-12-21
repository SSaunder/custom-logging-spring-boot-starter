package cn.com.coding4fun.logging.support;

public interface LoggingManager {

	default String getUsername() {
		return "anonymous";
	}
}
