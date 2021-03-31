package br.com.umdesenvolvedor.management_server.security;

import java.io.UnsupportedEncodingException;
import java.util.Collections;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.umdesenvolvedor.management_server.enumerated.EnumSecurity;
import br.com.umdesenvolvedor.management_server.model.User;
import br.com.umdesenvolvedor.management_server.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenAuthenticationService {
	@Autowired
	UserService service;

	static final String SECRET = EnumSecurity.KEY.getValue();
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	
	void addAuthentication(HttpServletResponse response, String username) throws UnsupportedEncodingException {
		User user = service.findByEmail(username).get();

		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));

		String JWT = Jwts.builder()
				.setSubject(username)
				.setId(user.getCompany().getId())
				.signWith(key, SignatureAlgorithm.HS512).compact();
				
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	}

	static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);

		if (token != null) {
			// faz parse do token
			String user = Jwts.parserBuilder()
					.setSigningKey(SECRET)
					.build()
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody()
					.getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
			}
		}
		return null;
	}

}
