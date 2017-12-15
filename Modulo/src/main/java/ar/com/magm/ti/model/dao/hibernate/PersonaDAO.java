package ar.com.magm.ti.model.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.magm.ti.model.Persona;
import ar.com.magm.ti.model.dao.IPersonaDAO;
import ar.com.magm.ti.persistence.dao.hibernate.GenericDAO;
import ar.com.magm.ti.persistence.exception.PersistenceException;

public class PersonaDAO extends GenericDAO<Persona, Integer> implements IPersonaDAO {
	private static Logger LOG = LoggerFactory.getLogger(PersonaDAO.class);
	public PersonaDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> list(String parteDelNombre) throws PersistenceException {
		List<Persona> l = null;
		try {
			l = getSession().createQuery("FROM Persona p WHERE p.nombre LIKE :parteNombre")
					.setString("parteNombre", "%"+parteDelNombre+"%").list();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new PersistenceException(e.getMessage(), e);
		} finally {
			closeSession();
		}
		return l;
	}
	

}
