package ar.com.midinero.MIDinero.controllers.dto.authentication;

import ar.com.midinero.MIDinero.controllers.dto.commons.StateDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.UserDTO;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthenticationResponse {
	@NonNull
	private StateDTO stateAuthentication;
	private String token;
	private String bearer = "Bearer";
	private UserDTO userDTO;
}
