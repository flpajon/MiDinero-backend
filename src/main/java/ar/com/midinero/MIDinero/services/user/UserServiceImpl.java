package ar.com.midinero.MIDinero.services.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ar.com.midinero.MIDinero.commons.Constants;
import ar.com.midinero.MIDinero.controllers.dto.commons.StateDTO;
import ar.com.midinero.MIDinero.controllers.dto.person.PersonDTO;
import ar.com.midinero.MIDinero.controllers.dto.role.RoleDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.ActivateUserRequestDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.ActivateUserResponseDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.DesactivateUserRequestDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.DesactivateUserResponseDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.NewUserRequestDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.NewUserResponseDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.UserDTO;
import ar.com.midinero.MIDinero.controllers.dto.user.UserListResponseDTO;
import ar.com.midinero.MIDinero.models.Person;
import ar.com.midinero.MIDinero.models.Role;
import ar.com.midinero.MIDinero.models.User;
import ar.com.midinero.MIDinero.repositories.UserRepository;
import ar.com.midinero.MIDinero.services.role.RoleService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleService roleService;

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public NewUserResponseDTO createNewUser(NewUserRequestDTO newUser) {
		if (!userRepository.existsByUserNameIgnoreCaseOrUserPersonPersonEmail(newUser.getUserName(),
				newUser.getPersonEmail())) {
			Role role = roleService.getRoleByRoleName(Constants.ROLE_NORMAL);
			Person newPerson = new Person(newUser.getPersonEmail(), newUser.getPersonName(),
					newUser.getPersonSurname());
			User newUserTemp = new User(newUser.getUserName(), passwordEncoder.encode(newUser.getUserPassword()), role,
					true, newPerson);
			userRepository.save(newUserTemp);
			return new NewUserResponseDTO(new StateDTO(0, "User created successfully"));
		}
		return new NewUserResponseDTO(new StateDTO(1, "User or email is already used"));

	}

	@Override
	public DesactivateUserResponseDTO desactivateUser(DesactivateUserRequestDTO user) {
		if (userRepository.existsById(user.getUser().getUserId())) {
			User userTemp = userRepository.findById(user.getUser().getUserId()).get();
			if (userTemp.getUserIsActive()) {
				userTemp.setUserIsActive(false);
				userRepository.save(userTemp);
				return new DesactivateUserResponseDTO(new StateDTO(0, "User deactivated successfully"));
			}
			return new DesactivateUserResponseDTO(new StateDTO(1, "User was already inactive"));
		}
		return new DesactivateUserResponseDTO(new StateDTO(2, "User does not exist"));
	}

	@Override
	public ActivateUserResponseDTO activateUser(ActivateUserRequestDTO user) {
		if (userRepository.existsById(user.getUser().getUserId())) {
			User userTemp = userRepository.findById(user.getUser().getUserId()).get();
			if (!userTemp.getUserIsActive()) {
				userTemp.setUserIsActive(true);
				userRepository.save(userTemp);
				return new ActivateUserResponseDTO(new StateDTO(0, "User activated successfully"));
			}
			return new ActivateUserResponseDTO(new StateDTO(1, "User was already active"));
		}
		return new ActivateUserResponseDTO(new StateDTO(2, "User does not exist"));
	}

	@Override
	public UserListResponseDTO getUsers() {
		List<User> userList = userRepository.findAllByUserRoleRoleName(Constants.ROLE_NORMAL);
		if (userList.size() > 0) {
			return new UserListResponseDTO(new StateDTO(0, "Full user list"), userList.stream().map(user -> {
				PersonDTO personDTO = new PersonDTO(user.getUserPerson().getPersonEmail(),
						user.getUserPerson().getPersonName(), user.getUserPerson().getPersonSurname());
				RoleDTO roleDTO = new RoleDTO(user.getUserRole().getRoleId(), user.getUserRole().getRoleName());
				return new UserDTO(user.getUserId(), user.getUserName(), roleDTO,
						user.getUserIsActive(), personDTO);
			}).collect(Collectors.toList()));
		}
		return new UserListResponseDTO(new StateDTO(1, "Empty user list"), new ArrayList<UserDTO>());
	}

	@Override
	public Boolean validateUserNameAndUserPassword(String userName, String password) {
		if(userRepository.existsByUserNameIgnoreCase(userName)){
			User userTemp = userRepository.findByUserName(userName);
			return passwordEncoder.matches(password, userTemp.getUserPassword());
		}
		return false;
	}

	@Override
	public User getUserFromUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public User getUserFromUserId(Long userId) {
		return userRepository.findById(userId).get();
	}
}
