package ar.com.midinero.MIDinero.services.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.midinero.MIDinero.models.Role;
import ar.com.midinero.MIDinero.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Role getRoleByRoleName(String roleName) {
		return roleRepository.getByRoleName(roleName);
	}

}
