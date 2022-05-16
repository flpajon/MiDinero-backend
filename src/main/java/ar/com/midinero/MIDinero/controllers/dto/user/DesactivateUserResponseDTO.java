package ar.com.midinero.MIDinero.controllers.dto.user;

import ar.com.midinero.MIDinero.controllers.dto.commons.StateDTO;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DesactivateUserResponseDTO {
	@NonNull
	private StateDTO stateDeactivateUser;
}
