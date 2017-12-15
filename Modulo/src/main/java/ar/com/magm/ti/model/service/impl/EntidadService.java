package ar.com.magm.ti.model.service.impl;

import ar.com.magm.ti.model.Entidad;
import ar.com.magm.ti.model.dao.IEntidadDAO;
import ar.com.magm.ti.model.service.IEntidadService;
import ar.com.magm.ti.service.impl.GenericService;

public class EntidadService extends GenericService<Entidad, Integer>
		implements IEntidadService {

	public EntidadService(IEntidadDAO dao) {
		super(dao);
	}
	
}
