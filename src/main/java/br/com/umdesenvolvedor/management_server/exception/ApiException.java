package br.com.umdesenvolvedor.management_server.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.validation.BindingResult;

import lombok.Getter;

@Getter
public class ApiException {
	private List<String> errors;

	public ApiException(BindingResult bindingResult) {
		this.errors = new ArrayList<>();
		bindingResult.getAllErrors().forEach(error -> this.errors.add(error.getDefaultMessage()));
	}

	public ApiException(BusinessException exception) {
		this.errors = Arrays.asList(exception.getMessage());
	}

}
