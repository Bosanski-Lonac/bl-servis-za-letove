package com.bosanskilonac.szl.runner;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.bosanskilonac.szl.model.Avion;
import com.bosanskilonac.szl.model.Let;
import com.bosanskilonac.szl.repository.AvionRepository;
import com.bosanskilonac.szl.repository.LetRepository;

import security.TokenService;

@Profile({"default"})
@Component
public class Runner implements CommandLineRunner {
	@Value("${oauth.jwt.secret}")
	private String jwtSecret;
	
	private AvionRepository avionRepository;
	private LetRepository letRepository;
	private TokenService tokenService;

	public Runner(TokenService tokenService, AvionRepository avionRepository, LetRepository letRepository) {
		this.tokenService = tokenService;
		this.avionRepository = avionRepository;
		this.letRepository = letRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		tokenService.setSecret(jwtSecret);
		
		Avion avion1 = addPlane("Airbus A320", 320);
		avionRepository.save(avion1);
		
		Avion avion2 = addPlane("Airbus A300", 300);
		avionRepository.save(avion2);

		Avion avion3 = addPlane("Boeing 747", 300);
		avionRepository.save(avion3);

		Avion avion4 = addPlane("Boeing 777", 550);
		avionRepository.save(avion4);
		
		Let let1 = addFlight("Beograd", "Atina", 30, 60, 250, avion2);
		letRepository.save(let1);
		
		Let let2 = addFlight("Beograd", "London", 120, 250, 1250, avion1);
		letRepository.save(let2);

		Let let3 = addFlight("Beograd", "Solun", 100, 250, 1200, avion4);
		letRepository.save(let3);

		Let let4 = addFlight("Beograd", "Cirih", 60, 200, 1000, avion4);
		letRepository.save(let4);

		Let let5 = addFlight("Cirih", "Dubai", 150, 450, 1300, avion3);
		letRepository.save(let5);

		Let let6 = addFlight("Dubai", "Cirih", 150, 450, 1300, avion3);
		letRepository.save(let6);

		Let let7 = addFlight("Njujork", "Beograd", 180, 600, 2400, avion4);
		letRepository.save(let7);

		Let let8 = addFlight("London", "Njujork", 100, 300, 1400, avion1);
		letRepository.save(let8);

		Let let9 = addFlight("Moskva", "Beograd", 160, 300, 1300, avion2);
		letRepository.save(let9);

		Let let10 = addFlight("Moskva", "Beograd", 160, 300, 1301, avion2);
		letRepository.save(let10);

		Let let11 = addFlight("Moskva", "Beograd", 160, 300, 1302, avion2);
		letRepository.save(let11);

		Let let12 = addFlight("Moskva", "Beograd", 160, 300, 1303, avion2);
		letRepository.save(let12);

		Let let13 = addFlight("Moskva", "Beograd", 160, 300, 1304, avion2);
		letRepository.save(let13);

		Let let14 = addFlight("Moskva", "Beograd", 160, 300, 1305, avion2);
		letRepository.save(let14);

		Let let15 = addFlight("Moskva", "Beograd", 160, 300, 1306, avion2);
		letRepository.save(let15);

		Let let16 = addFlight("Moskva", "Beograd", 160, 300, 1307, avion2);
		letRepository.save(let16);

		Let let17 = addFlight("Moskva", "Beograd", 160, 300, 1308, avion2);
		letRepository.save(let17);

		Let let18 = addFlight("Moskva", "Beograd", 160, 300, 1309, avion2);
		letRepository.save(let18);

		Let let19 = addFlight("Moskva", "Beograd", 160, 300, 1310, avion2);
		letRepository.save(let19);

		Let let20 = addFlight("Moskva", "Beograd", 160, 300, 1320, avion2);
		letRepository.save(let20);

		Let let21 = addFlight("Moskva", "Beograd", 160, 300, 1330, avion2);
		letRepository.save(let21);

		Let let22 = addFlight("Moskva", "Beograd", 160, 300, 1340, avion2);
		letRepository.save(let22);

		Let let23 = addFlight("Moskva", "Beograd", 160, 300, 1350, avion2);
		letRepository.save(let23);

		Let let24 = addFlight("Moskva", "Beograd", 160, 300, 1360, avion2);
		letRepository.save(let24);

		Let let25 = addFlight("Moskva", "Beograd", 160, 300, 1370, avion2);
		letRepository.save(let25);
	}

	public Let addFlight(String pocetna, String krajnja, int duzina, int cena, int milje, Avion avion) {
		Let let = new Let();
		let.setPocetnaDestinacija(pocetna);
		let.setKrajnjaDestinacija(krajnja);
		let.setDuzina(duzina);
		let.setCena(BigDecimal.valueOf(cena));
		let.setMilje(milje);
		let.setAvion(avion);
		return let;
	}

	public Avion addPlane(String naziv, int kapacitet) {
		Avion avion = new Avion();
		avion.setNaziv(naziv);
		avion.setKapacitetPutnika(kapacitet);
		return avion;
	}
}
