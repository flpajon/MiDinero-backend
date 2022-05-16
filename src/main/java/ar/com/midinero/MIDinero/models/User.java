package ar.com.midinero.MIDinero.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "users")
@RequiredArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@SequenceGenerator(name = "seq_user", sequenceName = "seq_user")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
	@Column(name = "user_id")
	private Long userId;
	@NonNull
	@Column(name = "user_name")
	private String userName;
	@NonNull
	@Column(name = "user_password")
	private String userPassword;
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@NonNull
	private Role userRole;
	@NonNull
	@Column(name = "user_is_active")
	private Boolean userIsActive;
	@OneToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@NonNull
	private Person userPerson;
}
