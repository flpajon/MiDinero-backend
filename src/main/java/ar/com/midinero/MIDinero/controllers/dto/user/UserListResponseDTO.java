package ar.com.midinero.MIDinero.controllers.dto.user;

import java.util.List;

import ar.com.midinero.MIDinero.controllers.dto.commons.StateDTO;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserListResponseDTO {
	@NonNull
	private StateDTO stateUserList;
	@NonNull
	private List<UserDTO> userList;
}
