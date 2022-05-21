package ar.com.midinero.MIDinero.services.movement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.midinero.MIDinero.controllers.dto.commons.StateDTO;
import ar.com.midinero.MIDinero.controllers.dto.movement.MovementAccountDTO;
import ar.com.midinero.MIDinero.controllers.dto.movement.MovementAccountResponseDTO;
import ar.com.midinero.MIDinero.controllers.dto.movement.MovementDTO;
import ar.com.midinero.MIDinero.controllers.dto.movement.MovementListResponseDTO;
import ar.com.midinero.MIDinero.controllers.dto.movement.MovementsAccountStatusResponseDTO;
import ar.com.midinero.MIDinero.controllers.dto.movement.NewMovementRequestDTO;
import ar.com.midinero.MIDinero.controllers.dto.movement.NewMovementResponseDTO;
import ar.com.midinero.MIDinero.controllers.dto.movementtype.MovementTypeDTO;
import ar.com.midinero.MIDinero.models.Movement;
import ar.com.midinero.MIDinero.models.MovementType;
import ar.com.midinero.MIDinero.models.User;
import ar.com.midinero.MIDinero.repositories.MovementRepository;
import ar.com.midinero.MIDinero.repositories.daos.MovementCurrentAccountState;
import ar.com.midinero.MIDinero.repositories.daos.MovementAccountState;
import ar.com.midinero.MIDinero.services.user.UserService;

@Service
public class MovementServiceImpl implements MovementService {

	@Autowired
	MovementRepository movementRepository;

	@Autowired
	UserService userService;

	@Override
	public MovementListResponseDTO getMovements(Long userId) {
		List<Movement> movementList = movementRepository.findAllByMovementUserUserIdOrderByMovementDateDesc(userId);
		if (movementList.size() > 0) {
			return new MovementListResponseDTO(new StateDTO(0, "Full movement list"),
					movementList.stream().map(movement -> {
						return new MovementDTO(movement.getMovementDate(), movement.getMovementDescription(),
								new MovementTypeDTO(movement.getMovementType().getMovementTypeId(),
										movement.getMovementType().getMovementTypeName(),
										movement.getMovementType().getMovementTypeIsPositive()),
								movement.getMovementAmount());
					}).collect(Collectors.toList()));
		}
		return new MovementListResponseDTO(new StateDTO(1, "Empty movement list"), new ArrayList<MovementDTO>());
	}

	@Override
	public MovementListResponseDTO getCurrentMovements(Long userId) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date fromDate = calendar.getTime();
		Integer daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DATE, daysOfMonth -1);
		Date toDate = calendar.getTime();
		List<Movement> movementList = movementRepository.findAllByMovementUserUserIdBetweenOrderByMovementDateDesc(
				userId, fromDate, toDate);
		if (movementList.size() > 0) {
			return new MovementListResponseDTO(new StateDTO(0, "Full movement list"),
					movementList.stream().map(movement -> {
						return new MovementDTO(movement.getMovementDate(), movement.getMovementDescription(),
								new MovementTypeDTO(movement.getMovementType().getMovementTypeId(),
										movement.getMovementType().getMovementTypeName(),
										movement.getMovementType().getMovementTypeIsPositive()),
								movement.getMovementAmount());
					}).collect(Collectors.toList()));
		}
		return new MovementListResponseDTO(new StateDTO(1, "Empty movement list"), new ArrayList<MovementDTO>());
	}

	@Override
	@Transactional
	public NewMovementResponseDTO newMovement(NewMovementRequestDTO newMovement) {
		User user = userService.getUserFromUserId(newMovement.getUserId());
		MovementType movementType = new MovementType(newMovement.getMovementType().getMovementTypeId(),
				newMovement.getMovementType().getMovementTypeName(),
				newMovement.getMovementType().getMovementIsPositive());
		Calendar calendar = Calendar.getInstance();
		Date movementDate = calendar.getTime();
		Movement movementTemp = new Movement(movementDate,
				newMovement.getMovementDescription(), movementType,
				newMovement.getMovementAmount(), user);
		movementRepository.save(movementTemp);
		return new NewMovementResponseDTO(new StateDTO(0, "Movement created correctly"));
	}

	@Override
	public MovementAccountResponseDTO getMovementCount(Long userId, String filter) {
		List<MovementAccountState> movementCountList = null;
		switch (filter) {
		case "YEAR":
			movementCountList = movementRepository.countPositiveAndNegativeMovementsFromUserByYEAR(userId);
			break;

		case "MONTH":
			movementCountList = movementRepository.countPositiveAndNegativeMovementsFromUserByMONTH(userId);
			break;

		case "DAY":
			movementCountList = movementRepository.countPositiveAndNegativeMovementsFromUserByDAY(userId);
			break;

		default:
			movementCountList = new ArrayList<MovementAccountState>();
			break;
		}
		if (movementCountList.size() > 0) {
			return new MovementAccountResponseDTO(new StateDTO(0, "Movements were counted correctly"),
					movementCountList.stream().map(movementCount -> {
						return new MovementAccountDTO(String.valueOf(movementCount.getYear_period_movement()),
								String.valueOf(movementCount.getMonth_period_movement()),
								String.valueOf(movementCount.getDay_period_movement()),
								movementCount.getTotal_positive_momement(), movementCount.getTotal_negative_momement());
					}).collect(Collectors.toList()));
		}
		return new MovementAccountResponseDTO(
				new StateDTO(1, "There aren't movements for that user or the filter is incorrect."),
				new ArrayList<MovementAccountDTO>());

	}

	@Override
	public MovementsAccountStatusResponseDTO getCurrentAccountStatement(Long userId) {
		Calendar calendar = Calendar.getInstance();
		String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd").format(calendar.getTime());
		String[] arrayOfDate = timeStamp.split("/");
		List<MovementCurrentAccountState> accountStateList = movementRepository.getAccountStatusFromUserId(userId,
				Double.valueOf(arrayOfDate[0]), Double.valueOf(arrayOfDate[1]));
		if (accountStateList.size() > 0) {
			Double accountState = accountStateList.stream()
					.mapToDouble(account -> account.getMovement_type_is_positive() ? account.getMovement_amount()
							: (account.getMovement_amount() * -1))
					.sum();
			return new MovementsAccountStatusResponseDTO(new StateDTO(0, "Account movement fetched correctly"),
					String.format("%.2f", accountState), month);
		}
		return new MovementsAccountStatusResponseDTO(new StateDTO(1, "There aren't movements for that user."), "0", "");
	}

}
