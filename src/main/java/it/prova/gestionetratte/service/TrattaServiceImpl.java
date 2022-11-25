package it.prova.gestionetratte.service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import it.prova.gestionetratte.model.Stato;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.repository.tratta.TrattaRepository;
import it.prova.gestionetratte.web.api.exception.TrattaNotFoundException;

public class TrattaServiceImpl implements TrattaService {
	
	@Autowired
	private TrattaRepository repository;

	@Override
	public List<Tratta> listAllElements(boolean eager) {
		// TODO Auto-generated method stub
		
		if(eager)
			return (List<Tratta>) repository.findAllTrattaEager();
		return (List<Tratta>) repository.findAll();
	}

	@Override
	public Tratta caricaSingoloElemento(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}

	@Override
	public Tratta caricaSingoloElementoEager(Long id) {
		// TODO Auto-generated method stub
		return repository.findSingleTrattaEager(id);
	}

	@Override
	public Tratta aggiorna(Tratta trattaInstance) {
		// TODO Auto-generated method stub
		return repository.save(trattaInstance);
	}

	@Override
	public Tratta inserisciNuovo(Tratta trattaInstance) {
		// TODO Auto-generated method stub
		return repository.save(trattaInstance);
	}

	@Override
	public void rimuovi(Long idToRemove) {
		// TODO Auto-generated method stub
		repository.findById(idToRemove).orElseThrow(
				() -> new TrattaNotFoundException("Tratta not found con id: " + idToRemove));
		repository.deleteById(idToRemove);
		
	}

	@Override
	public List<Tratta> findByExample(Tratta example) {
		// TODO Auto-generated method stub
		return repository.findByExample(example);
	}

	@Override 
	public List<Tratta> findByCodiceAndDescrizione(String codice, String descrizione) {
		// TODO Auto-generated method stub
		return repository.findByCodiceAndDescrizione(codice,descrizione);
	}

	@Override
	public void concludiTratte() {
		// TODO Auto-generated method stub
		List<Tratta> tratteDaConcludere = repository.findByStatoAndOraAtterraggioBefore(Stato.ATTIVA , LocalTime.now());
		tratteDaConcludere.stream().forEach(e -> { e.setStato(Stato.CONCLUSA); repository.save(e);});
		
	}

}
