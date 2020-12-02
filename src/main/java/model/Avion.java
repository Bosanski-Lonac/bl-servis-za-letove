package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

public class Avion {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String naziv;
	@Column(name="kapacitet_putnika")
	private Integer kapacitetPutnika;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "avion", orphanRemoval = true)
	private List<Let> letovi;
	
	public Avion(String naziv, Integer kapacitetPutnika) {
		this.naziv = naziv;
		this.kapacitetPutnika = kapacitetPutnika;
		letovi=new ArrayList<>();
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
