package br.com.umdesenvolvedor.management_server.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode(callSuper = false)
@Entity
public class Person extends EntityAbstract {
    private static final long serialVersionUID = 1L;
    
    @Setter
    private String name;

    @Setter
    private String cpfcnpj;

    @Setter
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_addr")
    private Address address;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phone;
}
