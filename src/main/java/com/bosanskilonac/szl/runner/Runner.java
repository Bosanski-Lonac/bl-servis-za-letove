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
		
		Avion avion1 = new Avion();
		avion1.setNaziv("Airbus A320");
		avion1.setKapacitetPutnika(320);
		avionRepository.save(avion1);
		
		Avion avion2 = new Avion();
		avion2.setNaziv("Airbus A300");
		avion2.setKapacitetPutnika(300);
		avionRepository.save(avion2);
		
		Let let1 = new Let();
		let1.setPocetnaDestinacija("Beograd");
		let1.setKrajnjaDestinacija("Atina");
		let1.setDuzina(30);
		let1.setCena(BigDecimal.valueOf(60));
		let1.setMilje(250);
		let1.setAvion(avion2);
		letRepository.save(let1);
		
		Let let2 = new Let();
		let2.setPocetnaDestinacija("Beograd");
		let2.setKrajnjaDestinacija("London");
		let2.setDuzina(120);
		let2.setCena(BigDecimal.valueOf(250));
		let2.setMilje(1250);
		let2.setAvion(avion1);
		letRepository.save(let2);
	}
}
