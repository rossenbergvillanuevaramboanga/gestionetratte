package it.prova.gestionetratte.repository.airbus;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionetratte.model.Airbus;

public interface AirbusRepository extends CrudRepository<Airbus, Long>, CustomAirbusRepository {
	
	@Query("from Airbus a left join fetch a.tratte where a.id=?1")
	Airbus findByIdEager(Long id);

	Airbus findByCodiceAndDescrizione(String codice, String descrizione);

}
