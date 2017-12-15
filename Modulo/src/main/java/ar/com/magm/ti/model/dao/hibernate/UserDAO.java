package ar.com.magm.ti.model.dao.hibernate;

import org.hibernate.SessionFactory;

import ar.com.magm.ti.exception.NotFoundException;
import ar.com.magm.ti.model.User;
import ar.com.magm.ti.model.dao.IUserDAO;
import ar.com.magm.ti.persistence.dao.hibernate.GenericDAO;
import ar.com.magm.ti.persistence.exception.PersistenceException;

public class UserDAO extends GenericDAO<User, Integer> implements IUserDAO {

	public UserDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public User load(String username) throws PersistenceException, NotFoundException {
		User r=null;
		try {
			r = (User) getSession().createQuery(String.format("FROM %s WHERE username=:username", getDomainClass().getSimpleName())).setString("username", username).uniqueResult();
			if(r==null)
				throw new NotFoundException();
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage(), e);
		} finally {
			closeSession();
		}
		return r;
	}
	

}
