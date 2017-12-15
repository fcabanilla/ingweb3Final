package ar.com.magm.ti.web.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


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

}
