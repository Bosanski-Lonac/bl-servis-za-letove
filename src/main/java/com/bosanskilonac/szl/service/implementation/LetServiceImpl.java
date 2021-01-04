package com.bosanskilonac.szl.service.implementation;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.bosanskilonac.szl.mapper.LetMapper;
import com.bosanskilonac.szl.model.Avion;
import com.bosanskilonac.szl.model.Let;
import com.bosanskilonac.szl.model.LetSpecifications;
import com.bosanskilonac.szl.repository.AvionRepository;
import com.bosanskilonac.szl.repository.LetRepository;
import com.bosanskilonac.szl.service.LetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.LetCUDto;
import dto.LetDto;
import exceptions.NotFoundException;
import utility.BLURL;

@Service
public class LetServiceImpl implements LetService {
	private final int velicinaStranice = 50;
	
	private LetRepository letRepository;
	private LetMapper letMapper;
	private AvionRepository avionRepository;
	private JmsTemplate jmsTemplate;
	private ObjectMapper objectMapper;

	public LetServiceImpl(LetRepository letRepository,
			LetMapper letMapper,
			AvionRepository avionRepository,
			JmsTemplate jmsTemplate,
			ObjectMapper objectMapper) {
		this.letRepository = letRepository;
		this.letMapper = letMapper;
		this.avionRepository = avionRepository;
		this.jmsTemplate = jmsTemplate;
		this.objectMapper = objectMapper;
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
	public LetDto findById(Long id) throws NotFoundException {
		Let let = letRepository
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Let nije nađen."));
		LetDto letDto = letMapper.letToLetDto(let);
		return letDto;
	}
	
	@Override
	public Page<LetDto> findAll(String pocetnaDestinacija, String krajnjaDestinacija, Integer minDuzina,
			Integer maxDuzina, Integer minCena, Integer maxCena, Integer brojStranice)
			throws EmptyResultDataAccessException {
		Specification<Let> specification = LetSpecifications.getLetByCriteriaSpec(pocetnaDestinacija,
				krajnjaDestinacija, minDuzina, maxDuzina, minCena, maxCena);
		return letRepository.findAll(specification, PageRequest.of(brojStranice, velicinaStranice))
				.map(letMapper::letToLetDto);
	}

	@Override
	public void deleteById(Long id) throws EmptyResultDataAccessException, NotFoundException {
		Let let = letRepository
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Let nije nađen."));
		letRepository.deleteById(id);
		LetDto letDto = letMapper.letToLetDto(let);
		try {
			jmsTemplate.convertAndSend(BLURL.AMQUEUE_FIDS, objectMapper.writeValueAsString(letDto));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
