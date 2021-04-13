package br.com.umdesenvolvedor.management_server.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.umdesenvolvedor.management_server.dto.ServiceApplicationResponseDTO;
import br.com.umdesenvolvedor.management_server.model.company.Company;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode(callSuper = false)
@Entity
public class ServiceApplication extends EntityAbstract {
    private static final long serialVersionUID = 1L;
    
    private String description;

    private String code;

    @Column(precision = 21, scale = 2)
    private BigDecimal priceSales;

    @Column(precision = 21, scale = 2)
    private BigDecimal priceCost;

    private boolean active;

    @JsonIgnore
    @Setter
    @ManyToOne
    @JoinColumn(name = "id_comp")
    private Company company;

    public ServiceApplication merger(ServiceApplicationResponseDTO dto) {
        this.description = dto.getDescription();
        this.code = dto.getCode();
        this.priceSales = dto.getPriceSales();
        this.priceCost = dto.getPriceCost();
        this.active = dto.isActive();
        return this;
    }
}
