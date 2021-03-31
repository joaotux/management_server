package br.com.umdesenvolvedor.management_server.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = false)
@Entity
public class Phone extends EntityAbstract {
    private static final long serialVersionUID = 1L;
    
    @Getter
    private String number;

    @Getter
    @Enumerated(EnumType.STRING)
    private PhoneType type;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_pers")
    private Person person;
}
