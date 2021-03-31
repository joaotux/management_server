package br.com.umdesenvolvedor.management_server.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@Entity
public class Address extends EntityAbstract {
    private static final long serialVersionUID = 1L;
    
    private String street;
    private String number;
    private String zipCode;
    private String info;
    private String district;

    @ManyToOne
    @JoinColumn(name = "id_stat")
    private State state;

    @ManyToOne
    @JoinColumn(name = "id_city")
    private City city;
}
