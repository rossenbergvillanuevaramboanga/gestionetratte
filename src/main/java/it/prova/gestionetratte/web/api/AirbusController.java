package it.prova.gestionetratte.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestionetratte.dto.AirbusDTO;
import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.service.AirbusService;
import it.prova.gestionetratte.web.api.exception.AirbusNotFoundException;

@RestController
@RequestMapping("api/airbus")
public class AirbusController {
	
	@Autowired
	private AirbusService airbusService;
	
	@GetMapping
	public List<AirbusDTO> getAll(){
		return AirbusDTO.createAirbusDTOListFromModelList(airbusService.listAllElements(true), true);	
	}
	
	@GetMapping("/{id}")
	public AirbusDTO findById(@PathVariable(value = "id", required = true) long id) {
		Airbus airbus = airbusService.caricaSingoloElementoEager(id);

		if (airbus == null)
			throw new AirbusNotFoundException("Airbus not found con id: " + id);
		return AirbusDTO.buildAirbusDTOFromModel(airbus, true);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AirbusDTO createNew() {
		return null; 
	}
	
	@PutMapping("/{id}")
	public AirbusDTO update() {
		return null; 
	}
	
	@DeleteMapping("/{id}")
	public void delete() {
		
	}
	
	@PostMapping("/search")
	public List<AirbusDTO> search(){
		return null; 
	}
	
	
	
}
