package ar.com.magm.ti.app;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import ar.com.magm.ti.model.dao.IEntidadDAO;
import ar.com.magm.ti.model.dao.hibernate.EntidadDAO;
import ar.com.magm.ti.model.service.IEntidadService;
import ar.com.magm.ti.model.service.impl.EntidadService;
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
	
	//Services
	
	
	@Bean
	@Autowired
	public IEntidadService entidadService(final IEntidadDAO entidadDAO) {
		return new EntidadService(entidadDAO);
	}

	
}
