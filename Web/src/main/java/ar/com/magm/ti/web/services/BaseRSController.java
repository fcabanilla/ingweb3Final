package ar.com.magm.ti.web.services;

import org.jooq.tools.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import ar.com.magm.ti.model.User;

/**
 * 
 * @author magm
 *
 */
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

}
