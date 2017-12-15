package ar.com.magm.ti.model.service;

import ar.com.magm.ti.exception.NotFoundException;
import ar.com.magm.ti.model.User;
import ar.com.magm.ti.service.IGenericService;
import ar.com.magm.ti.service.exception.ServiceException;

public interface IUserService extends IGenericService<User, Integer> {
	public User load(String username) throws ServiceException, NotFoundException;
}
