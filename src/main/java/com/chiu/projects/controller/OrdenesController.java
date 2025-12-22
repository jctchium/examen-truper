package com.chiu.projects.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiu.projects.entities.Ordenes;
import com.chiu.projects.model.OrdenDTO;
import com.chiu.projects.service.OrdenesService;

@RestController
@RequestMapping("/ordenes")
public class OrdenesController {
	@Autowired
	private OrdenesService ordenesService;
	
	@GetMapping("/{id}")
	public Ordenes getOrden(@PathVariable Integer id) {
		return ordenesService.getOrdenes(id);
	}
	
	@PostMapping
	public Ordenes addOrdenes(@RequestBody OrdenDTO ordenDto) {
		return ordenesService.addOrdenes(ordenDto);
	}
}