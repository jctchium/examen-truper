package com.chiu.projects.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chiu.projects.dao.OrdenesDao;
import com.chiu.projects.dao.ProductosDao;
import com.chiu.projects.dao.SucursalesDao;
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
	
	@Autowired
	private SucursalesDao sucursalesDao;
	
	public OrdenResponseDTO addOrdenes(OrdenRequestDTO ordenDto) {
		Sucursales sucursales = new Sucursales();
		sucursales.setSucursalId(ordenDto.getSucursal().getSucursalId());
		sucursales.setNombre(ordenDto.getSucursal().getNombre());

		//sucursalesDao.addSucursal(sucursales);

		Ordenes ordenes = new Ordenes();		
		ordenes.setSucursales(sucursales);
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
		List<Productos> listProductos = productosDao.getProductos(ordenesSaved.getOrdenId());
		
		//sucursales.setOrdenes(ordenesSaved);
		//Sucursales sucursalesSaved = sucursalesDao.addSucursal(sucursales);
		
		return OrdenesMapper.mapOrdenes(ordenesSaved, listProductos);
	}
	
	public OrdenResponseDTO getOrdenes(Integer orderId) {
		Ordenes ordenes = ordenesDao.getOrdenes(orderId);
		List<Productos> productos = productosDao.getProductos(orderId);
		
		return OrdenesMapper.mapOrdenes(ordenes, productos);
	}
}