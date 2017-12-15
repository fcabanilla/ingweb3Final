package ar.com.magm.ti.service;

import java.io.Serializable;
import java.util.List;

import ar.com.magm.ti.service.exception.ServiceException;
import javassist.NotFoundException;

/**
 * 
 * @author magm
 *
 * @param <Entity>
 * @param <PK>
 */
public interface IGenericService<Entity, PK extends Serializable> {

	public void delete(Entity entity) throws ServiceException, NotFoundException;

	public List<Entity> list() throws ServiceException;

	public Entity load(PK id) throws ServiceException, NotFoundException;

	public Entity save(Entity entity) throws ServiceException;

	public Entity saveOrUpdate(Entity entity) throws ServiceException;

	public Entity update(Entity entity) throws ServiceException, NotFoundException;
}
