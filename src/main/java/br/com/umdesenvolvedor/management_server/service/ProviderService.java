package br.com.umdesenvolvedor.management_server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.umdesenvolvedor.management_server.model.provider.Provider;
import br.com.umdesenvolvedor.management_server.repository.ProviderRepository;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository repository;

    public List<Provider> list(String name, String uuid) {
        return repository.findByNameAndCompanyId(name, uuid);
    }
}
