package ar.com.magm.ti.app;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import ar.com.magm.ti.model.dao.IEntidadDAO;
import ar.com.magm.ti.model.dao.IUserDAO;
import ar.com.magm.ti.model.dao.hibernate.EntidadDAO;
import ar.com.magm.ti.model.dao.hibernate.UserDAO;
import ar.com.magm.ti.model.service.IEntidadService;
import ar.com.magm.ti.model.service.IUserService;
import ar.com.magm.ti.model.service.impl.EntidadService;
import ar.com.magm.ti.model.service.impl.UserService;
/**
 * 
 * @author magm
 *
 */
@Component
public class Beans {

	//DAO
	@Bean
	@Autowired
	public IEntidadDAO entidadDAO(final SessionFactory sessionFactory) {
		return new EntidadDAO(sessionFactory);
	}
	@Bean
	@Autowired
	public IUserDAO userDAO(final SessionFactory sessionFactory) {
		return new UserDAO(sessionFactory);
	}
	//Services
	
	
	@Bean
	@Autowired
	public IEntidadService entidadService(final IEntidadDAO entidadDAO) {
		return new EntidadService(entidadDAO);
	}
	
	@Bean
	@Autowired
	public IUserService userService(final IUserDAO userDAO) {
		return new UserService(userDAO);
	}

	
}
