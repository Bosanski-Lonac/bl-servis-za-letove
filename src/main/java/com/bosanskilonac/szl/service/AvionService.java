package com.bosanskilonac.szl.service;

import org.springframework.dao.EmptyResultDataAccessException;

import dto.AvionCUDto;
import dto.AvionDto;
import exceptions.InUseException;

public interface AvionService {
	AvionDto add(AvionCUDto avionCreateDto);
	void deleteById(Long id) throws EmptyResultDataAccessException, InUseException;
}
