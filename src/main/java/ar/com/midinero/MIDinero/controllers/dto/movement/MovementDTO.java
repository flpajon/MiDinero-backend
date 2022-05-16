package ar.com.midinero.MIDinero.controllers.dto.movement;

import java.util.Date;

import ar.com.midinero.MIDinero.controllers.dto.movementtype.MovementTypeDTO;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MovementDTO {
	@NonNull
	private Date movementDate;
	@NonNull
	private String movementDescription;
	@NonNull
	private MovementTypeDTO movementType;
	@NonNull
	private Double movementAmount;
}
