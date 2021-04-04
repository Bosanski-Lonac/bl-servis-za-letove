package com.bosanskilonac.szl.controller;

import javax.validation.Valid;

import dto.*;
import enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bosanskilonac.szl.service.LetService;

import io.swagger.annotations.ApiOperation;
import security.CheckSecurity;

@RestController
@RequestMapping("/let")
public class LetController {
	private LetService letService;

	public LetController(LetService letService) {
		this.letService = letService;
	}
	
	@ApiOperation(value = "Dodavanje leta")
	@PostMapping
	@CheckSecurity(roles = {Role.ROLE_ADMIN}, checkOwnership = false)
	public ResponseEntity<LetDto> add(@RequestHeader("Authorization") String authorization, 
			@RequestBody @Valid LetCUDto letCreateDto) {
		return new ResponseEntity<>(letService.add(letCreateDto), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Prikaz leta sa id-om")
	@GetMapping("/{letId}")
	@CheckSecurity(roles = {Role.ROLE_SERVICE}, checkOwnership = false)
	public ResponseEntity<LetDto> getLet(@RequestHeader("Authorization") String authorization,
			@PathVariable("letId") Long letId) {
		return new ResponseEntity<>(letService.findById(letId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Prikaz letova po id-u")
	@PutMapping
	@CheckSecurity(roles = {Role.ROLE_USER}, checkOwnership = false)
	public ResponseEntity<LetoviDto> getLetovePoId(@RequestHeader("Authorization") String authorization,
			@RequestBody @Valid ListaLetovaDto listaLetovaDto) {
		return new ResponseEntity<>(letService.findLetovi(listaLetovaDto), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Prikaz svih letova")
	@GetMapping
	@CheckSecurity(roles = {Role.ROLE_USER, Role.ROLE_ADMIN}, checkOwnership = false)
	public ResponseEntity<Page<LetDto>> getLetove(@RequestHeader("Authorization") String authorization,
			@RequestParam(value = "dprt", required = false, defaultValue="") String pocetnaDestinacija,
			@RequestParam(value = "arvl", required = false, defaultValue="") String krajnjaDestinacija,
			@RequestParam(value = "nduz", required = false, defaultValue="-1") Integer minDuzina,
			@RequestParam(value = "xduz", required = false, defaultValue="-1") Integer maxDuzina,
			@RequestParam(value = "ncen", required = false, defaultValue="-1") Integer minCena,
			@RequestParam(value = "xcen", required = false, defaultValue="-1") Integer maxCena,
			@RequestParam(value = "ndal", required = false, defaultValue="-1") Integer minDaljina,
			@RequestParam(value = "xdal", required = false, defaultValue="-1") Integer maxDaljina,
			@RequestParam(value = "bstr", required = false, defaultValue="0") Integer brojStranice) {
		return new ResponseEntity<>(letService.findAll(pocetnaDestinacija, krajnjaDestinacija,
				minDuzina, maxDuzina, minCena, maxCena, minDaljina, maxDaljina, brojStranice), HttpStatus.OK);
	}

	@ApiOperation(value = "Prikaz informacija o letovima")
	@GetMapping("/info")
	@CheckSecurity(roles = {Role.ROLE_USER, Role.ROLE_ADMIN}, checkOwnership = false)
	public ResponseEntity<LetoviInfoDto> getLetoviInfo(@RequestHeader("Authorization") String authorization) {
		return new ResponseEntity<>(letService.getLetoviInfo(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Brisanje leta")
	@DeleteMapping("/{letId}")
	@CheckSecurity(roles = {Role.ROLE_ADMIN}, checkOwnership = false)
	public ResponseEntity<?> delete(@RequestHeader("Authorization") String authorization, 
			@PathVariable("letId") Long letId) {
		letService.deleteById(letId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
