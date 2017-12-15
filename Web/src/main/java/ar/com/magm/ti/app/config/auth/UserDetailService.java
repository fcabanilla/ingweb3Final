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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User r = null;
		try {
			r = userService.load(username);
			LOG.debug("User logged: {} - {}", r.getUsername(), r.getAuthorities());
		} catch (ServiceException e) {
		} catch (NotFoundException e) {
			throw new UsernameNotFoundException(username + " no encontrado");
		}
		return r;
	}

}