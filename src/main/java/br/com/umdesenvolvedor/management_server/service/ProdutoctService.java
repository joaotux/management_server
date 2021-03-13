package br.com.umdesenvolvedor.management_server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Product> findAll(String idCom) {
        return repository.findByCompanyId(idCom);
    }

    public Optional<Product> find(Long id, String uuid) {
        return repository.findByIdAndCompanyId(id, uuid);
    }
}
