package com.chiu.projects.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.chiu.projects.dao.OrdenesDao;
import com.chiu.projects.entities.Ordenes;
import com.chiu.projects.entities.Productos;
import com.chiu.projects.entities.Sucursales;
import com.chiu.projects.exception.OrdenesDataException;
import com.chiu.projects.mapper.OrdenesMapper;
import com.chiu.projects.model.OrdenRequestDTO;
import com.chiu.projects.model.OrdenResponseDTO;
import com.chiu.projects.model.ProductoDTO;

import io.micrometer.observation.annotation.Observed;

@Service
public class OrdenesService {
	private OrdenesDao ordenesDao;
	
	public OrdenesService(OrdenesDao ordenesDao) {
		this.ordenesDao = ordenesDao;
	}

	@Observed(name = "add.ordenes")
	public OrdenResponseDTO addOrdenes(OrdenRequestDTO ordenDto) {
		Sucursales sucursales = Sucursales.builder()
								.sucursalId(ordenDto.getSucursal().getSucursalId())
								.nombre(ordenDto.getSucursal().getNombre())
								.build();
		
		BigDecimal total = ordenDto.getListProductos().stream()
				.map(ProductoDTO::getPrecio)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		Ordenes ordenes = Ordenes.builder()
							.sucursales(sucursales)
							.fecha(new Date())
							.total(total)
							.build();
		
		List<Productos> listProductos = ordenDto.getListProductos().stream()
										.map(productoDto -> mapProducto(productoDto, ordenes))
										.collect(Collectors.toList());

		ordenes.setProductos(listProductos);

		Ordenes ordenesSaved = ordenesDao.addOrdenes(ordenes);

		return OrdenesMapper.mapOrdenes(ordenesSaved);
	}

	private Productos mapProducto(ProductoDTO productoDto, Ordenes ordenes) {
		return Productos.builder()
				.productoId(productoDto.getProductoId())
				.descripcion(productoDto.getDescripcion())
				.codigo(productoDto.getCodigo())
				.precio(productoDto.getPrecio())
				.ordenes(ordenes)
				.build();
	}

	@Observed(name = "get.ordenes")
	public OrdenResponseDTO getOrdenes(Integer orderId) throws OrdenesDataException{
		Ordenes ordenes = ordenesDao.getOrdenes(orderId);
		
		return OrdenesMapper.mapOrdenes(ordenes);
	}
}