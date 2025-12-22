package com.chiu.projects.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chiu.projects.entities.Productos;
import com.chiu.projects.repository.ProductosRepository;

@Component
public class ProductosDao {
	@Autowired
	private ProductosRepository productosRepository;
	
	public List<Productos> addProductos(List<Productos> listProductos) {
		return productosRepository.saveAll(listProductos);
	}
	
	public Productos findByCodigo(String codigo) {
		return productosRepository.findByCodigo(codigo);
	}
	
	public Productos updateProductos(Productos productos) {
		return productosRepository.save(productos);
	}
}