package com.chiu.projects.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductoDTO {
	Integer productoId;
	String descripcion;
	String codigo;
	BigDecimal precio;
}