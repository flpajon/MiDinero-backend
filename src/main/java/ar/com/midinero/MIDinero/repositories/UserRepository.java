package ar.com.midinero.MIDinero.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.midinero.MIDinero.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Boolean existsByUserNameIgnoreCaseOrUserPersonPersonEmailIgnoreCase(String userName, String personEmail);
	User findByUserNameIgnoreCase(String username);
	Boolean existsByUserNameIgnoreCaseAndUserIsActiveTrue(String userName);
	List<User> findAllByUserRoleRoleName(String roleName);
	Boolean existsByUserNameIgnoreCaseAndUserIsActiveFalse(String userName);

}
