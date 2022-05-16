package ar.com.midinero.MIDinero.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "persons")
@RequiredArgsConstructor
@NoArgsConstructor
public class Person {
	@Id
	@NonNull
	@Column(name = "person_email")
	private String personEmail;
	@NonNull
	@Column(name = "person_name")
	private String personName;
	@NonNull
	@Column(name = "person_surname")
	private String personSurname;
}
