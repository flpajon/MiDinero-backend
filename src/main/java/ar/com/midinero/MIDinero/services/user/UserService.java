package ar.com.midinero.MIDinero.services.user;

import ar.com.midinero.MIDinero.controllers.dto.user.ActivateUserRequestDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.ActivateUserResponseDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.DesactivateUserRequestDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.DesactivateUserResponseDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.NewUserRequestDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.NewUserResponseDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.UserListResponseDTO;
import ar.com.midinero.MIDinero.models.User;

public interface UserService {
	
	public NewUserResponseDTO createNewUser(NewUserRequestDTO newUser);
	public DesactivateUserResponseDTO desactivateUser(DesactivateUserRequestDTO user);
	public ActivateUserResponseDTO activateUser(ActivateUserRequestDTO user);
	public UserListResponseDTO getUsers();
	public Boolean validateUserNameAndUserPassword(String userName, String password);
	public User getUserFromUserName(String userName);
	public User getUserFromUserId(Long userId);
}
