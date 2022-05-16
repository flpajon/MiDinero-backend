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
@Table(name = "movement_types")
@RequiredArgsConstructor
@NoArgsConstructor
public class MovementType {
	@Id
	@NonNull
	@Column(name = "movement_type_id")
	private Long movementTypeId;
	@NonNull
	@Column(name = "movement_type_name")
	private String movementTypeName;
	@NonNull
	@Column(name = "movement_type_is_positive")
	private Boolean movementTypeIsPositive;
}
