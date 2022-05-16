package ar.com.midinero.MIDinero.controllers.dto.movement;

import ar.com.midinero.MIDinero.controllers.dto.commons.StateDTO;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NewMovementResponseDTO {
	@NonNull
	private StateDTO stateNewMovement;
}
