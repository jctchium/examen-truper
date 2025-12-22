package com.chiu.projects.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.chiu.projects.dao.OrdenesDao;
import com.chiu.projects.dao.ProductosDao;
import com.chiu.projects.entities.Ordenes;
import com.chiu.projects.entities.Productos;
import com.chiu.projects.mapper.ProductosMapper;
import com.chiu.projects.model.ProductoDTO;

@Service
public class ProductosService {
	private ProductosDao productosDao;
	private OrdenesDao ordenesDao;
	
	public ProductosService(ProductosDao productosDao,
							OrdenesDao ordenesDao) {
		this.productosDao = productosDao;
		this.ordenesDao = ordenesDao;
	}
	
	public ProductoDTO updateProductos(ProductoDTO productoDTO) {
		Productos productos = productosDao.findByCodigo(productoDTO.getCodigo());
		if(productoDTO.getDescripcion() != null) {
			productos.setDescripcion(productoDTO.getDescripcion());
		}
		
		if(productoDTO.getPrecio() != null) {
			productos.setPrecio(productoDTO.getPrecio());
		}
		
		Productos productosUpdated = productosDao.updateProductos(productos);
		
		Ordenes ordenes = ordenesDao.getOrdenes(productosUpdated.getOrdenes().getOrdenId());
		BigDecimal total = ordenes.getProductos().stream()
				.map(Productos::getPrecio)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		ordenes.setTotal(total);
		Ordenes ordenesUpdated = ordenesDao.addOrdenes(ordenes);
		
		return ProductosMapper.mapProductos(productosUpdated);
	}
}
