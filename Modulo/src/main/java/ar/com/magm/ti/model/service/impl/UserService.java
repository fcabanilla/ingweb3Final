package ar.com.magm.ti.model.service.impl;

import ar.com.magm.ti.exception.NotFoundException;
import ar.com.magm.ti.model.User;
import ar.com.magm.ti.model.dao.IUserDAO;
import ar.com.magm.ti.model.service.IUserService;
import ar.com.magm.ti.persistence.exception.PersistenceException;
import ar.com.magm.ti.service.exception.ServiceException;
import ar.com.magm.ti.service.impl.GenericService;

public class UserService extends GenericService<User, Integer> implements IUserService {
	private IUserDAO dao;

	public UserService(IUserDAO dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public User load(String username) throws ServiceException, NotFoundException {
		try {
			return dao.load(username);
		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
