package com.quanlyphongkhamvadatlich.dto.client;

import java.io.Serializable;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ApiResponse<T> implements Serializable{
	private String message;
	private T data;
	private HttpStatus status;
}
