package br.com.umdesenvolvedor.management_server.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;

import br.com.umdesenvolvedor.management_server.dto.user.UserDTO;
import br.com.umdesenvolvedor.management_server.model.company.Company;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@ToString
public class User implements Serializable {
	private static final long serialVersionUID = -1952118550552308679L;

	@Setter
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	private String id;
	private String email;

	@Setter
	private String password;
	private int number;

	@JsonIgnore
	private LocalDateTime createTime;

	@Setter
	@ManyToOne
	@JoinColumn(name = "id_comp")
	private Company company;

	@PrePersist
	private void registreCreateTime() {
		this.createTime = LocalDateTime.now();
	}

	public User merger(UserDTO dto) {
		this.email = dto.getEmail();
		return this;
	}
}
