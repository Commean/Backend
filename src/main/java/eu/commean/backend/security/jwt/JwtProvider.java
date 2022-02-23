package eu.commean.backend.security.jwt;

import eu.commean.backend.security.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Component
@Log4j2
public class JwtProvider {

	@Value("${commean.jwt-secret}")
	private String jwtSecret;

	@Value("${commean.jwt-exp}")
	private int jwtExpirationInDays;

	private Key getSecretKey() {
		return new SecretKeySpec(Base64.getDecoder().decode(jwtSecret),
				SignatureAlgorithm.HS512.getJcaName());
	}

	public String generateToken(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		Timestamp now = Timestamp.from(Instant.now());
		Timestamp expDate = Timestamp.from(Instant.now().plus(jwtExpirationInDays, ChronoUnit.DAYS));

		return Jwts.builder()
				.setSubject(userPrincipal.getUsername())
				.setIssuedAt(Date.from(Instant.now()))
				.setExpiration(expDate)
				.signWith(getSecretKey())
				.compact();
	}

	public String getUsernameFromJWT(String token) {
		Claims claims = (Claims) Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parse(token).getBody();
		return claims.getSubject();
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(authToken);
			log.debug("Valid JWT: {}", authToken);
			return true;

		} catch (JwtException e) {
			log.error("Error Invalid JWT (not trusted): {}", authToken);
			return false;
		}
	}
}
