package br.com.umdesenvolvedor.management_server.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.umdesenvolvedor.management_server.model.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Long> {

    public static final String QUERY = "SELECT p FROM Provider p WHERE p.person.name LIKE %:name% AND p.company.id = :id AND p.active = :active ORDER BY p.person.name";

    @Query(QUERY)
    Page<Provider> findByNameAndCompanyIdAndIsActive(@Param("name") String name, @PathVariable("active") boolean active, Pageable pageable, @Param("id") String id);

    @Query(QUERY)
    List<Provider> findByNameAndCompanyIdAndIsActive(@Param("name") String name, @PathVariable("active") boolean active, @Param("id") String id);

    Optional<Provider> findByIdAndCompanyId(Long id, String uuid);
}
