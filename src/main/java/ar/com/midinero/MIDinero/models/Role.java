package ar.com.midinero.MIDinero.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	@ManyToMany
	@JoinTable(
	  name = "role_menu", 
	  joinColumns = @JoinColumn(name = "role_id"), 
	  inverseJoinColumns = @JoinColumn(name = "menu_id"))
	List<Menu> roleMenuList;
}
