package ar.com.magm.ti.app.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import ar.com.magm.ti.app.config.auth.UserDetailService;
import ar.com.magm.ti.app.config.auth.rememberme.PersistentTokenRememberMeService;
import ar.com.magm.ti.model.service.IAuthTokenService;
import ar.com.magm.ti.web.services.Constants;

/**
 * 
 * @author magm
 *
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
@PropertySource({ "classpath:/config/security.properties",
		"classpath:/config/security-${spring.profiles.active:local}.properties" })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;

	@Bean
	public Filter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.addFilterBefore(corsFilter(), ChannelProcessingFilter.class);

		http.authorizeRequests().antMatchers(Constants.URL_BASE + "/**").authenticated();
		http.rememberMe().rememberMeServices(rememberMeServices()).key("w3rm").alwaysRemember(true);

		if (Boolean.parseBoolean(env.getProperty("auth.basic", "true"))) {
			http.httpBasic();
		}
		if (Boolean.parseBoolean(env.getProperty("auth.form", "true"))) {
			FormLoginConfigurer<HttpSecurity> flc = http.formLogin();
			if (env.getProperty("auth.form.login.page", "").length() > 0) {
				flc.loginPage(env.getProperty("auth.form.login.page")).permitAll().loginProcessingUrl("/login");
			}
			flc.defaultSuccessUrl(env.getProperty("auth.form.login.success", "/")).permitAll().and().logout()
					.permitAll().logoutSuccessUrl(env.getProperty("logout.success.url", "/login?logout"));
		}

		if (Boolean.parseBoolean(env.getProperty("ensure.https", "false")))
			http.requiresChannel().antMatchers("/**").requiresSecure();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();

	}

	@Autowired
	private UserDetailService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		if (Boolean.parseBoolean(env.getProperty("in.memory.users", "true"))) {
			auth.inMemoryAuthentication().withUser("user").password("password").roles("USER", "ADMIN").and()
					.withUser("admin").password("password").roles("USER", "ADMIN");
		} else {
			auth.userDetailsService(userDetailsService);
		}
	}

	@Autowired
	private IAuthTokenService authTokenService;

	@Bean
	public RememberMeServices rememberMeServices() {
		PersistentTokenRememberMeService rememberMeServices = new PersistentTokenRememberMeService("w3rm",
				userDetailsService, authTokenService);
		rememberMeServices.setAlwaysRemember(true);
		rememberMeServices.setCookieName("rmw3");
		rememberMeServices.setUseSecureCookie(false);
		rememberMeServices.setTokenValiditySeconds(60 * 60 * 24);
		return rememberMeServices;
	}

}
