package ar.com.midinero.MIDinero.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.midinero.MIDinero.controllers.dto.authentication.AuthenticationRequest;
import ar.com.midinero.MIDinero.controllers.dto.authentication.AuthenticationResponse;
import ar.com.midinero.MIDinero.controllers.dto.commons.StateDTO;
import ar.com.midinero.MIDinero.services.authentication.AuthenticationService;

@RestController
@RequestMapping(value = "v1/auth")
public class AuthenticationController {

	private final static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	AuthenticationService authenticationService;

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ResponseEntity<?> userLogIn(@RequestBody AuthenticationRequest userLogIn) {
		try {
			return new ResponseEntity<AuthenticationResponse>(authenticationService.userLogIn(userLogIn),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<StateDTO>(new StateDTO(500, "Server problems."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
