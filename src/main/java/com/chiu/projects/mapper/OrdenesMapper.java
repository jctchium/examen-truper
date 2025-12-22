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
		OrdenResponseDTO ordenResponseDTO = new OrdenResponseDTO();
		ordenResponseDTO.setOrdenId(ordenes.getOrdenId());
		ordenResponseDTO.setFecha(ordenes.getFecha());
		
		SucursalDTO sucursalDTO = new SucursalDTO();
		sucursalDTO.setNombre(ordenes.getSucursales().getNombre());
		sucursalDTO.setSucursalId(ordenes.getSucursales().getSucursalId());
		
		ordenResponseDTO.setSucursales(sucursalDTO);
		
		List<ProductoDTO> listProductoDTO = new ArrayList<ProductoDTO>();
		ordenes.getProductos().forEach(productodb -> mapProducto(productodb, listProductoDTO));
		
		ordenResponseDTO.setProductos(listProductoDTO);
		
		ordenResponseDTO.setTotal(ordenes.getTotal());
		
		return ordenResponseDTO;
	}
	
	private static void mapProducto(Productos producto, List<ProductoDTO> listProductos) {
		ProductoDTO productoDTO = new ProductoDTO();
		productoDTO.setCodigo(producto.getCodigo());
		productoDTO.setDescripcion(producto.getDescripcion());
		productoDTO.setPrecio(producto.getPrecio());
		productoDTO.setProductoId(producto.getProductoId());
		
		listProductos.add(productoDTO);
	}
}
