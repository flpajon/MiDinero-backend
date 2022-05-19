package ar.com.midinero.MIDinero.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.midinero.MIDinero.controllers.dto.commons.StateDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.ActivateUserRequestDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.ActivateUserResponseDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.DesactivateUserRequestDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.DesactivateUserResponseDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.NewUserRequestDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.NewUserResponseDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.UserListResponseDTO;
import ar.com.midinero.MIDinero.services.user.UserService;

@RestController
@RequestMapping(value = "v1/user")
public class UserController {

	private final static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@GetMapping(value = "/list")
	public ResponseEntity<?> getUsers() {
		try {
			return new ResponseEntity<UserListResponseDTO>(userService.getUsers(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<StateDTO>(new StateDTO(500, "Server problems."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/new")
	public ResponseEntity<?> newUser(@RequestBody NewUserRequestDTO newUser) {
		try {
			return new ResponseEntity<NewUserResponseDTO>(userService.createNewUser(newUser), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<StateDTO>(new StateDTO(500, "Server problems"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/deactivate")
	public ResponseEntity<?> deactivateUser(@RequestBody DesactivateUserRequestDTO deactivateUser) {
		try {
			return new ResponseEntity<DesactivateUserResponseDTO>(userService.desactivateUser(deactivateUser),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<StateDTO>(new StateDTO(500, "Server problems"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/activate")
	public ResponseEntity<?> activateUser(@RequestBody ActivateUserRequestDTO activateUser) {
		try {
			return new ResponseEntity<ActivateUserResponseDTO>(userService.activateUser(activateUser), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<StateDTO>(new StateDTO(500, "Server problems"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
