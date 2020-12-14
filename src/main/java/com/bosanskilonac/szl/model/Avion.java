package com.bosanskilonac.szl.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Avion {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String naziv;
	@Positive
	@Column(name="kapacitet_putnika")
	private Integer kapacitetPutnika;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "avion", orphanRemoval = true)
	private List<Let> letovi;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Integer getKapacitetPutnika() {
		return kapacitetPutnika;
	}

	public void setKapacitetPutnika(Integer kapacitetPutnika) {
		this.kapacitetPutnika = kapacitetPutnika;
	}

	public List<Let> getLetovi() {
		return letovi;
	}

	public void setLetovi(List<Let> letovi) {
		this.letovi = letovi;
	}
}
