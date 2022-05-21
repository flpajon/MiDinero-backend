package ar.com.midinero.MIDinero.controllers.dto.movementtype;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class MovementTypeDTO {
	@NonNull
	private Long movementTypeId;
	@NonNull
	private String movementTypeName;
	@NonNull
	private Boolean movementIsPositive;
}
