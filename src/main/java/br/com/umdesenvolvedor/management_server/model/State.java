package br.com.umdesenvolvedor.management_server.model;

import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
@Entity
public class State extends EntityAbstract {
    private static final long serialVersionUID = 1L;
    
    private Long codeUf;
    private String name;
    private String uf;
}
