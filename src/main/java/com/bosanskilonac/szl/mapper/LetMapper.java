package com.bosanskilonac.szl.mapper;

import org.springframework.stereotype.Component;

import com.bosanskilonac.szl.model.Avion;
import com.bosanskilonac.szl.model.Let;

import dto.LetCUDto;
import dto.LetDto;

@Component
public class LetMapper {
	private AvionMapper avionMapper;
	
	public LetMapper(AvionMapper avionMapper) {
		this.avionMapper = avionMapper;
	}
	
	public LetDto letToLetDto(Let let) {
		LetDto letDto = new LetDto();
		letDto.setId(let.getId());
		letDto.setPocetnaDestinacija(let.getPocetnaDestinacija());
		letDto.setKrajnjaDestinacija(let.getKrajnjaDestinacija());
		letDto.setDuzina(let.getDuzina());
		letDto.setCena(let.getCena());
		letDto.setAvion(avionMapper.avionToAvionDto(let.getAvion()));
		return letDto;
	}
	
	public Let letCreateDtoToLet(LetCUDto letCreateDto, Avion avion) {
		Let let = new Let();
		let.setPocetnaDestinacija(letCreateDto.getPocetnaDestinacija());
		let.setKrajnjaDestinacija(letCreateDto.getKrajnjaDestinacija());
		let.setDuzina(letCreateDto.getDuzina());
		let.setCena(letCreateDto.getCena());
		let.setAvion(avion);
		return let;
	}
}
