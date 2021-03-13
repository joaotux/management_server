package br.com.umdesenvolvedor.management_server.dto.user;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
public class UserCreateDTO extends UserDTO {
    @Setter
	@NotEmpty(message = "Senha é obrigatória")
	private String password;
}
