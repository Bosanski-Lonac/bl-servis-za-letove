package com.bosanskilonac.szl.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;

import dto.LetCUDto;
import dto.LetDto;
import exceptions.NotFoundException;

public interface LetService {
	LetDto add(LetCUDto letCUDto) throws DataIntegrityViolationException, NotFoundException;
	Page<LetDto> findAll(String pocetnaDestinacija, String krajnjaDestinacija, String minDuzina, String maxDuzina, String minCena, String maxCena, String brojStranice) throws EmptyResultDataAccessException;
	void deleteById(Long id) throws EmptyResultDataAccessException;
}
