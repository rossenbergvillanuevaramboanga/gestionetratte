package it.prova.gestionetratte.repository.airbus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.gestionetratte.model.Airbus;

public class CustomAirbusRepositoryImpl implements CustomAirbusRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Airbus> findByExample(Airbus example){
		
		Map<String,Object> parameterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();
		
		StringBuilder queryBuilder = new StringBuilder("select a from Airbus a where a.id = a.id ");

		if (StringUtils.isNotEmpty(example.getCodice())) {
			whereClauses.add(" a.codice  like :codice ");
			parameterMap.put("codice", "%" + example.getCodice() + "%");
		}
		if (StringUtils.isNotEmpty(example.getDescrizione())) {
			whereClauses.add(" a.descrizione like :descrizione ");
			parameterMap.put("descrizione", "%" + example.getDescrizione() + "%");
		}
		
		if (example.getDataInizioServizio() != null) {
			whereClauses.add("a.datainizioservizio >= :dataInizioServizio ");
			parameterMap.put("dataInizioServizio", example.getDataInizioServizio());
		}
		
		if (example.getNumeroPasseggeri() != null) {
			whereClauses.add("a.numeropasseggeri = :numeroPasseggeri ");
			parameterMap.put("numeroPasseggeri", example.getNumeroPasseggeri());
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Airbus> typedQuery = entityManager.createQuery(queryBuilder.toString(), Airbus.class);

		for (String key : parameterMap.keySet()) {
			typedQuery.setParameter(key, parameterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}
