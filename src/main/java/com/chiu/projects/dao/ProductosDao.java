package com.chiu.projects.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.chiu.projects.entities.Productos;
import com.chiu.projects.exception.ProductosDataException;
import com.chiu.projects.repository.ProductosRepository;

@Component
public class ProductosDao {
	private ProductosRepository productosRepository;
	
	public ProductosDao(ProductosRepository productosRepository) {
		this.productosRepository = productosRepository;
	}
	
	public List<Productos> addProductos(List<Productos> listProductos) {
		return productosRepository.saveAll(listProductos);
	}
	
	public Productos findByCodigo(String codigo) throws ProductosDataException {
		Productos productos = productosRepository.findByCodigo(codigo);
		if(productos == null) {
			throw new ProductosDataException("El producto con el codigo " + codigo + " no se encuentra en la base de datos");
		}
		
		return productos;
	}
	
	public Productos updateProductos(Productos productos) {
		return productosRepository.save(productos);
	}
}