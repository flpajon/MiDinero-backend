package ar.com.midinero.MIDinero.controllers.dto.menu;

import java.util.List;

import ar.com.midinero.MIDinero.controllers.dto.commons.StateDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class MenuListResponseDTO {
	@NonNull
	private StateDTO stateMenuList;
	@NonNull
	private List<MenuDTO> menuList;
}
