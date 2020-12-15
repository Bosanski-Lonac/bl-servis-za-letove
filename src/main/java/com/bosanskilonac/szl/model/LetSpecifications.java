package com.bosanskilonac.szl.model;

import org.springframework.data.jpa.domain.Specification;

public class LetSpecifications {
	public static Specification<Let> getLetByPocetnaDestinacijaSpec(String pocetnaDestinacija) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.like(root.get("pocetnaDestinacija"), "%" + pocetnaDestinacija + "%");
		};
	}
	
	public static Specification<Let> getLetByKrajnjaDestinacijaSpec(String krajnjaDestinacija) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.like(root.get("krajnjaDestinacija"), "%" + krajnjaDestinacija + "%");
		};
	}
	
	public static Specification<Let> getLetByDuzinaSpec(Integer min, Integer max) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.between(root.get("duzina"), min, max);
		};
	}
	
	public static Specification<Let> getLetByCenaSpec(Integer min, Integer max) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.between(root.get("cena"), min, max);
		};
	}
	
	public static Specification<Let> getLetByCriteriaSpec(String pocetnaDestinacija, String krajnjaDestinacija, String minDuzinaStr,
			String maxDuzinaStr, String minCenaStr, String maxCenaStr) {
		Specification<Let> specifications = null;
		if(!pocetnaDestinacija.isBlank()) {
			specifications = getLetByPocetnaDestinacijaSpec(pocetnaDestinacija);
		}
		if(!krajnjaDestinacija.isBlank()) {
			Specification<Let> specification = getLetByPocetnaDestinacijaSpec(krajnjaDestinacija);
			if(specifications != null) {
				specifications.and(specification);
			} else {
				specifications = specification;
			}
		}
		if(!minDuzinaStr.isBlank() && !maxDuzinaStr.isBlank()) {
			try {
				Integer minDuzina = Integer.parseInt(minDuzinaStr);
				Integer maxDuzina = Integer.parseInt(maxDuzinaStr);
				Specification<Let> specification = getLetByDuzinaSpec(minDuzina, maxDuzina);
				if(specifications != null) {
					specifications.and(specification);
				} else {
					specifications = specification;
				}
			} catch (NumberFormatException e) {
				
			}
		}
		if(!minCenaStr.isBlank() && !minCenaStr.isBlank()) {
			try {
				Integer minCena = Integer.parseInt(minCenaStr);
				Integer maxCena = Integer.parseInt(maxCenaStr);
				Specification<Let> specification = getLetByCenaSpec(minCena, maxCena);
				if(specifications != null) {
					specifications.and(specification);
				} else {
					specifications = specification;
				}
			} catch (NumberFormatException e) {
				
			}
		}
		return specifications;
	}
}
