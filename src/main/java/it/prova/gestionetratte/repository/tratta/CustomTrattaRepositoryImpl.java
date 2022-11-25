package it.prova.gestionetratte.repository.tratta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.gestionetratte.model.Tratta;

public class CustomTrattaRepositoryImpl implements CustomTrattaRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Tratta> findByExample(Tratta example){
		Map<String,Object> parameterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();
		
		StringBuilder queryBuilder = new StringBuilder("select t from Tratta t where t.id = t.id ");

		if (StringUtils.isNotEmpty(example.getCodice())) {
			whereClauses.add(" t.codice  like :codice ");
			parameterMap.put("codice", "%" + example.getCodice() + "%");
		}
		if (StringUtils.isNotEmpty(example.getDescrizione())) {
			whereClauses.add(" t.descrizione like :descrizione ");
			parameterMap.put("descrizione", "%" + example.getDescrizione() + "%");
		}
		
		if (example.getData() != null) {
			whereClauses.add("t.data >= :data ");
			parameterMap.put("data", example.getData());
		}
		
		if (example.getOraDecollo() != null) {
			whereClauses.add("t.oradecollo = :oraDecollo ");
			parameterMap.put("oraDecollo", example.getOraDecollo());
		}
		
		if (example.getOraAtterraggio() != null) {
			whereClauses.add("t.oraatterraggio = :oraAtterraggio ");
			parameterMap.put("oraAtterraggio", example.getOraAtterraggio());
		}
		
		if (example.getStato() != null) {
			whereClauses.add("t.stato = :stato ");
			parameterMap.put("stato", example.getStato());
		}
		
		if (example.getAirbus().getId() != null) {
			whereClauses.add("t.airbus.id = :airbusId ");
			parameterMap.put("airbusId", example.getAirbus().getId());
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Tratta> typedQuery = entityManager.createQuery(queryBuilder.toString(), Tratta.class);

		for (String key : parameterMap.keySet()) {
			typedQuery.setParameter(key, parameterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}
