package ar.com.midinero.MIDinero.services.movementtype;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.midinero.MIDinero.controllers.dto.commons.StateDTO;
import ar.com.midinero.MIDinero.controllers.dto.movementtype.MovementTypeDTO;
import ar.com.midinero.MIDinero.controllers.dto.movementtype.MovementTypeListResponseDTO;
import ar.com.midinero.MIDinero.models.MovementType;
import ar.com.midinero.MIDinero.repositories.MovementTypeRepository;

@Service
public class MovementTypeServiceImpl implements MovementTypeService {

	@Autowired
	MovementTypeRepository movementTypeRepository;

	@Override
	public MovementTypeListResponseDTO getMovementTypes() {
		List<MovementType> movementTypeList = movementTypeRepository.findAll();
		if (movementTypeList.size() > 0) {
			return new MovementTypeListResponseDTO(new StateDTO(0, "Full movementType list"),
					movementTypeList.stream().map(movementType -> {
						return new MovementTypeDTO(movementType.getMovementTypeId(),
								movementType.getMovementTypeName(), movementType.getMovementTypeIsPositive());
					}).collect(Collectors.toList()));
		}
		return new MovementTypeListResponseDTO(new StateDTO(1, "Empty movementType list"),
				new ArrayList<MovementTypeDTO>());
	}

}
