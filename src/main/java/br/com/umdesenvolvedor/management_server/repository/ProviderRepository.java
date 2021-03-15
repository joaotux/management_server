package br.com.umdesenvolvedor.management_server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.umdesenvolvedor.management_server.model.provider.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Long> {

    @Query("SELECT p FROM Provider p WHERE p.name LIKE %:name% AND p.company.id = :id")
    List<Provider> findByNameAndCompanyId(@Param("name") String name, @Param("id") String id);
}
