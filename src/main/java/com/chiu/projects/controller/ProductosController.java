package com.chiu.projects.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiu.projects.model.ProductoDTO;
import com.chiu.projects.service.ProductosService;

@RestController
@RequestMapping("/productos")
public class ProductosController {
	@Autowired
	private ProductosService productosService;
	
	@PutMapping
	public ProductoDTO updateProductos(@RequestBody ProductoDTO productoDTO) {
		return productosService.updateProductos(productoDTO);
	}
}