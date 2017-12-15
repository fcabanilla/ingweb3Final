package ar.com.magm.ti.web.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> list() {
		try {
			return new ResponseEntity<Object>(entidadService.list(), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(getSimpleResponse(-1, e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
