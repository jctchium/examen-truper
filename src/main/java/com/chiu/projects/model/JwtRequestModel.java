package com.chiu.projects.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtRequestModel implements Serializable{
	private static final long serialVersionUID = -15148869145726354L;
	
	private String username;
	private String password;
}