package com.chiu.projects.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Productos {
	@Id
	@Column(name = "producto_id")
	private Integer productoId;
	
	@ManyToOne
    @JoinColumn(name="orden_id", nullable=false)
    private Ordenes ordenes;
	
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