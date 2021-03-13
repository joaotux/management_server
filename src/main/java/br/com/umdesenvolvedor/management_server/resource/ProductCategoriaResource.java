package br.com.umdesenvolvedor.management_server.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.umdesenvolvedor.management_server.model.product.ProductCategory;
import br.com.umdesenvolvedor.management_server.security.JwtDecoder;
import br.com.umdesenvolvedor.management_server.service.ProductCategoryService;

@RestController
@RequestMapping("/product-category")
public class ProductCategoriaResource {
    
    @Autowired
    private ProductCategoryService service;

    @GetMapping
    public List<ProductCategory> list(@RequestHeader (name = "Authorization") String token) {
        String uuid = JwtDecoder.getUUID(token);
        return service.list(uuid);
    }
}
