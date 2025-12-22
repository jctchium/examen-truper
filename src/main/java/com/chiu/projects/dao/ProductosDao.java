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
	
	public Productos addProductos(Productos productos) {
		return productosRepository.save(productos);
	}
	
	public List<Productos> getProductos(Integer orderId) {
		return productosRepository.findByOrdenId(orderId);
	}
}
