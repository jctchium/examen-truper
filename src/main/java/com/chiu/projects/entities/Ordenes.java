package com.chiu.projects.entities;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Ordenes")
@Data
public class Ordenes {
	@Id
	@Column(name = "orden_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ordenId;
	
	@Column(name = "sucursal_id")
	private Integer sucursalId;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "total")
	private BigDecimal total;
}
