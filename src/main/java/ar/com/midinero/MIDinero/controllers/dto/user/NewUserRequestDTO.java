package ar.com.midinero.MIDinero.controllers.dto.user;

import lombok.Data;

@Data
public class NewUserRequestDTO {
	private String userName;
	private String userPassword;
	private String personEmail;
	private String personName;
	private String personSurname;
}