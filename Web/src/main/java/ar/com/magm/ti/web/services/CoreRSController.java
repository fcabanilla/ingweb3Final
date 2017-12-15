package ar.com.magm.ti.web.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = Constants.URL_CORE)
// @PreAuthorize("hasRole('ROLE_USER')")
public class CoreRSController extends BaseRSController {
	
	
	@RequestMapping(value = "/pingAuth", method = RequestMethod.GET)
	public ResponseEntity<String> pingAuth() {
		return new ResponseEntity<String>("{\"code\":0,\"username\": \"" + getUserPrincipal().getUsername() + "\"}",
				HttpStatus.OK);

	}
	
	@RequestMapping(value = "/loginsuccess", method = RequestMethod.GET)
	public ResponseEntity<String> loginsuccess() {
		return new ResponseEntity<String>(getBasicUserInfo(),
				HttpStatus.OK);

	}
	

	

}
