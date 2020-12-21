package cn.com.coding4fun.logging.configuration;

import cn.com.coding4fun.logging.annotation.EnableLoggingManagement;
import cn.com.coding4fun.logging.annotation.ProxyLoggingManagementConfiguration;
import cn.com.coding4fun.logging.filter.RequestDetailsFilter;
import com.maxmind.db.CHMCache;
import com.maxmind.geoip2.DatabaseReader;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Arrays;

@Configuration
@EnableConfigurationProperties({LoggingProperties.class})
@AutoConfigureAfter({AopAutoConfiguration.class, MongoAutoConfiguration.class} )
@ConditionalOnProperty(value = {"coding4fun.logging.enable"})
public class LoggingAutoConfiguration {

	@Bean
	@ConditionalOnWebApplication
	public FilterRegistrationBean requestDetailsFilter() {
		FilterRegistrationBean<RequestDetailsFilter> bean = new FilterRegistrationBean<>();
		bean.setFilter(new RequestDetailsFilter());
		bean.setName("requestDetailsFilter");
		bean.addUrlPatterns("/**");
		bean.setOrder(1);
		return bean;
	}

	@Bean(destroyMethod = "close")
	public DatabaseReader geoIp2Lite() throws IOException {
		return new DatabaseReader
				.Builder(new ClassPathResource("GeoLite2-City.mmdb").getInputStream())
				.locales(Arrays.asList("zh-CN"))
				.withCache(new CHMCache()).build();
	}

	@Configuration
	@ConditionalOnMissingBean(ProxyLoggingManagementConfiguration.class)
	public static class EnableTransactionManagementConfiguration {

		@Configuration
		@EnableLoggingManagement
		@ConditionalOnProperty(prefix = "spring.aop", name = "proxy-target-class", havingValue = "false", matchIfMissing = false)
		public static class JdkDynamicAutoProxyConfiguration {}
	}
}
