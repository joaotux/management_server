package br.com.umdesenvolvedor.management_server.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.umdesenvolvedor.management_server.dto.company.CompanyDTO;
import br.com.umdesenvolvedor.management_server.exception.BusinessException;
import br.com.umdesenvolvedor.management_server.model.company.Company;
import br.com.umdesenvolvedor.management_server.model.User;
import br.com.umdesenvolvedor.management_server.repository.CompanyRepository;

@Service
public class CompanyService {
    
    @Autowired
    private CompanyRepository repository;

    @Autowired
    private UserService users;

    @Autowired
    private ModelMapper mapper;

    public CompanyDTO create(Company company) {
        User user = company.getUsers().get(0);

        if(users.findByEmail(user.getEmail()).isPresent())
            throw new BusinessException("E-mail já cadastrado");

        String password = new BCryptPasswordEncoder().encode(user.getPassword());

        user.setPassword(password);
        user.setCompany(company);
        company.getUsers().add(0, user);

        company = repository.save(company);
        return mapper.map(company, CompanyDTO.class);
    }
}
