package ar.com.midinero.MIDinero.services.role;

import ar.com.midinero.MIDinero.controllers.dto.menu.MenuListResponseDTO;
import ar.com.midinero.MIDinero.models.Role;

public interface RoleService {
	
	public Role getRoleByRoleName(String roleName);
	public MenuListResponseDTO getMenuListFromRoleName(String roleName);
}
