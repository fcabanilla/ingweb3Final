package ar.com.magm.ti.model.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.magm.ti.model.Persona;
import ar.com.magm.ti.model.dao.IPersonaDAO;
import ar.com.magm.ti.model.service.IPersonaService;
import ar.com.magm.ti.persistence.exception.PersistenceException;
import ar.com.magm.ti.service.exception.ServiceException;
import ar.com.magm.ti.service.impl.GenericService;

public class PersonaService extends GenericService<Persona, Integer>
		implements IPersonaService {
	private static Logger LOG = LoggerFactory.getLogger(PersonaService.class);
	private IPersonaDAO dao;
	public PersonaService(IPersonaDAO dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<Persona> list(String parteDelNombre) throws ServiceException {
		try {
			return dao.list(parteDelNombre);
		} catch (PersistenceException e) {
			LOG.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
}
