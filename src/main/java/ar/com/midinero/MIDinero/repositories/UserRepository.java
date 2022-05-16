package ar.com.midinero.MIDinero.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.midinero.MIDinero.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Boolean existsByUserNameIgnoreCaseOrUserPersonPersonEmail(String userName, String personEmail);
	User findByUserName(String username);
	Boolean existsByUserNameIgnoreCase(String userName);
	List<User> findAllByUserRoleRoleName(String roleName);

}
