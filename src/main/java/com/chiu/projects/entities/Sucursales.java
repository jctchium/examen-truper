package com.chiu.projects.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "sucursales")
@Data
public class Sucursales {
	@Id
	@Column(name = "sucursal_id")
	private Integer sucursalId;
	
	@OneToOne(mappedBy = "sucursales")
	private Ordenes ordenes;
	
	@NotNull
	@Column(name = "nombre")
	private String nombre;
}