package com.chiu.projects.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chiu.projects.dao.OrdenesDao;
import com.chiu.projects.dao.ProductosDao;
import com.chiu.projects.entities.Ordenes;
import com.chiu.projects.entities.Productos;
import com.chiu.projects.model.OrdenDTO;
import com.chiu.projects.model.ProductoDTO;

@Service
public class OrdenesService {
	@Autowired
	private OrdenesDao ordenesDao;
	
	@Autowired
	private ProductosDao productosDao;
	
	public Ordenes addOrdenes(OrdenDTO ordenDto) {
		Ordenes ordenes = new Ordenes();
		ordenes.setSucursalId(ordenDto.getSucursalId());
		ordenes.setFecha(new Date());
		
		Ordenes ordenesSaved = ordenesDao.addOrdenes(ordenes);
		Productos productos;
		BigDecimal total = BigDecimal.ZERO;
		
		for(ProductoDTO productoDto: ordenDto.getListProductos()) {
			productos = new Productos();
			productos.setProductoId(productoDto.getProductoId());
			productos.setDescripcion(productoDto.getDescripcion());
			productos.setCodigo(productoDto.getCodigo());
			productos.setPrecio(productoDto.getPrecio());
			productos.setOrdenId(ordenesSaved.getOrdenId());
			
			productosDao.addProductos(productos);
			total = total.add(productos.getPrecio());
		}
		
		ordenes.setTotal(total);
		ordenesSaved = ordenesDao.addOrdenes(ordenesSaved);
		
		return ordenes;
	}
	
	public Ordenes getOrdenes(Integer orderId) {
		return ordenesDao.getOrdenes(orderId);
	}
}