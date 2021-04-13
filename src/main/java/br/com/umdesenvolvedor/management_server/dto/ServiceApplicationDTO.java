package br.com.umdesenvolvedor.management_server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceApplicationDTO {
    private Long id;
    private String description;
    private String code;
}
