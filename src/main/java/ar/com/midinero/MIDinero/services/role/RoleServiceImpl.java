package ar.com.midinero.MIDinero.services.role;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.midinero.MIDinero.controllers.dto.commons.StateDTO;
import ar.com.midinero.MIDinero.controllers.dto.menu.MenuDTO;
import ar.com.midinero.MIDinero.controllers.dto.menu.MenuListResponseDTO;
import ar.com.midinero.MIDinero.models.Menu;
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

	@Override
	public MenuListResponseDTO getMenuListFromRoleName(String roleName) {
		List<Menu> menuList = roleRepository.getByRoleName(roleName).getRoleMenuList();
		if (menuList.size() > 0) {
			return new MenuListResponseDTO(new StateDTO(0, "Full menu list"), menuList.stream()
					.map(menu -> new MenuDTO(menu.getMenuName(), menu.getMenuEndpoint())).collect(Collectors.toList()));
		}
		return new MenuListResponseDTO(new StateDTO(1, "Empty menu list"), new ArrayList<MenuDTO>());
	}

}
