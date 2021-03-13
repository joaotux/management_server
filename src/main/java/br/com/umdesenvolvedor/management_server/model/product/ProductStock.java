package br.com.umdesenvolvedor.management_server.model.product;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.umdesenvolvedor.management_server.model.EntityAbstract;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@EqualsAndHashCode(callSuper = false)
public class ProductStock extends EntityAbstract {
    private static final long serialVersionUID = 1L;
    
    private String description;
    private BigDecimal amount;
    private BigDecimal max;
    private BigDecimal min;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_prod")
    @JsonIgnore
    private Product product;
}
