package com.chiu.projects.model;

import java.util.List;

import lombok.Data;

@Data
public class OrdenRequestDTO {
	private SucursalDTO sucursal;
	private List<ProductoDTO> listProductos;
}