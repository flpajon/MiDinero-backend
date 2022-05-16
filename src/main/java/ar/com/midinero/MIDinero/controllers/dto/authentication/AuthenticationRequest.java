package ar.com.midinero.MIDinero.controllers.dto.authentication;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthenticationRequest {
	private String userName;
	private String password;
}
