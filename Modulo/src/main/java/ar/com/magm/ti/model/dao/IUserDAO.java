package ar.com.magm.ti.model.dao;

import ar.com.magm.ti.exception.NotFoundException;
import ar.com.magm.ti.model.User;
import ar.com.magm.ti.persistence.dao.IGenericDAO;
import ar.com.magm.ti.persistence.exception.PersistenceException;

public interface IUserDAO extends IGenericDAO<User, Integer> {
	public User load(String username) throws PersistenceException, NotFoundException;
}
