package br.com.umdesenvolvedor.management_server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.umdesenvolvedor.management_server.model.product.Product;
import br.com.umdesenvolvedor.management_server.repository.ProductRepository;

@Service
public class ProdutoctService {
    
    @Autowired
    private ProductRepository repository;

    public Product save(Product product) {
        for(int i = 0; i < product.getStocks().size(); i++) {
            product.getStocks().get(i).setProduct(product);
        }
        return repository.save(product);
    }

    public Page<Product> findAll(String idCom, String query, Pageable pageable) {
        return repository.find(idCom, query, pageable);
    }

    public Optional<Product> find(Long id, String uuid) {
        return repository.findByIdAndCompanyId(id, uuid);
    }

    public Optional<Product> providerExist(Long id, String uuid) {
        return repository.providerExist(id, uuid);
    }
}
