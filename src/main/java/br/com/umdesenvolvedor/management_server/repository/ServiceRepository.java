package br.com.umdesenvolvedor.management_server.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.umdesenvolvedor.management_server.model.ServiceApplication;

public interface ServiceRepository extends JpaRepository<ServiceApplication, Long> {
    @Query("SELECT sa FROM ServiceApplication sa WHERE sa.company.id = :uuid "
    + "AND (sa.description LIKE %:query% OR sa.code LIKE %:query%) "
    + "AND sa.active = :active")
    Page<ServiceApplication> list(String query, boolean active, String uuid, Pageable pageable);

    Optional<ServiceApplication> findByIdAndCompanyId(Long id, String tenance);
}