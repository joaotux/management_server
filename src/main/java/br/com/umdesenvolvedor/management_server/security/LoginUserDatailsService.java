package br.com.umdesenvolvedor.management_server.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.umdesenvolvedor.management_server.repository.UserRepository;
import br.com.umdesenvolvedor.management_server.model.User;

@Component
public class LoginUserDatailsService implements UserDetailsService {

	@Autowired
	private UserRepository usuarios;

	@Override
	public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
		Optional<User> usuarioLocalizado = usuarios.findByEmail(user);

		if (usuarioLocalizado.isEmpty())
			throw new UsernameNotFoundException("Usuário não encontrado!");

		return new UserSystem(usuarioLocalizado.get().getEmail(), usuarioLocalizado.get().getPassword(),
				authorities(usuarioLocalizado.get()));
	}

	public Collection<? extends GrantedAuthority> authorities(User user) {
		Collection<GrantedAuthority> auths = new ArrayList<>();
		return auths;
	}
}