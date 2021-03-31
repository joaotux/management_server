package br.com.umdesenvolvedor.management_server.model.company;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;

import br.com.umdesenvolvedor.management_server.model.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@EqualsAndHashCode(exclude = {"users"})
@NoArgsConstructor
public class Company {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	private String id;
    private String name;
    private String identification_number;
    private String identification_type;

    @NotEmpty(message = "Informe o celular")
    private String phone;

    @NotEmpty(message = "Informe a situação da sua empresa")
    private String info1;

    @NotEmpty(message = "Informe como nos encontrou")
    private String info2;

    @JsonIgnore
    private LocalDateTime createTime;

    @ManyToOne
    @JoinColumn(name = "id_comp_typ")
    private CompanyType type;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    public Company(String id) {
        this.id = id;
    }

    @PrePersist
	private void registreCreateTime() {
		this.createTime = LocalDateTime.now();
        this.type = new CompanyType(1L);
	}
}
