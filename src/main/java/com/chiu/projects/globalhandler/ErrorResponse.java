package com.chiu.projects.globalhandler;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {
	private String errorMessage;
	private Integer errorCode;
}