package com.bosanskilonac.szl.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosanskilonac.szl.service.LetService;

import dto.LetCUDto;
import dto.LetCriteriaDto;
import dto.LetDto;
import enums.Role;
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
	
	@ApiOperation(value = "Prikaz svih letova")
	@GetMapping
	@CheckSecurity(roles = {Role.ROLE_USER, Role.ROLE_ADMIN}, checkOwnership = false)
	public ResponseEntity<Page<LetDto>> getLet(@RequestHeader("Authorization") String authorization, 
			@RequestBody @Valid LetCriteriaDto letCriteriaDto, Pageable pageable) {
		return new ResponseEntity<>(letService.findAll(letCriteriaDto, pageable), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Brisanje leta")
	@DeleteMapping("{letId}")
	@CheckSecurity(roles = {Role.ROLE_ADMIN}, checkOwnership = false)
	public ResponseEntity<?> delete(@RequestHeader("Authorization") String authorization, 
			@PathVariable("letId") Long letId) {
		letService.deleteById(letId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
