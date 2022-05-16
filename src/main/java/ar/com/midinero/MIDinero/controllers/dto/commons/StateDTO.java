package ar.com.midinero.MIDinero.controllers.dto.commons;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StateDTO {
	@NonNull
	private Integer statusCode;
	@NonNull
	private String statusMessage;
}
