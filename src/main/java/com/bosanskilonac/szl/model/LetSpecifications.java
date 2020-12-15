package com.bosanskilonac.szl.model;

import org.springframework.data.jpa.domain.Specification;

public class LetSpecifications {
	public static Specification<Let> getLetByPocetnaDestinacijaSpec(String pocetnaDestinacija) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.like(criteriaBuilder.lower(root.get("pocetnaDestinacija")), "%" + pocetnaDestinacija.toLowerCase() + "%");
		};
	}
	
	public static Specification<Let> getLetByKrajnjaDestinacijaSpec(String krajnjaDestinacija) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.like(criteriaBuilder.lower(root.get("krajnjaDestinacija")), "%" + krajnjaDestinacija.toLowerCase() + "%");
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
	
	public static Specification<Let> getLetByCriteriaSpec(String pocetnaDestinacija, String krajnjaDestinacija, Integer minDuzina,
			Integer maxDuzina, Integer minCena, Integer maxCena) {
		Specification<Let> specifications = null;
		if(!pocetnaDestinacija.isBlank()) {
			specifications = Specification.where(getLetByPocetnaDestinacijaSpec(pocetnaDestinacija));
		}
		if(!krajnjaDestinacija.isBlank()) {
			Specification<Let> specification = getLetByKrajnjaDestinacijaSpec(krajnjaDestinacija);
			if(specifications != null) {
				specifications.and(specification);
			} else {
				specifications = Specification.where(specification);
			}
		}
		if(minDuzina >= 0 && maxDuzina >= 0) {
			Specification<Let> specification = getLetByDuzinaSpec(minDuzina, maxDuzina);
			if(specifications != null) {
				specifications.and(specification);
			} else {
				specifications = Specification.where(specification);
			}
		}
		if(minCena >= 0 && maxCena >= 0) {
			Specification<Let> specification = getLetByCenaSpec(minCena, maxCena);
			if(specifications != null) {
				specifications.and(specification);
			} else {
				specifications = Specification.where(specification);
			}
		}
		return specifications;
	}
}
