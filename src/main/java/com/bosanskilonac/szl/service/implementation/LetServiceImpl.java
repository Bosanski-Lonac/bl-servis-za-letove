package com.bosanskilonac.szl.service.implementation;

import java.util.ArrayList;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
import dto.ListaLetovaDto;
import dto.RezervacijeLetovaDto;
import exceptions.NotFoundException;
import utility.BLURL;

@Service
public class LetServiceImpl implements LetService {
	private final int velicinaStranice = 50;
	
	private LetRepository letRepository;
	private LetMapper letMapper;
	private AvionRepository avionRepository;
	private RestTemplate serviceCommunicationRestTemplate;
	private JmsTemplate jmsTemplate;
	private ObjectMapper objectMapper;

	public LetServiceImpl(LetRepository letRepository,
			LetMapper letMapper,
			AvionRepository avionRepository,
			RestTemplate serviceCommunicationRestTemplate,
			JmsTemplate jmsTemplate,
			ObjectMapper objectMapper) {
		this.letRepository = letRepository;
		this.letMapper = letMapper;
		this.avionRepository = avionRepository;
		this.serviceCommunicationRestTemplate = serviceCommunicationRestTemplate;
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
	public Page<LetDto> findAll(String pocetnaDestinacija, String krajnjaDestinacija,
			Integer minDuzina, Integer maxDuzina, 
			Integer minCena, Integer maxCena,
			Integer minDaljina, Integer maxDaljina,
			Integer brojStranice)
			throws EmptyResultDataAccessException {
		Specification<Let> specification = LetSpecifications.getLetByCriteriaSpec(pocetnaDestinacija,
				krajnjaDestinacija, minDuzina, maxDuzina, minCena, maxCena, minDaljina, maxDaljina);
		Page<LetDto> strana = letRepository.findAll(specification, PageRequest.of(brojStranice, velicinaStranice))
				.map(letMapper::letToLetDto);
		
		ListaLetovaDto listaLetovaDto = new ListaLetovaDto();
		listaLetovaDto.setLetovi(new ArrayList<>());
		for(LetDto letDto : strana.getContent()) {
			listaLetovaDto.getLetovi().add(letDto.getId());
		}
		HttpEntity<ListaLetovaDto> request = new HttpEntity<>(listaLetovaDto);
		
		ResponseEntity<RezervacijeLetovaDto> response = serviceCommunicationRestTemplate
				.exchange(BLURL.getCountReservationsURL(),
						HttpMethod.POST, request, RezervacijeLetovaDto.class);
		
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			for(LetDto letDto : strana.getContent()) {
				letDto.setKapacitet(letDto.getKapacitet()
						- response.getBody().getListaLetRezervacije().get(letDto.getId()));
			}
		}
		return strana;
		/*return letRepository.findAll(specification, PageRequest.of(brojStranice, velicinaStranice))
				.map(letMapper::letToLetDto);*/
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
