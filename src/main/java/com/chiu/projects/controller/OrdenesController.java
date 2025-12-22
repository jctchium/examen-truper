package com.chiu.projects.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiu.projects.exception.OrdenesDataException;
import com.chiu.projects.model.OrdenRequestDTO;
import com.chiu.projects.model.OrdenResponseDTO;
import com.chiu.projects.service.OrdenesService;

@RestController
@RequestMapping("/ordenes")
public class OrdenesController {
	private OrdenesService ordenesService;
	
	public OrdenesController(OrdenesService ordenesService) {
		this.ordenesService = ordenesService;
	}
	
	@GetMapping("/{id}")
	public OrdenResponseDTO getOrden(@PathVariable Integer id) throws OrdenesDataException{
		return ordenesService.getOrdenes(id);
	}
	
	@PostMapping
	public OrdenResponseDTO addOrdenes(@RequestBody OrdenRequestDTO ordenDto) {
		return ordenesService.addOrdenes(ordenDto);
	}
}