package br.com.umdesenvolvedor.management_server.web.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.umdesenvolvedor.management_server.model.State;
import br.com.umdesenvolvedor.management_server.repository.StateRepository;

@RestController
@RequestMapping("/state")
public class StateResource {
    
    @Autowired
    private StateRepository repository;

    @GetMapping(value = {"", "{name}"})
    public List<State> list(@PathVariable(required = false) String name) {
        name = name == null ? "%" : name;
        return repository.list(name);
    }

    @GetMapping("uf/{uf}")
    public State findByUF(@PathVariable String uf) {
        return repository.findByUf(uf).map(s -> {
            return s;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
