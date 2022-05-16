package ar.com.midinero.MIDinero.controllers.dto.user;

import ar.com.midinero.MIDinero.controllers.dto.commons.StateDTO;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ActivateUserResponseDTO {
	@NonNull
	private StateDTO stateActivateUser;
}
