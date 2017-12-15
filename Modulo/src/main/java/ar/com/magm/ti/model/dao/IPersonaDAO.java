package ar.com.magm.ti.model.dao;

import java.util.List;

import ar.com.magm.ti.model.Persona;
import ar.com.magm.ti.persistence.dao.IGenericDAO;
import ar.com.magm.ti.persistence.exception.PersistenceException;

public interface IPersonaDAO extends IGenericDAO<Persona, Integer>{
	public List<Persona> list(String parteDelNombre) throws PersistenceException; 
}
