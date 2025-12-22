package com.chiu.projects.mapper;

import java.util.ArrayList;
import java.util.List;

import com.chiu.projects.entities.Ordenes;
import com.chiu.projects.entities.Productos;
import com.chiu.projects.model.OrdenResponseDTO;
import com.chiu.projects.model.ProductoDTO;
import com.chiu.projects.model.SucursalDTO;

public class OrdenesMapper {
	public static OrdenResponseDTO mapOrdenes(Ordenes ordenes) {
		SucursalDTO sucursalDTO = SucursalDTO.builder()
									.nombre(ordenes.getSucursales().getNombre())
									.sucursalId(ordenes.getSucursales().getSucursalId())
									.build();
		
		List<ProductoDTO> listProductoDTO = new ArrayList<ProductoDTO>();
		ordenes.getProductos().forEach(productodb -> mapProducto(productodb, listProductoDTO));
		
		OrdenResponseDTO ordenResponseDTO = OrdenResponseDTO.builder()
											.ordenId(ordenes.getOrdenId())
											.fecha(ordenes.getFecha())
											.sucursales(sucursalDTO)
											.productos(listProductoDTO)
											.total(ordenes.getTotal())
											.build();

		return ordenResponseDTO;
	}

	private static void mapProducto(Productos producto, List<ProductoDTO> listProductos) {
		ProductoDTO productoDTO = ProductoDTO.builder()
									.codigo(producto.getCodigo())
									.descripcion(producto.getDescripcion())
									.precio(producto.getPrecio())
									.productoId(producto.getProductoId())
									.build();
		
		listProductos.add(productoDTO);
	}
}