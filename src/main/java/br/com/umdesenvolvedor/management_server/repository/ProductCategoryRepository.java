package br.com.umdesenvolvedor.management_server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.umdesenvolvedor.management_server.model.product.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    List<ProductCategory> findByCompanyId(String id);
}
