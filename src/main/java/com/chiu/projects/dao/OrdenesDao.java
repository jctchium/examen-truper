package com.chiu.projects.dao;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.chiu.projects.entities.Ordenes;
import com.chiu.projects.exception.OrdenesDataException;
import com.chiu.projects.repository.OrdenesRepository;

@Component
public class OrdenesDao {
	private OrdenesRepository ordenesRepository;
	
	public OrdenesDao(OrdenesRepository ordenesRepository) {
		this.ordenesRepository = ordenesRepository;
	}

	public Ordenes addOrdenes(Ordenes ordenes) {
		return ordenesRepository.save(ordenes);
	}
	
	public Ordenes getOrdenes(Integer id) throws OrdenesDataException {
		Optional<Ordenes> optOrdenes = ordenesRepository.findById(id);
		if(!optOrdenes.isPresent()) {
			throw new OrdenesDataException("La orden " + id + " no se encuentra en la base de datos");
		}
		return optOrdenes.get();
	}
}