package ar.com.magm.ti.model.dao.hibernate;

import org.hibernate.SessionFactory;

import ar.com.magm.ti.model.Entidad;
import ar.com.magm.ti.model.dao.IEntidadDAO;
import ar.com.magm.ti.persistence.dao.hibernate.GenericDAO;

public class EntidadDAO extends GenericDAO<Entidad, Integer> implements IEntidadDAO {

	public EntidadDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	

}
