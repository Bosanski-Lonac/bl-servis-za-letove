package com.bosanskilonac.szl.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;

import dto.LetCUDto;
import dto.LetDto;
import dto.LetoviDto;
import dto.ListaLetovaDto;
import exceptions.NotFoundException;

public interface LetService {
	LetDto add(LetCUDto letCUDto) throws DataIntegrityViolationException, NotFoundException;
	LetDto findById(Long id) throws NotFoundException;
	LetoviDto findLetovi(ListaLetovaDto listaLetovaDto);
	Page<LetDto> findAll(String pocetnaDestinacija, String krajnjaDestinacija,
			Integer minDuzina, Integer maxDuzina,
			Integer minCena, Integer maxCena,
			Integer minDaljina, Integer maxDaljina,
			Integer brojStranice) throws EmptyResultDataAccessException;
	void deleteById(Long id) throws EmptyResultDataAccessException, NotFoundException;
}
