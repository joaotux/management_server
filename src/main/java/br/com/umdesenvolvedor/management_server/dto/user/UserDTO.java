package br.com.umdesenvolvedor.management_server.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
    private String id;
	
	@NotEmpty(message = "E-mail é obrigatório")
	@Email(message = "Informe um e-mail válido")
	private String email;
	private int number; 
}
