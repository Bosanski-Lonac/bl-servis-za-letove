package com.bosanskilonac.szl.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;

import dto.AvionCUDto;
import dto.AvionDto;
import exceptions.InUseException;

public interface AvionService {
	AvionDto add(AvionCUDto avionCreateDto);
	Page<AvionDto> findAll(Integer brojStranice) throws EmptyResultDataAccessException;
	void deleteById(Long id) throws EmptyResultDataAccessException, InUseException;
}
