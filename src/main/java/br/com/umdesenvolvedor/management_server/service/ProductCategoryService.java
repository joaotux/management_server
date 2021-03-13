package br.com.umdesenvolvedor.management_server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.umdesenvolvedor.management_server.model.product.ProductCategory;
import br.com.umdesenvolvedor.management_server.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository repository;

    public List<ProductCategory> list(String uuid) {
        return repository.findByCompanyId(uuid);
    }
}
