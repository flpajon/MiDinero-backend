package ar.com.midinero.MIDinero.repositories.daos;

public interface MovementAccountState {
	Integer getYear_period_movement();
	Integer getMonth_period_movement();
	Integer getDay_period_movement();
	Long getTotal_positive_momement();
	Long getTotal_negative_momement();
}
