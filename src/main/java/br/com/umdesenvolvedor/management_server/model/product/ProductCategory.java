package br.com.umdesenvolvedor.management_server.model.product;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.umdesenvolvedor.management_server.model.EntityAbstract;
import br.com.umdesenvolvedor.management_server.model.company.Company;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
@Entity
public class ProductCategory extends EntityAbstract {
    private static final long serialVersionUID = 1L;
    
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_comp")
    @JsonIgnore
    private Company company;
}
