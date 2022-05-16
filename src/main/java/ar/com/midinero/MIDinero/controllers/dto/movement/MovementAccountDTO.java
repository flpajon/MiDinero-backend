package ar.com.midinero.MIDinero.controllers.dto.movement;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class MovementAccountDTO {
	@NonNull
	private String yearPeriodMovement;
	@NonNull
	private String monthPeriodMovement;
	@NonNull
	private String dayPeriodMovement;
	@NonNull
	private Long totalPositiveMomement;
	@NonNull
	private Long totalNegativeMomement;
}
