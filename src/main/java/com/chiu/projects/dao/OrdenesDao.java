package com.chiu.projects.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chiu.projects.entities.Ordenes;
import com.chiu.projects.repository.OrdenesRepository;

@Component
public class OrdenesDao {
	@Autowired
	private OrdenesRepository ordenesRepository;
	
	public Ordenes addOrdenes(Ordenes ordenes) {
		return ordenesRepository.save(ordenes);
	}
	
	public Ordenes getOrdenes(Integer id) {
		return ordenesRepository.findById(id).get();
	}
}