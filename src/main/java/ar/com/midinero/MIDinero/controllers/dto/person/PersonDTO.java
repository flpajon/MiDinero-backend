package ar.com.midinero.MIDinero.controllers.dto.person;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PersonDTO {
	@NonNull
	private String personEmail;
	@NonNull
	private String personName;
	@NonNull
	private String personSurname;
}
