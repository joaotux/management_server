package br.com.umdesenvolvedor.management_server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.umdesenvolvedor.management_server.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	Optional<User> findById(String uuid);
}
