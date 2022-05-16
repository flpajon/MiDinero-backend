package ar.com.midinero.MIDinero.services.movement;

import ar.com.midinero.MIDinero.controllers.dto.movement.MovementAccountResponseDTO;
import ar.com.midinero.MIDinero.controllers.dto.movement.MovementListResponseDTO;
import ar.com.midinero.MIDinero.controllers.dto.movement.MovementsAccountStatusResponseDTO;
import ar.com.midinero.MIDinero.controllers.dto.movement.NewMovementRequestDTO;
import ar.com.midinero.MIDinero.controllers.dto.movement.NewMovementResponseDTO;

public interface MovementService {

	MovementListResponseDTO getMovements(Long userId);
	NewMovementResponseDTO newMovement(NewMovementRequestDTO newMovement);
	MovementAccountResponseDTO getMovementCount(Long userId, String filter);
	MovementsAccountStatusResponseDTO getCurrentAccountStatement(Long userId);

}
