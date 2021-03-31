package br.com.umdesenvolvedor.management_server.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.umdesenvolvedor.management_server.model.City;
import br.com.umdesenvolvedor.management_server.repository.CityRepository;

@RestController
@RequestMapping("/city")
public class CityResource {
    
    @Autowired
    private CityRepository repository;

    @GetMapping(value = {"{uf}", "{uf}/{name}"})
    public List<City> list(@PathVariable String uf, @PathVariable(required = false) String name) {
        name = name == null ? "%" : name;
        return repository.findByUfLikeName(uf, name);
    }
}
