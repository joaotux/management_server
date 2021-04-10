package br.com.umdesenvolvedor.management_server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.umdesenvolvedor.management_server.model.State;

public interface StateRepository extends JpaRepository<State, Long> {

    @Query("SELECT s FROM State s WHERE s.name LIKE %:name% ORDER BY s.name")
    List<State> list(@Param("name") String name);

    Optional<State> findByUf(String uf);
    
}
