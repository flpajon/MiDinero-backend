package ar.com.midinero.MIDinero.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.midinero.MIDinero.models.MovementType;

@Repository
public interface MovementTypeRepository extends JpaRepository<MovementType, Long>{

}
