package br.com.umdesenvolvedor.management_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.umdesenvolvedor.management_server.model.company.Company;

public interface CompanyRepository extends JpaRepository<Company, String> {
    
}
