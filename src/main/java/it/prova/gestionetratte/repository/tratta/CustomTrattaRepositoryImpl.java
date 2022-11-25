package it.prova.gestionetratte.repository.tratta;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.prova.gestionetratte.model.Tratta;

public class CustomTrattaRepositoryImpl implements CustomTrattaRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Tratta> findByExample(Tratta example){
		return null;
	}

}
