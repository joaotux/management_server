package br.com.umdesenvolvedor.management_server.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.umdesenvolvedor.management_server.model.company.Company;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode(callSuper = false)
@Entity
public class Provider extends EntityAbstract {
    private static final long serialVersionUID = 1L;

    private String fantasyName;
    private boolean active;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pers")
    private Person person;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_comp")
    @JsonIgnore
    private Company company;


    public Provider mescla(Provider provider) {
        Address address = provider.getPerson().getAddress();

        this.active = provider.isActive();
        this.fantasyName = provider.getFantasyName();
        this.person.setName(provider.getPerson().getName());
        this.person.setCpfcnpj(provider.getPerson().getCpfcnpj());
        this.person.setEmail(provider.getPerson().getEmail());
        this.person.getAddress().setStreet(address.getStreet());
        this.person.getAddress().setNumber(address.getNumber());
        this.person.getAddress().setZipCode(address.getZipCode());
        this.person.getAddress().setInfo(address.getInfo());
        this.person.getAddress().setCity(address.getCity());
        this.person.getAddress().setState(address.getState());
        this.person.getAddress().setDistrict(address.getDistrict());

        if(provider.getPerson().getPhone() != null) {
            this.person.getPhone().clear();
            this.person.getPhone().addAll(provider.getPerson().getPhone());
        }
        return this;
    }
}
