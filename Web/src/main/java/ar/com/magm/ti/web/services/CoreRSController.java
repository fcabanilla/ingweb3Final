package ar.com.magm.ti.web.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author magm
 *
 */
@RestController
public class CoreRSController extends BaseRSController {
	@RequestMapping(value = Constants.URL_DENY, method = RequestMethod.GET)
	public ResponseEntity<String> deny() {
		return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = Constants.URL_LOGINSUCCESS, method = RequestMethod.GET)
	public ResponseEntity<String> loginSuccess() {
		return new ResponseEntity<String>(getBasicUserInfo(), HttpStatus.OK);
	}

}
