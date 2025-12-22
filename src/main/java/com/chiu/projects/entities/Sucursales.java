package com.chiu.projects.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Sucursales")
@Data
public class Sucursales {
	@Id
	@Column(name = "sucursal_id")
	private Integer sucursalId;
	
	@Column(name = "nombre")
	private String nombre;
}