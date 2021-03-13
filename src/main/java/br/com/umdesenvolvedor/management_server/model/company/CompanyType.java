package br.com.umdesenvolvedor.management_server.model.company;

import javax.persistence.Entity;

import br.com.umdesenvolvedor.management_server.model.EntityAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class CompanyType extends EntityAbstract {
    private static final long serialVersionUID = 4666185086469060973L;
    
    private String description;

    public CompanyType(Long id) {
        this.id = id;
    }
}
