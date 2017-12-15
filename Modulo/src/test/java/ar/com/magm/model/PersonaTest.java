package ar.com.magm.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Test;

import ar.com.magm.ti.model.Persona;
import ar.com.magm.ti.model.dao.hibernate.PersonaDAO;
import ar.com.magm.ti.model.service.IPersonaService;
import ar.com.magm.ti.model.service.impl.PersonaService;
import ar.com.magm.ti.service.exception.ServiceException;
public class PersonaTest extends BaseTest {

	@Test
	public void test1() throws ServiceException {
		IPersonaService service = new PersonaService(new PersonaDAO((SessionFactory) sessionFactory()));

		List<Persona> l = service.list();

		
		//assertEquals("Tamaño erróneo de la lista",0,l.size());
		
		Persona p=new Persona();
		p.setApellido("García");
		p.setNombre("Mariano");
		p.setFechaNacimiento(new Date());
		
		p=service.save(p);
		assertNotEquals("Se generó mal el id", 0,p.getDni());
		
	}
}
