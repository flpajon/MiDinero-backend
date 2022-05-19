package ar.com.midinero.MIDinero.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.midinero.MIDinero.controllers.dto.commons.StateDTO;
import ar.com.midinero.MIDinero.controllers.dto.menu.MenuListResponseDTO;
import ar.com.midinero.MIDinero.services.role.RoleService;

@RestController
@RequestMapping(value = "v1/menu")
public class MenuController {

	private final static Logger logger = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	RoleService roleService;

	@GetMapping(params = { "roleName" })
	public ResponseEntity<?> getMenuListFromRoleName(@RequestParam(name = "roleName") String roleName) {
		try {
			return new ResponseEntity<MenuListResponseDTO>(roleService.getMenuListFromRoleName(roleName),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<StateDTO>(new StateDTO(500, "Server problems."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
