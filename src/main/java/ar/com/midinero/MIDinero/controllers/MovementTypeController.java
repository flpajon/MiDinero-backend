package ar.com.midinero.MIDinero.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.midinero.MIDinero.controllers.dto.commons.StateDTO;
import ar.com.midinero.MIDinero.controllers.dto.movementtype.MovementTypeListResponseDTO;
import ar.com.midinero.MIDinero.services.movementtype.MovementTypeService;

@RestController
@RequestMapping(value = "v1/movement-type")
public class MovementTypeController {
	
	private final static Logger logger = LoggerFactory.getLogger(MovementTypeController.class);
	
	@Autowired
	MovementTypeService movementTypeService;

	@GetMapping(value = "/list")
	public ResponseEntity<MovementTypeListResponseDTO> getMovementTypes() {
		try {
			return new ResponseEntity<MovementTypeListResponseDTO>(movementTypeService.getMovementTypes(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<MovementTypeListResponseDTO>(
					new MovementTypeListResponseDTO(new StateDTO(500, "Server problems."), new ArrayList<>()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
