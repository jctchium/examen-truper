package com.chiu.projects.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiu.projects.exception.OrdenesDataException;
import com.chiu.projects.exception.ProductosDataException;
import com.chiu.projects.model.ProductoDTO;
import com.chiu.projects.service.ProductosService;

@RestController
@RequestMapping("/productos")
public class ProductosController {
	private ProductosService productosService;
	
	public ProductosController(ProductosService productosService) {
		this.productosService = productosService;
	}
	
	@PutMapping
	public ProductoDTO updateProductos(@RequestBody ProductoDTO productoDTO) throws OrdenesDataException, ProductosDataException {
		return productosService.updateProductos(productoDTO);
	}
}