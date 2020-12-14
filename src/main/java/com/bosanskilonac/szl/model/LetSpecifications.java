package com.bosanskilonac.szl.model;

import org.springframework.data.jpa.domain.Specification;

import dto.LetCriteriaDto;

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
	
	public static Specification<Let> getLetByCriteriaSpec(LetCriteriaDto letCriteriaDto) {
		Specification<Let> specifications = null;
		if(letCriteriaDto.getPocetnaDestinacija() != null) {
			specifications = getLetByPocetnaDestinacijaSpec(letCriteriaDto.getPocetnaDestinacija());
		}
		if(letCriteriaDto.getKrajnjaDestinacija() != null) {
			Specification<Let> specification = getLetByPocetnaDestinacijaSpec(letCriteriaDto.getPocetnaDestinacija());
			if(specifications != null) {
				specifications.and(specification);
			} else {
				specifications = specification;
			}
		}
		if(letCriteriaDto.getMinDuzina() != null && letCriteriaDto.getMaxDuzina() != null) {
			Specification<Let> specification = getLetByDuzinaSpec(letCriteriaDto.getMinDuzina(), letCriteriaDto.getMaxDuzina());
			if(specifications != null) {
				specifications.and(specification);
			} else {
				specifications = specification;
			}
		}
		if(letCriteriaDto.getMinCena() != null && letCriteriaDto.getMinCena() != null) {
			Specification<Let> specification = getLetByCenaSpec(letCriteriaDto.getMinCena(), letCriteriaDto.getMinCena());
			if(specifications != null) {
				specifications.and(specification);
			} else {
				specifications = specification;
			}
		}
		return specifications;
	}
}
