package ar.com.midinero.MIDinero.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "movements")
@RequiredArgsConstructor
@NoArgsConstructor
public class Movement {
	@Id
	@SequenceGenerator(name = "seq_movement", sequenceName = "seq_movement")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_movement")
	@Column(name = "movement_id")
	private Long movementId;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Argentina/Cordoba")
	@NonNull
	@Column(name = "movement_date")
	private Date movementDate;
	@Column(name = "movement_description")
	@NonNull
	private String movementDescription;
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@NonNull
	private MovementType movementType;
	@NonNull
	@Column(name = "movement_amount")
	private Double movementAmount;
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@NonNull
	private User movementUser;
	
}
