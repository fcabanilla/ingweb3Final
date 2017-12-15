package ar.com.magm.ti.app.config.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
/**
 * 
 * @author magm
 *
 */
@Component
public class UserDetailService implements UserDetailsService {
	private static Logger LOG = LoggerFactory.getLogger(UserDetailService.class);
	//@Autowired
	//private IUserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		//Preconditions.checkNotNull(userName);
		User r=null;
		
		
		String[] rolesAdmin = "ROLE_USER,ROLE_ADMIN,ROLE_WRITE,ROLE_READ,ROLE_DELETE".split(",");
		 r = new User(userName,"password",AuthorityUtils.createAuthorityList(rolesAdmin));
		
		LOG.debug("User logged: {} - {}", r.getUsername(), r.getAuthorities());
		return r;
	}

}