package com.chiu.projects.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtResponseModel implements Serializable{
	private static final long serialVersionUID = 4219085568474655757L;

	private String token;
}
