package br.com.umdesenvolvedor.management_server.dto.company;

import br.com.umdesenvolvedor.management_server.model.company.CompanyType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyDTO {
    private String id;
    private String name;
    private String identification_number;
    private String identification_type;
    private String phone;
    private String info1;
    private String info2;
    private CompanyType type;
}
