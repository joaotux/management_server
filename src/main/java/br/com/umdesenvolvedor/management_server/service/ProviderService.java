package br.com.umdesenvolvedor.management_server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.umdesenvolvedor.management_server.model.company.Company;
import br.com.umdesenvolvedor.management_server.exception.BusinessException;
import br.com.umdesenvolvedor.management_server.model.Provider;
import br.com.umdesenvolvedor.management_server.repository.ProviderRepository;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository repository;

    @Autowired
    private ProdutoctService products;

    public Page<Provider> list(String name, boolean active, Pageable pageable, String uuid) {
        return repository.findByNameAndCompanyIdAndIsActive(name, active, pageable, uuid);
    }

    public Optional<Provider> find(Long id, String uuid) {
        return repository.findByIdAndCompanyId(id, uuid);
    }

    public Provider create(Provider provider, String uuid) {
        Company company = new Company(uuid);
        provider.setCompany(company);

        if(provider.getPerson().getPhone() != null) {
            for(int i = 0; i < provider.getPerson().getPhone().size(); i++) {
                provider.getPerson().getPhone().get(i).setPerson(provider.getPerson());
            }
        }
        return repository.save(provider);
    }

    public void delete(Provider provider, String uuid) {
        if(products.providerExist(provider.getId(), uuid).isPresent()) {
            throw new BusinessException("Fornecedor não pode ser removido, pois esta vinculado a um produto");
        }
        repository.delete(provider);
    }
}
