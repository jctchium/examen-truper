package com.chiu.projects.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chiu.projects.entities.Sucursales;
import com.chiu.projects.repository.SucursalesRepository;

@Component
public class SucursalesDao {
	@Autowired
	private SucursalesRepository sucursalesRepository;
	
	public Sucursales addSucursal(Sucursales sucursales) {
		return sucursalesRepository.save(sucursales);
	}
}
