package ar.com.magm.ti.app.setup;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
/**
 * 
 * @author magm
 *
 */
public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
	private static Logger LOG = LoggerFactory.getLogger(MyApplicationContextInitializer.class);
	

	/**
	 * Sets the active profile.
	 */
	@Override
	public void initialize(final ConfigurableApplicationContext applicationContext) {
		final ConfigurableEnvironment environment = applicationContext.getEnvironment();
		final String activeProfiles = environment.getProperty("spring.profiles.active");
		LOG.info("El perfil activo es: {}", activeProfiles);

		environment.setActiveProfiles(activeProfiles.split(","));
		

		
	}
	

}
