package br.com.umdesenvolvedor.management_server.web.resource;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.umdesenvolvedor.management_server.dto.product.ProductDTO;
import br.com.umdesenvolvedor.management_server.model.company.Company;
import br.com.umdesenvolvedor.management_server.model.product.Product;
import br.com.umdesenvolvedor.management_server.security.JwtDecoder;
import br.com.umdesenvolvedor.management_server.service.ProdutoctService;

@RestController
@RequestMapping("/product")
public class ProductResource {
    
    @Autowired
    private ProdutoctService service;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Product create(@RequestBody @Valid Product product, @RequestHeader (name = "Authorization") String token) {
        String uuid = JwtDecoder.getUUID(token);
        Company company = new Company(uuid);
        product.setCompany(company);
        return service.save(product);
    }

    @GetMapping(value = {"list", "list/{query}"})
    public Page<ProductDTO> findAll(@PathVariable(required = false) String query, Pageable pageable, @RequestHeader (name = "Authorization") String token) {
        String uuid = JwtDecoder.getUUID(token);
        query = query == null ? "%" : query;
        return service.findAll(uuid, query, pageable).map(p -> {
            return mapper.map(p, ProductDTO.class);
        }); 
    }

    @GetMapping(value = {"list-no-page", "list-no-page/{query}"})
    public List<Product> findAll(@PathVariable(required = false) String query, @RequestHeader (name = "Authorization") String token) {
        String uuid = JwtDecoder.getUUID(token);
        query = query == null ? "%" : query;
        return service.findAll(uuid, query);
    }

    @GetMapping("{id}")
    public Product find(@PathVariable Long id, @RequestHeader (name = "Authorization") String token) {
        String uuid = JwtDecoder.getUUID(token);

        return service.find(id, uuid).map(product -> {
            return product;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    public Product alter(@PathVariable Long id, @RequestBody @Valid Product product, @RequestHeader (name = "Authorization") String token) {
        String uuid = JwtDecoder.getUUID(token);
        return service.find(id, uuid).map(p -> {
            p = p.merger(product);
            return service.save(p);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id, @RequestHeader (name = "Authorization") String token) {
        String uuid = JwtDecoder.getUUID(token);
        service.find(id, uuid).ifPresent(p -> {
            service.delete(p);
        });
    }
}
