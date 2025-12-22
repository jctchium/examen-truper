package com.chiu.projects.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrdenResponseDTO {
	private Integer ordenId;
	private SucursalDTO sucursales;
	private Date fecha;
	private BigDecimal total;
	private List<ProductoDTO> productos;
}