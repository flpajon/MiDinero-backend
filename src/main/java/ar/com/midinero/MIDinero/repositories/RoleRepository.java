package ar.com.midinero.MIDinero.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.midinero.MIDinero.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role getByRoleName(String roleName);

}
