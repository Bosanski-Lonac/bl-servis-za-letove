package com.bosanskilonac.szl.model;

import java.time.Duration;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Let {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private Avion avion;
	@Column(name="pocetna_destinacija")
	private String pocetnaDestinacija;
	@Column(name="krajnja_destinacija")
	private String krajnjaDestinacija;
	@Column(name="duzina_leta")
	private Duration duzinaLeta;
	private Integer cena;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	public String getPocetnaDestinacija() {
		return pocetnaDestinacija;
	}

	public void setPocetnaDestinacija(String pocetnaDestinacija) {
		this.pocetnaDestinacija = pocetnaDestinacija;
	}

	public String getKrajnjaDestinacija() {
		return krajnjaDestinacija;
	}

	public void setKrajnjaDestinacija(String krajnjaDestinacija) {
		this.krajnjaDestinacija = krajnjaDestinacija;
	}

	public Duration getDuzinaLeta() {
		return duzinaLeta;
	}

	public void setDuzinaLeta(Duration duzinaLeta) {
		this.duzinaLeta = duzinaLeta;
	}

	public Integer getCena() {
		return cena;
	}

	public void setCena(Integer cena) {
		this.cena = cena;
	}
}
