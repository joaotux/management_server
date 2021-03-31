package br.com.umdesenvolvedor.management_server.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.umdesenvolvedor.management_server.model.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.company.id = :idComp AND (p.description LIKE %:query% OR p.code " + 
    "LIKE %:query% OR p.barCode LIKE %:query%) ORDER BY p.description")
    Page<Product> find(@Param("idComp") String id, @Param("query") String query, Pageable pageable);

    Optional<Product> findByIdAndCompanyId(Long id, String uuid);

    @Query(value = "SELECT * FROM product p WHERE p.id_prov = :id AND p.id_comp = :uuid LIMIT 1", nativeQuery = true)
    Optional<Product> providerExist(@Param("id") Long id, @Param("uuid") String uuid);
}
