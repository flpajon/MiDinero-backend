package ar.com.midinero.MIDinero.controllers.dto.role;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RoleDTO {
	@NonNull
	private Long roleId;
	@NonNull
	private String roleName;
}
