package br.com.umdesenvolvedor.management_server.model.provider;

import javax.persistence.Entity;

import br.com.umdesenvolvedor.management_server.model.EntityAbstract;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
@Entity
public class Provider extends EntityAbstract {
    private static final long serialVersionUID = 1L;
    
    private String description;
}
