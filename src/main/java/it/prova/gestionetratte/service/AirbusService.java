package it.prova.gestionetratte.service;

import java.util.List;

import it.prova.gestionetratte.model.Airbus;

public interface AirbusService {
	
	List<Airbus> listAllElements(boolean eager);
	
	Airbus caricaSingoloElemento(Long id);
	
	Airbus caricaSingoloElementoEager(Long id);
	
	Airbus aggiorna(Airbus airbusInstance);
	
	Airbus inserisciNuovo(Airbus airbusInstance);
	
	void rimuovi(Long idToRemove);
	
	List<Airbus> findByExample(Airbus example);

	Airbus findByCodiceAndDescrizione(String codiceEmirates, String descrizioneEmirates);

}
