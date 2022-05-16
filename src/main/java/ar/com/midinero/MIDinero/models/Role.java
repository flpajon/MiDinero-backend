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
@Table(name = "roles")
@RequiredArgsConstructor
@NoArgsConstructor
public class Role {
	@Id
	@NonNull
	@Column(name = "role_id")
	private Long roleId;
	@NonNull
	@Column(name = "role_name")
	private String roleName;
}
