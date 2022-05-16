package ar.com.midinero.MIDinero.services.authentication;

import ar.com.midinero.MIDinero.controllers.dto.authentication.AuthenticationRequest;
import ar.com.midinero.MIDinero.controllers.dto.authentication.AuthenticationResponse;

public interface AuthenticationService {
	
	public AuthenticationResponse userLogIn(AuthenticationRequest authentication);

}
