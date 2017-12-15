package ar.com.magm.ti.model.service;

import java.util.List;

import ar.com.magm.ti.model.Persona;
import ar.com.magm.ti.service.IGenericService;
import ar.com.magm.ti.service.exception.ServiceException;

public interface IPersonaService extends IGenericService<Persona, Integer> {
	public List<Persona> list(String parteDelNombre) throws ServiceException; 
}
