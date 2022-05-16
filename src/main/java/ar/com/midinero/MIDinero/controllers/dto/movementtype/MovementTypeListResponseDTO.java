package ar.com.midinero.MIDinero.controllers.dto.movementtype;

import java.util.List;

import ar.com.midinero.MIDinero.controllers.dto.commons.StateDTO;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MovementTypeListResponseDTO {
	@NonNull
	private StateDTO stateMovementType;
	@NonNull
	private List<MovementTypeDTO> movementTypeList;
}
