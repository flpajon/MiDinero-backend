package ar.com.midinero.MIDinero.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "menu_list")
@RequiredArgsConstructor
@NoArgsConstructor
public class Menu {
	@Id
	@NonNull
	@Column(name = "menu_id")
	private Long menuId;
	@NonNull
	@Column(name = "menu_name")
	private String menuName;
	@NonNull
	@Column(name = "menu_endpoint")
	private String menuEndpoint;
	@ManyToMany(mappedBy = "roleMenuList")
	List<Role> menuRoleList;
}
