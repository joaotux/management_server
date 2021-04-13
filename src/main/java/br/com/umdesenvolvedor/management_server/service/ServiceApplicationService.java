package br.com.umdesenvolvedor.management_server.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.umdesenvolvedor.management_server.model.ServiceApplication;
import br.com.umdesenvolvedor.management_server.repository.ServiceRepository;

@org.springframework.stereotype.Service
public class ServiceApplicationService {
    @Autowired
    private ServiceRepository repository;

    public Optional<ServiceApplication> find(Long id, String tenance) {
        return repository.findByIdAndCompanyId(id, tenance);
    }

    public Page<ServiceApplication> list(String query, boolean active, String uuid, Pageable pageable) {
        return repository.list(query, active, uuid, pageable);
    }

    public ServiceApplication create(ServiceApplication service) {
        return repository.save(service);
    }

    public void delete(ServiceApplication service) {
        repository.delete(service);
    }
}
