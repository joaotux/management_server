package br.com.umdesenvolvedor.management_server.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.umdesenvolvedor.management_server.dto.user.UserCreateDTO;
import br.com.umdesenvolvedor.management_server.dto.user.UserDTO;
import br.com.umdesenvolvedor.management_server.exception.BusinessException;
import br.com.umdesenvolvedor.management_server.model.user.User;
import br.com.umdesenvolvedor.management_server.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    public UserDTO save(UserCreateDTO userDto) {
        if(repository.findByEmail(userDto.getEmail()).isPresent())
            throw new BusinessException("E-mail já cadastrado");

        String password = new BCryptPasswordEncoder().encode(userDto.getPassword());
        userDto.setPassword(password);
        
        User user = mapper.map(userDto, User.class);
        user = repository.save(user);
        return mapper.map(user, UserDTO.class);
    }

    public UserDTO alter(User user) {
        Optional<User> optional = repository.findByEmail(user.getEmail());
        if(optional.isPresent() && !optional.get().getId().equals(user.getId()))
            throw new BusinessException("E-mail já cadastrado");
        
        user = repository.save(user);
        return mapper.map(user, UserDTO.class);
    }

	public Optional<User> findById(String uuid) {
        return repository.findById(uuid);
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
