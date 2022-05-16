package ar.com.midinero.MIDinero.controllers.dto.user;

import ar.com.midinero.MIDinero.controllers.dto.person.PersonDTO;
import ar.com.midinero.MIDinero.controllers.dto.role.RoleDTO;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDTO {
	@NonNull
	private Long userId;
	@NonNull
	private String userName;
	@NonNull
	private RoleDTO userRole;
	@NonNull
	private Boolean userIsActive;
	@NonNull
	private PersonDTO userPerson;
}
