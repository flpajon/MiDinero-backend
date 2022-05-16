package ar.com.midinero.MIDinero.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.midinero.MIDinero.controllers.dto.commons.StateDTO;
import ar.com.midinero.MIDinero.controllers.dto.movement.MovementAccountResponseDTO;
import ar.com.midinero.MIDinero.controllers.dto.movement.MovementListResponseDTO;
import ar.com.midinero.MIDinero.controllers.dto.movement.MovementsAccountStatusResponseDTO;
import ar.com.midinero.MIDinero.controllers.dto.movement.NewMovementRequestDTO;
import ar.com.midinero.MIDinero.controllers.dto.movement.NewMovementResponseDTO;
import ar.com.midinero.MIDinero.services.movement.MovementService;

@RestController
@RequestMapping(value = "v1/movement")
public class MovementController {

	private final static Logger logger = LoggerFactory.getLogger(MovementController.class);

	@Autowired
	MovementService movementService;

	@GetMapping(value = "/list", params = { "userId" })
	public ResponseEntity<MovementListResponseDTO> getUserMovements(@RequestParam(name = "userId") Long userId) {
		try {
			return new ResponseEntity<MovementListResponseDTO>(movementService.getMovements(userId), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<MovementListResponseDTO>(
					new MovementListResponseDTO(new StateDTO(500, "Server problems."), new ArrayList<>()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/accountState", params = { "userId", "filter" })
	public ResponseEntity<MovementAccountResponseDTO> getUserMovementCountFromFilter(
			@RequestParam(name = "userId") Long userId, @RequestParam(name = "filter") String filter) {
		try {
			return new ResponseEntity<MovementAccountResponseDTO>(movementService.getMovementCount(userId, filter),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<MovementAccountResponseDTO>(
					new MovementAccountResponseDTO(new StateDTO(500, "Server problems."), new ArrayList<>()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/currentAccountState", params = { "userId" })
	public ResponseEntity<MovementsAccountStatusResponseDTO> getUserMovementAccountStatus(
			@RequestParam(name = "userId") Long userId) {
		try {
			return new ResponseEntity<MovementsAccountStatusResponseDTO>(movementService.getCurrentAccountStatement(userId),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<MovementsAccountStatusResponseDTO>(
					new MovementsAccountStatusResponseDTO(new StateDTO(500, "Server problems."), "0"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PostMapping(value = "/new")
	public ResponseEntity<NewMovementResponseDTO> newMovement(@RequestBody NewMovementRequestDTO newMovement) {
		try {
			return new ResponseEntity<NewMovementResponseDTO>(movementService.newMovement(newMovement), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<NewMovementResponseDTO>(
					new NewMovementResponseDTO(new StateDTO(500, "Server problems.")),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
