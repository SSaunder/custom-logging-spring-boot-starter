package com.github.ssaunder.logging.annotation;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;
import org.springframework.context.annotation.AutoProxyRegistrar;

public class LoggingManagementConfigurationSelector extends AdviceModeImportSelector<EnableLoggingManagement> {

	@Override
	protected String[] selectImports(AdviceMode adviceMode) {
		switch (adviceMode) {
			case PROXY:
				return new String[] {AutoProxyRegistrar.class.getName(),
						ProxyLoggingManagementConfiguration.class.getName()};
			case ASPECTJ:
			default:
				return null;
		}
	}
}
