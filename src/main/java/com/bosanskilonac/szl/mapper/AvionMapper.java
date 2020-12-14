package com.bosanskilonac.szl.mapper;

import org.springframework.stereotype.Component;

import com.bosanskilonac.szl.model.Avion;

import dto.AvionCUDto;
import dto.AvionDto;

@Component
public class AvionMapper {
	public AvionDto avionToAvionDto(Avion avion) {
		AvionDto avionDto = new AvionDto();
		avionDto.setId(avion.getId());
		avionDto.setNaziv(avion.getNaziv());
		avionDto.setKapacitetPutnika(avion.getKapacitetPutnika());
		return avionDto;
	}
	
	public Avion avionCreateDtoToAvion(AvionCUDto avionCreateDto) {
		Avion avion = new Avion();
		avion.setNaziv(avionCreateDto.getNaziv());
		avion.setKapacitetPutnika(avionCreateDto.getKapacitetPutnika());
		return avion;
	}
}
