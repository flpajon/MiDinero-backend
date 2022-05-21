package ar.com.midinero.MIDinero.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.midinero.MIDinero.models.Movement;
import ar.com.midinero.MIDinero.repositories.daos.MovementCurrentAccountState;
import ar.com.midinero.MIDinero.repositories.daos.MovementAccountState;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

	List<Movement> findAllByMovementUserUserIdOrderByMovementDateDesc(Long userId);

	@Query(value = "SELECT * "
			+ "FROM movements "
			+ "WHERE movements.movement_user_user_id = ?1 AND movements.movement_date BETWEEN ?2 AND ?3 "
			+ "ORDER BY movements.movement_date DESC", nativeQuery = true)
	List<Movement> findAllByMovementUserUserIdBetweenOrderByMovementDateDesc(Long userId, Date fromDate,
			Date toDate);

	@Query(value = "SELECT count_movement.filter_movement as year_period_movement, sum(count_movement.is_positive) as total_positive_momement, sum(count_movement.is_negative) as total_negative_momement "
			+ "	FROM ("
			+ "		SELECT EXTRACT(YEAR FROM movement_date) as filter_movement, sum(movement_amount) as is_positive, 0 as is_negative "
			+ "			FROM movements "
			+ "			INNER JOIN movement_types ON movement_types.movement_type_id = movements.movement_type_movement_type_id "
			+ "			WHERE movement_user_user_id = ?1 AND movement_types.movement_type_is_positive "
			+ "			GROUP BY filter_movement " + "		UNION "
			+ "		SELECT EXTRACT(YEAR FROM movement_date) as filter_movement,0 as is_positive, sum(movement_amount) as is_negative "
			+ "			FROM movements "
			+ "			INNER JOIN movement_types ON movement_types.movement_type_id = movements.movement_type_movement_type_id "
			+ "			WHERE movement_user_user_id = ?1 AND NOT movement_types.movement_type_is_positive "
			+ "			GROUP BY filter_movement" + ")as count_movement "
			+ "GROUP BY count_movement.filter_movement", nativeQuery = true)
	List<MovementAccountState> countPositiveAndNegativeMovementsFromUserByYEAR(Long userId);

	@Query(value = "SELECT count_movement.year_movement as year_period_movement,count_movement.month_movement as month_period_movement, sum(count_movement.is_positive) as total_positive_momement, sum(count_movement.is_negative) as total_negative_momement "
			+ "	FROM ("
			+ "		SELECT EXTRACT(YEAR FROM movement_date) as year_movement, EXTRACT(MONTH FROM movement_date) as month_movement , sum(movement_amount) as is_positive, 0 as is_negative "
			+ "			FROM movements "
			+ "			INNER JOIN movement_types ON movement_types.movement_type_id = movements.movement_type_movement_type_id "
			+ "			WHERE movement_user_user_id = ?1 AND movement_types.movement_type_is_positive "
			+ "			GROUP BY year_movement, month_movement " + "		UNION "
			+ "		SELECT EXTRACT(YEAR FROM movement_date) as year_movement, EXTRACT(MONTH FROM movement_date) as month_movement,0 as is_positive, sum(movement_amount) as is_negative "
			+ "			FROM movements "
			+ "			INNER JOIN movement_types ON movement_types.movement_type_id = movements.movement_type_movement_type_id "
			+ "			WHERE movement_user_user_id = ?1 AND NOT movement_types.movement_type_is_positive "
			+ "			GROUP BY year_movement, month_movement" + ")as count_movement "
			+ "GROUP BY count_movement.year_movement, count_movement.month_movement", nativeQuery = true)
	List<MovementAccountState> countPositiveAndNegativeMovementsFromUserByMONTH(Long userId);

	@Query(value = "SELECT count_movement.year_movement as year_period_movement ,count_movement.month_movement as month_period_movement, count_movement.day_movement as day_period_movement, sum(count_movement.is_positive) as total_positive_momement, sum(count_movement.is_negative) as total_negative_momement "
			+ "	FROM ("
			+ "		SELECT EXTRACT(YEAR FROM movement_date) as year_movement, EXTRACT(MONTH FROM movement_date) as month_movement, EXTRACT(DAY FROM movement_date) as day_movement , sum(movement_amount) as is_positive, 0 as is_negative "
			+ "			FROM movements "
			+ "			INNER JOIN movement_types ON movement_types.movement_type_id = movements.movement_type_movement_type_id "
			+ "			WHERE movement_user_user_id = ?1 AND movement_types.movement_type_is_positive "
			+ "			GROUP BY year_movement, month_movement, day_movement " + "		UNION "
			+ "		SELECT EXTRACT(YEAR FROM movement_date) as year_movement, EXTRACT(MONTH FROM movement_date) as month_movement, EXTRACT(DAY FROM movement_date) as day_movement ,0 as is_positive, sum(movement_amount) as is_negative "
			+ "			FROM movements "
			+ "			INNER JOIN movement_types ON movement_types.movement_type_id = movements.movement_type_movement_type_id "
			+ "			WHERE movement_user_user_id = ?1 AND NOT movement_types.movement_type_is_positive "
			+ "			GROUP BY year_movement, month_movement, day_movement" + ")as count_movement "
			+ "GROUP BY count_movement.year_movement, count_movement.month_movement, count_movement.day_movement", nativeQuery = true)
	List<MovementAccountState> countPositiveAndNegativeMovementsFromUserByDAY(Long userId);

	@Query(value = "SELECT movement_amount,  movement_types.movement_type_is_positive " + "FROM movements "
			+ "INNER JOIN movement_types ON movement_types.movement_type_id = movement_type_movement_type_id "
			+ "WHERE movement_user_user_id = ?1 AND EXTRACT(YEAR FROM movement_date) = ?2 AND EXTRACT(MONTH FROM movement_date) = ?3 ;", nativeQuery = true)
	List<MovementCurrentAccountState> getAccountStatusFromUserId(Long userId, Double year, Double month);

}
