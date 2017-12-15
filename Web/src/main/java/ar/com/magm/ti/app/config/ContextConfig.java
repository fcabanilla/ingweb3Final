package ar.com.magm.ti.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
/**
 * 
 * @author magm
 *
 */
@ComponentScan({ "ar.com.magm.ti.app", "ar.com.magm.ti.web" })
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ContextConfig {
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		final PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		return pspc;
	}


}
