package br.com.umdesenvolvedor.management_server.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.umdesenvolvedor.management_server.model.provider.Provider;
import br.com.umdesenvolvedor.management_server.security.JwtDecoder;
import br.com.umdesenvolvedor.management_server.service.ProviderService;

@RestController
@RequestMapping("/provider")
public class ProviderResource {
    
    @Autowired
    private ProviderService service;

    @GetMapping("{name}")
    public List<Provider> list(@PathVariable String name, @RequestHeader (name = "Authorization") String token) {
        String uuid = JwtDecoder.getUUID(token);
        return service.list(name, uuid);
    }
}
