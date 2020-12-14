package com.bosanskilonac.szl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan({"com.bosanskilonac.szl", "security", "exceptions"})
public class ServisZaLetoveRun {

	public static void main(String[] args) {
		SpringApplication.run(ServisZaLetoveRun.class, args);
	}
}
