package ar.com.midinero.MIDinero.controllers.dto.menu;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class MenuDTO {
	@NonNull
	private String menuName;
	@NonNull
	private String menuEndpoint;
	@NonNull
	private Integer menuOrder;
}
