package ar.com.midinero.MIDinero.controllers.dto.movement;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NewMovementRequestDTO {
	@NonNull
	private MovementDTO movement;
	@NonNull
	private Long userId;
}
