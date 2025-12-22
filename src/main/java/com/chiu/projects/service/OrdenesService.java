package com.chiu.projects.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chiu.projects.dao.OrdenesDao;
import com.chiu.projects.dao.ProductosDao;
import com.chiu.projects.entities.Ordenes;
import com.chiu.projects.entities.Productos;
import com.chiu.projects.entities.Sucursales;
import com.chiu.projects.mapper.OrdenesMapper;
import com.chiu.projects.model.OrdenRequestDTO;
import com.chiu.projects.model.OrdenResponseDTO;
import com.chiu.projects.model.ProductoDTO;

@Service
public class OrdenesService {
	@Autowired
	private OrdenesDao ordenesDao;
	
	@Autowired
	private ProductosDao productosDao;
	
	public OrdenResponseDTO addOrdenes(OrdenRequestDTO ordenDto) {
		Sucursales sucursales = new Sucursales();
		sucursales.setSucursalId(ordenDto.getSucursal().getSucursalId());
		sucursales.setNombre(ordenDto.getSucursal().getNombre());

		Ordenes ordenes = new Ordenes();		
		ordenes.setSucursales(sucursales);
		ordenes.setFecha(new Date());
		
		List<Productos> listProductos = new ArrayList<Productos>();
		Productos productos;
		BigDecimal total = BigDecimal.ZERO;
		
		for(ProductoDTO productoDto: ordenDto.getListProductos()) {
			productos = new Productos();
			productos.setProductoId(productoDto.getProductoId());
			productos.setDescripcion(productoDto.getDescripcion());
			productos.setCodigo(productoDto.getCodigo());
			productos.setPrecio(productoDto.getPrecio());
			productos.setOrdenes(ordenes);
			
			listProductos.add(productos);
			total = total.add(productos.getPrecio());
		}

		ordenes.setTotal(total);
		ordenes.setProductos(listProductos);
		
		Ordenes ordenesSaved = ordenesDao.addOrdenes(ordenes);
		productosDao.addProductos(listProductos);
		
		return OrdenesMapper.mapOrdenes(ordenesSaved);
	}
	
	public OrdenResponseDTO getOrdenes(Integer orderId) {
		Ordenes ordenes = ordenesDao.getOrdenes(orderId);
		
		return OrdenesMapper.mapOrdenes(ordenes);
	}
}