package br.com.umdesenvolvedor.management_server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReceitaWs {
    private String nome;
    private String fantasia;
    private String telefone;
    private String email;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String municipio;
    private String uf;
    private String status;
    private String message;
}