package br.com.umdesenvolvedor.management_server.security;

import javax.crypto.SecretKey;

import br.com.umdesenvolvedor.management_server.enumerated.EnumSecurity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtDecoder {
    private static SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(EnumSecurity.KEY.getValue()));

    public static String getUUID(String token) {
        Jws<Claims> jwt = Jwts.parserBuilder().setSigningKey(key) .build().parseClaimsJws(token.replace("Bearer ", ""));
        return jwt.getBody().getId();
    }
}
