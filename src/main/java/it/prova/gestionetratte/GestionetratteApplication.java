package it.prova.gestionetratte;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.model.Stato;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.service.AirbusService;
import it.prova.gestionetratte.service.TrattaService;

@SpringBootApplication
public class GestionetratteApplication implements CommandLineRunner {

	@Autowired
	private TrattaService trattaService;
	@Autowired
	private AirbusService airbusService;

	public static void main(String[] args) {
		SpringApplication.run(GestionetratteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		// Creazione Airbus Emirates
		// Airbus (id, codice, descrizione, dataInizioServizio, numeroPasseggeri,
		// tratte)
		String codiceEmirates = "AB2345E";
		String descrizioneEmirates = "Emirates";
		LocalDate dataInizioServizioEmirates = LocalDate.parse("18/12/1946", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		Integer numeroPasseggeriEmirates = 800;

		Airbus airbusEmirates = airbusService.findByCodiceAndDescrizione(codiceEmirates, descrizioneEmirates);

		if (airbusEmirates == null) {
			airbusEmirates = new Airbus(codiceEmirates, descrizioneEmirates, dataInizioServizioEmirates,
					numeroPasseggeriEmirates);
			airbusService.inserisciNuovo(airbusEmirates);
		}

		// Creazione Tratta
		Tratta trattaMilanoNapoli = new Tratta("HJKGJ678", "Milano-Napoli",
				LocalDate.parse("18/12/1946", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
				LocalTime.parse("10:30:35"), LocalTime.parse("22:30:35"), Stato.ATTIVA,
				airbusEmirates);
		if (trattaService
				.findByCodiceAndDescrizione(trattaMilanoNapoli.getCodice(), trattaMilanoNapoli.getDescrizione())
				.isEmpty())
			trattaService.inserisciNuovo(trattaMilanoNapoli);

		// Creazione Airbus Emirates
		// Airbus (id, codice, descrizione, dataInizioServizio, numeroPasseggeri,
		// tratte)
		String codiceIta = "AB5872I";
		String descrizioneIta = "ItaAirways";
		LocalDate dataInizioServizioIta = LocalDate.parse("20/12/2022", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		Integer numeroPasseggeriIta = 600;

		Airbus airbusIta = airbusService.findByCodiceAndDescrizione(codiceIta, descrizioneIta);

		if (airbusIta == null) {
			airbusIta = new Airbus(codiceIta, descrizioneIta, dataInizioServizioIta, numeroPasseggeriIta);
			airbusService.inserisciNuovo(airbusIta);
		}

		// Creazione Tratta
		Tratta trattaRomaLondra = new Tratta("KKOOP67", "Roma-Londra",
				LocalDate.parse("20/12/2022", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
				LocalTime.parse("9:30:35"), LocalTime.parse("13:30:35"), Stato.ATTIVA,
				airbusIta);
		if (trattaService
				.findByCodiceAndDescrizione(trattaRomaLondra.getCodice(), trattaRomaLondra.getDescrizione())
				.isEmpty())
			trattaService.inserisciNuovo(trattaRomaLondra);

	}

}
