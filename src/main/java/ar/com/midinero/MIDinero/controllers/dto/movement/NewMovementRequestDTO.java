package ar.com.midinero.MIDinero.controllers.dto.movement;

import ar.com.midinero.MIDinero.controllers.dto.movementtype.MovementTypeDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class NewMovementRequestDTO {
	@NonNull
	private String movementDescription;
	@NonNull
	private MovementTypeDTO movementType;
	@NonNull
	private Double movementAmount;
	@NonNull
	private Long userId;
}
