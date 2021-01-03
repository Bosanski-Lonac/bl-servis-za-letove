package com.bosanskilonac.szl.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bosanskilonac.szl.service.AvionService;

import dto.AvionCUDto;
import dto.AvionDto;
import enums.Role;
import io.swagger.annotations.ApiOperation;
import security.CheckSecurity;

@RestController
@RequestMapping("/avion")
public class AvionController {
	private AvionService avionService;

	public AvionController(AvionService avionService) {
		this.avionService = avionService;
	}
	
	@ApiOperation(value = "Dodavanje aviona")
	@PostMapping
	@CheckSecurity(roles = {Role.ROLE_ADMIN}, checkOwnership = false)
	public ResponseEntity<AvionDto> add(@RequestHeader("Authorization") String authorization, 
			@RequestBody @Valid AvionCUDto avionCreateDto) {
		return new ResponseEntity<>(avionService.add(avionCreateDto), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Prikaz aviona")
	@GetMapping
	@CheckSecurity(roles = {Role.ROLE_ADMIN}, checkOwnership = false)
	public ResponseEntity<Page<AvionDto>> getPlanes(@RequestHeader("Authorization") String authorization,
			@RequestParam(value = "bstr", required = false, defaultValue="0") Integer brojStranice) {
		return new ResponseEntity<>(avionService.findAll(brojStranice), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Brisanje aviona")
	@DeleteMapping("/{avionId}")
	@CheckSecurity(roles = {Role.ROLE_ADMIN}, checkOwnership = false)
	public ResponseEntity<?> delete(@RequestHeader("Authorization") String authorization, 
			@PathVariable("avionId") Long avionId) {
		avionService.deleteById(avionId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
