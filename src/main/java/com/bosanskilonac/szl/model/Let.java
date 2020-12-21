package com.bosanskilonac.szl.model;

import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Let {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Column(name="pocetna_destinacija")
	private String pocetnaDestinacija;
	@NotBlank
	@Column(name="krajnja_destinacija")
	private String krajnjaDestinacija;
	@Positive
	private Integer duzina; // u minutima
	@Positive
	private BigDecimal cena;
	@Positive
	private Integer milje;
	@ManyToOne
	private Avion avion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getDuzina() {
		return duzina;
	}

	public void setDuzina(Integer duzina) {
		this.duzina = duzina;
	}

	public BigDecimal getCena() {
		return cena;
	}

	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}
	
	public Integer getMilje() {
		return milje;
	}

	public void setMilje(Integer milje) {
		this.milje = milje;
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}
}
