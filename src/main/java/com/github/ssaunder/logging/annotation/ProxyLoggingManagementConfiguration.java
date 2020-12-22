package com.github.ssaunder.logging.annotation;

import com.github.ssaunder.logging.configuration.LoggingProperties;
import com.github.ssaunder.logging.support.AbstractLoggingManager;
import com.github.ssaunder.logging.support.LoggingManagerImpl;
import com.github.ssaunder.logging.support.LoggingManagerMongoImpl;
import com.github.ssaunder.logging.support.LoggingPointcutAdvisor;
import com.github.ssaunder.logging.support.LoggingPointcutThrowingAdvisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.context.annotation.Role;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration(proxyBeanMethods = false)
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class ProxyLoggingManagementConfiguration implements ImportAware {

	protected AnnotationAttributes enableLogging;

	@Configuration
	@ConditionalOnProperty(prefix = "custom.logging", value = "runtime-env", havingValue = "default")
	public static class DefaultLoggingManagerInstance {

		@Bean
		@ConditionalOnMissingBean(AbstractLoggingManager.class)
		public LoggingManagerImpl defaultLoggingManager(@Autowired LoggingProperties properties) {
			return new LoggingManagerImpl(properties);
		}
	}

	@Configuration
	@ConditionalOnProperty(prefix = "custom.logging", value = "runtime-env", havingValue = "mongo")
	public static class MongoLoggingManagerInstance {

		@Bean
		@ConditionalOnMissingBean(AbstractLoggingManager.class)
		public LoggingManagerMongoImpl mongoLoggingManager(@Autowired LoggingProperties properties,
														   @Autowired MongoTemplate template) {
			return new LoggingManagerMongoImpl(properties, template);
		}
	}

	@Bean
	@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
	public LoggingPointcutAdvisor loggingPointcutAdvisor(@Autowired AbstractLoggingManager loggingManager) {
		return new LoggingPointcutAdvisor(loggingManager);
	}

	@Bean
	@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
	public DefaultPointcutAdvisor loggingPointcutThrowingAdvisor(@Autowired AbstractLoggingManager loggingManager) {
		return new DefaultPointcutAdvisor(
				new AnnotationMatchingPointcut(LoggingModule.class, LoggingOperation.class),
				new LoggingPointcutThrowingAdvisor(loggingManager)
		);
	}

	@Override
	public void setImportMetadata(AnnotationMetadata importMetadata) {
		this.enableLogging = AnnotationAttributes.fromMap(
				importMetadata.getAnnotationAttributes(EnableLoggingManagement.class.getName(), false));
		if (this.enableLogging == null) {
			throw new IllegalArgumentException(
					"@EnableLoggingManagement is not present on importing class " + importMetadata.getClassName());
		}
	}


}
