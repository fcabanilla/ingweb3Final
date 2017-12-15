package ar.com.magm.ti.web.services;

import org.jooq.tools.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import ar.com.magm.ti.model.User;
import ar.com.magm.ti.service.SimpleResponse;

/**
 * 
 * @author magm
 *
 */
@PropertySource({ "classpath:/config/security.properties",
		"classpath:/config/security-${spring.profiles.active:local}.properties" })
public class BaseRSController {

	protected UserDetails getUserPrincipal() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails user = (UserDetails) auth.getPrincipal();
		return user;
	}

	@SuppressWarnings("unchecked")
	protected String getBasicUserInfo() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		JSONObject obj = new JSONObject();
		if (user != null) {
			obj.put("nombre", user.getFirstName());
			obj.put("apellido", user.getLastName());
			obj.put("username", user.getUsername());
			obj.put("mail", user.getEmail());
			obj.put("roles", user.getRoles());
		}
		return obj.toString();
	}

	@Autowired
	private Environment env;

	protected SimpleResponse getSimpleResponse(int code, Throwable tr) {
		if (Boolean.parseBoolean(env.getProperty("exception.verbose", "false"))) {
			return new SimpleResponse(code, tr);
		} else {
			return new SimpleResponse(code, tr.getMessage());

		}
	}

}
