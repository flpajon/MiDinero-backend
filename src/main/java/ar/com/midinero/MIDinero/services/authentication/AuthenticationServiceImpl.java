package ar.com.midinero.MIDinero.services.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.midinero.MIDinero.controllers.dto.authentication.AuthenticationRequest;
import ar.com.midinero.MIDinero.controllers.dto.authentication.AuthenticationResponse;
import ar.com.midinero.MIDinero.controllers.dto.commons.StateDTO;
import ar.com.midinero.MIDinero.controllers.dto.person.PersonDTO;
import ar.com.midinero.MIDinero.controllers.dto.role.RoleDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.UserDTO;
import ar.com.midinero.MIDinero.jwt.JwtProvider;
import ar.com.midinero.MIDinero.models.User;
import ar.com.midinero.MIDinero.services.user.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	UserService userService;

	@Autowired
	JwtProvider jwtProvider;

	@Override
	public AuthenticationResponse userLogIn(AuthenticationRequest authenticationRequest) {
		if (userService.validateUserNameAndUserPassword(authenticationRequest.getUserName(),
				authenticationRequest.getPassword())) {
			String jwt = jwtProvider.generateToken(authenticationRequest.getUserName());
			AuthenticationResponse authenticationResponse = new AuthenticationResponse(
					new StateDTO(0, "User authenticated successfully"));
			authenticationResponse.setToken(jwt);
			User user = userService.getUserFromUserName(authenticationRequest.getUserName());
			RoleDTO roleDTO = new RoleDTO(user.getUserRole().getRoleId(), user.getUserRole().getRoleName());
			PersonDTO personDTO = new PersonDTO(user.getUserPerson().getPersonEmail(), user.getUserPerson().getPersonName(), user.getUserPerson().getPersonSurname());
			authenticationResponse.setUserDTO(
					new UserDTO(user.getUserId(), user.getUserName(), roleDTO, user.getUserIsActive(), personDTO));
			return authenticationResponse;
		}
		return new AuthenticationResponse(new StateDTO(1,"Wrong user or password"));
	}

}
