package ar.com.magm.ti.app.config.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ar.com.magm.ti.exception.NotFoundException;
import ar.com.magm.ti.model.User;
import ar.com.magm.ti.model.service.IUserService;
import ar.com.magm.ti.service.exception.ServiceException;

/**
 * 
 * @author magm
 *
 */
@Component
public class UserDetailService implements UserDetailsService {
	private static Logger LOG = LoggerFactory.getLogger(UserDetailService.class);
	@Autowired
	private IUserService userService;
	
	public static String AUTOLOGIN="**autoLogin**";
	public static String AUTOLOGIN_BYTOKEN="**autoLogin byToken**";

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		boolean autoLogin = false;
		boolean byToken = false;
		if (username.startsWith(AUTOLOGIN)) {
			autoLogin = true;
			username = username.substring(AUTOLOGIN.length(), username.length());
		}
		if (username.startsWith(AUTOLOGIN_BYTOKEN)) {
			autoLogin = true;
			byToken = true;
			username = username.substring(AUTOLOGIN_BYTOKEN.length(), username.length());
		}
		if (!autoLogin)
			LOG.debug("Try login: {}", username);
		User r = null;
		try {
			r = userService.load(username);
			LOG.debug("User logged: {} - {}", r.getUsername(), r.getAuthorities());
		} catch (ServiceException e) {
		} catch (NotFoundException e) {
			throw new UsernameNotFoundException(username + " no encontrado");
		}

		if (!autoLogin)
			LOG.debug("{} logged OK", username);
		LOG.debug("User logged: {} - {} [{}]", r.getUsername(), r.getAuthorities(),
				autoLogin ? (byToken ? "autologin byToken" : "autologin") : "login inicial");
		return r;
	}

}

/*
 * 
 * boolean autoLogin = false; boolean byToken = false; if
 * (userName.startsWith("**autoLogin**")) { autoLogin = true; userName =
 * userName.substring("**autoLogin**".length(), userName.length()); } if
 * (userName.startsWith("**autoLogin byToken**")) { autoLogin = true; byToken =
 * true; userName = userName.substring("**autoLogin byToken**".length(),
 * userName.length()); } if (!autoLogin) LOG.debug("Try login: {}", userName);
 * Preconditions.checkNotNull(userName); User r = null; try { r =
 * userService.load(userName); r.setLocalLogout("/logout");
 * r.setGlobalLogout("/logout"); // r.setLoguedBySSO(false); } catch
 * (ServiceException e) { LOG.error(e.getMessage(), e); e.printStackTrace(); }
 * catch (NotFoundException e) { throw new
 * UsernameNotFoundException(e.getMessage()); }
 * 
 * if (!autoLogin) LOG.debug("{} logged OK", userName);
 * LOG.trace("User logged: {} - {} [{}]", r.getUsername(), r.getAuthorities(),
 * autoLogin ? (byToken ? "autologin byToken" : "autologin") : "login inicial");
 * return r;
 */