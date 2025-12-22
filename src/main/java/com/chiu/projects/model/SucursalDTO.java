package com.chiu.projects.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SucursalDTO {
	private Integer sucursalId;
	private String nombre;
}