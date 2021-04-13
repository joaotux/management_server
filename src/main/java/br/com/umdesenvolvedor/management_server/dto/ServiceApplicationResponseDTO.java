package br.com.umdesenvolvedor.management_server.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceApplicationResponseDTO extends ServiceApplicationDTO {
    private BigDecimal priceSales;
    private BigDecimal priceCost;
    private boolean active;
}
