package com.bosanskilonac.szl.service.implementation;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bosanskilonac.szl.mapper.LetMapper;
import com.bosanskilonac.szl.model.Avion;
import com.bosanskilonac.szl.model.Let;
import com.bosanskilonac.szl.repository.AvionRepository;
import com.bosanskilonac.szl.repository.LetRepository;
import com.bosanskilonac.szl.service.LetService;

import dto.LetCUDto;
import dto.LetDto;
import exceptions.NotFoundException;

@Service
public class LetServiceImpl implements LetService {
	private LetRepository letRepository;
	private LetMapper letMapper;
	private AvionRepository avionRepository;

	public LetServiceImpl(LetRepository letRepository, LetMapper letMapper, AvionRepository avionRepository) {
		this.letRepository = letRepository;
		this.letMapper = letMapper;
		this.avionRepository = avionRepository;
	}

	@Override
	public LetDto add(LetCUDto letCreateDto) throws NotFoundException {
		Avion avion = avionRepository
				.findById(letCreateDto.getIdAvion())
				.orElseThrow(() -> new NotFoundException("Izabran avion ne postoji."));
		Let let = letMapper.letCreateDtoToLet(letCreateDto, avion);
		let = letRepository.save(let);
		LetDto letDto = letMapper.letToLetDto(let);
		return letDto;
	}

	@Override
	public Page<LetDto> findAll(Pageable pageable) throws EmptyResultDataAccessException {
		return letRepository.findAll(pageable)
				.map(letMapper::letToLetDto);
	}

	@Override
	public Page<LetDto> findByCriteria(Pageable pageable) throws EmptyResultDataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) throws EmptyResultDataAccessException {
		letRepository.deleteById(id);
	}

}
