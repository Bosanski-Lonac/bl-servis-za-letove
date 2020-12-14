package com.bosanskilonac.szl.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dto.LetCUDto;
import dto.LetCriteriaDto;
import dto.LetDto;
import exceptions.NotFoundException;

public interface LetService {
	LetDto add(LetCUDto letCUDto) throws DataIntegrityViolationException, NotFoundException;
	Page<LetDto> findAll(LetCriteriaDto letCriteriaDto, Pageable pageable) throws EmptyResultDataAccessException;
	void deleteById(Long id) throws EmptyResultDataAccessException;
}
