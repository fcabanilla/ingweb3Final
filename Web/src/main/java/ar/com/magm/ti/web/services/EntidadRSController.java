package ar.com.magm.ti.web.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.magm.ti.exception.NotFoundException;
import ar.com.magm.ti.model.Entidad;
import ar.com.magm.ti.model.service.IEntidadService;
import ar.com.magm.ti.service.exception.ServiceException;

/**
 * 
 * @author magm
 *
 */
@RestController
@RequestMapping(value = Constants.URL_ENTIDAD)
public class EntidadRSController extends BaseRSController {
	private static Logger LOG = LoggerFactory.getLogger(EntidadRSController.class);
	@Autowired
	IEntidadService entidadService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<Object> list() {
		try {
			return new ResponseEntity<Object>(entidadService.list(), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(getSimpleResponse(-1, e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> load(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<Object>(entidadService.load(id), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(getSimpleResponse(-1, e), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>(getSimpleResponse(-1, e), HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public ResponseEntity<Object> add(@RequestBody Entidad entidad) {
		try {
			entidadService.save(entidad);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constants.URL_ENTIDAD + "/" + entidad.getIdEntidad());
			return new ResponseEntity<Object>(entidad,responseHeaders, HttpStatus.CREATED);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(getSimpleResponse(-1, e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = { "", "/" }, method = RequestMethod.PUT)
	public ResponseEntity<Object> edit(@RequestBody Entidad entidad) {
		try {
			entidadService.update(entidad);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constants.URL_ENTIDAD + "/" + entidad.getIdEntidad());
			return new ResponseEntity<Object>(entidad,responseHeaders, HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(getSimpleResponse(-1, e), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>(getSimpleResponse(-1, e), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> remove(@PathVariable("id") int id) {
		try {
			Entidad entidad = new Entidad();
			entidad.setIdEntidad(id);
			entidadService.delete(entidad);
			return new ResponseEntity<Object>( HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(getSimpleResponse(-1, e), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>(getSimpleResponse(-1, e), HttpStatus.NOT_FOUND);
		}
	}
}
