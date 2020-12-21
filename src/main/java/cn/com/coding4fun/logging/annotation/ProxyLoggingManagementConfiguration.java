package cn.com.coding4fun.logging.annotation;

import cn.com.coding4fun.logging.configuration.LoggingProperties;
import cn.com.coding4fun.logging.support.AbstractLoggingManager;
import cn.com.coding4fun.logging.support.LoggingManagerImpl;
import cn.com.coding4fun.logging.support.LoggingPointcutAdvisor;
import cn.com.coding4fun.logging.support.LoggingPointcutThrowingAdvisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.context.annotation.Role;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

@Configuration(proxyBeanMethods = false)
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class ProxyLoggingManagementConfiguration implements ImportAware {

	protected AnnotationAttributes enableLogging;

	@Bean
	@ConditionalOnMissingBean(AbstractLoggingManager.class)
	public LoggingManagerImpl loggingManager(@Autowired LoggingProperties properties) {
		return new LoggingManagerImpl(properties);
	}

//	@Bean
//	public LoggingManagerMongoImpl loggingManager(@Autowired LoggingProperties properties,
//												  @Autowired MongoTemplate template) {
//		return new LoggingManagerMongoImpl(properties, template);
//	}

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
