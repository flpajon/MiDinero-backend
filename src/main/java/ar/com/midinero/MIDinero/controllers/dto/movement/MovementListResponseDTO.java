package ar.com.midinero.MIDinero.controllers.dto.movement;

import java.util.List;

import ar.com.midinero.MIDinero.controllers.dto.commons.StateDTO;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MovementListResponseDTO {
	@NonNull
	private StateDTO stateMovement;
	@NonNull
	private List<MovementDTO> movementList; 
	
}
