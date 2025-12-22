package com.chiu.projects.mapper;

import com.chiu.projects.entities.Productos;
import com.chiu.projects.model.ProductoDTO;

public class ProductosMapper {
	public static ProductoDTO mapProductos(Productos productos) {
		ProductoDTO productoDTO = ProductoDTO.builder()
										.productoId(productos.getProductoId())
										.codigo(productos.getCodigo())
										.descripcion(productos.getDescripcion())
										.precio(productos.getPrecio())
										.build();
		return productoDTO;
	}
}
