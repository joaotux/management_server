package br.com.umdesenvolvedor.management_server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.umdesenvolvedor.management_server.model.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCompanyId(String id);

    Optional<Product> findByIdAndCompanyId(Long id, String uuid);
}
