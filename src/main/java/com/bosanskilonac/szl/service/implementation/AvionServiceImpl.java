package com.bosanskilonac.szl.service.implementation;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.bosanskilonac.szl.mapper.AvionMapper;
import com.bosanskilonac.szl.model.Avion;
import com.bosanskilonac.szl.repository.AvionRepository;
import com.bosanskilonac.szl.repository.LetRepository;
import com.bosanskilonac.szl.service.AvionService;

import dto.AvionCUDto;
import dto.AvionDto;
import exceptions.InUseException;

@Service
public class AvionServiceImpl implements AvionService {
	private AvionRepository avionRepository;
	private AvionMapper avionMapper;
	private LetRepository letRepository;

	public AvionServiceImpl(AvionRepository avionRepository, AvionMapper avionMapper, LetRepository letRepository) {
		this.avionRepository = avionRepository;
		this.avionMapper = avionMapper;
		this.letRepository = letRepository;
	}

	@Override
	public AvionDto add(AvionCUDto avionCreateDto) {
		Avion avion = avionMapper.avionCreateDtoToAvion(avionCreateDto);
		avion = avionRepository.save(avion);
		AvionDto avionDto = avionMapper.avionToAvionDto(avion);
		return avionDto;
	}

	@Override
	public void deleteById(Long id) throws EmptyResultDataAccessException, InUseException {
		long count = letRepository.countByAvionId(id);
		if(count > 0) {
			throw new InUseException("Avion ne može da se izbriše zato što ima zakazane letove.");
		}
		avionRepository.deleteById(id);
	}

}
