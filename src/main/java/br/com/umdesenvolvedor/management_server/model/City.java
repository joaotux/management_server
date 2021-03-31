package br.com.umdesenvolvedor.management_server.model;

import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
@Entity
public class City extends EntityAbstract {
    private static final long serialVersionUID = 1L;
    
    private Long code;
    private String name;
    private String uf;
}
