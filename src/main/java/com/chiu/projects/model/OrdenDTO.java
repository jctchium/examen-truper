package com.chiu.projects.model;

import java.util.List;

import lombok.Data;

@Data
public class OrdenDTO {
	private Integer sucursalId;
	private List<ProductoDTO> listProductos;
}