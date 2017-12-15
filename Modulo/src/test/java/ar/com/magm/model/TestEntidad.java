package ar.com.magm.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Test;

import ar.com.magm.ti.model.Entidad;
import ar.com.magm.ti.model.dao.hibernate.EntidadDAO;
import ar.com.magm.ti.model.service.IEntidadService;
import ar.com.magm.ti.model.service.impl.EntidadService;
import ar.com.magm.ti.service.exception.ServiceException;
import javassist.NotFoundException;

public class TestEntidad extends BaseTest {
	@Test
	public void saveAndLoad() throws ServiceException, NotFoundException {
		IEntidadService entidadService=new EntidadService(new EntidadDAO((SessionFactory) sessionFactory()));
		
		List<Entidad> l=entidadService.list();
		int size=l.size();
		Entidad e=new Entidad();
		e.setNombre("nombre");
		e=entidadService.save(e);
		assertTrue("Error id entidad", e.getIdEntidad()>-1 );
		assertEquals("Error nombre entidad", e.getNombre(), "nombre");
		int id=e.getIdEntidad();
		
		Entidad cargada=entidadService.load(id);
		assertEquals("Error en la carga", e,cargada);
		
		entidadService.delete(cargada);
		l=entidadService.list();
		assertEquals("Error eliminando", size,l.size());
		
		
	}
}