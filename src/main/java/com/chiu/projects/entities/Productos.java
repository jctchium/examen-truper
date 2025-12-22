package com.chiu.projects.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "productos")
@Data
public class Productos {
	@Id
	@Column(name = "producto_id")
	private Integer productoId;
	
	@Column(name = "orden_id")
	private Integer ordenId;
	
	@Column(name = "codigo")
	@NotNull
	private String codigo;
	
	@Column(name = "descripcion")
	@NotNull
	private String descripcion;
	
	@Column(name = "precio")
	@NotNull
	private BigDecimal precio;
}