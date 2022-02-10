package eu.commean.backend.security.jwt;

import eu.commean.backend.security.UserPrincipal;
import io.jsonwebtoken.*;
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
	//FIXME: Value annotation not working
	private String jwtSecret = "KQ!*wx%CvK&35En4hD!8GjHUE2d&$*8B";
	Key jwtKey = new SecretKeySpec(Base64.getEncoder().encode(jwtSecret.getBytes()), SignatureAlgorithm.HS512.getJcaName());
	@Value("${commean.jwt-exp}")
	private int jwtExpirationInDays;

	public String generateToken(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		Timestamp now = Timestamp.from(Instant.now());
		Timestamp expDate = Timestamp.from(Instant.now().plus(jwtExpirationInDays, ChronoUnit.DAYS));

		return Jwts.builder()
				.setSubject(userPrincipal.getUsername())
				.setIssuedAt(Date.from(Instant.now()))
				.setExpiration(expDate)
				.signWith(jwtKey)
				.compact();
	}

	public String getUsernameFromJWT(String token) {
		Claims claims = (Claims) Jwts.parserBuilder().setSigningKey(jwtKey).build().parse(token).getBody();
		return claims.getSubject();
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(jwtKey).build().parseClaimsJws(authToken);
			return true;

		} catch (SecurityException ex) {
		} catch (MalformedJwtException ex) {
		} catch (ExpiredJwtException ex) {
		} catch (UnsupportedJwtException ex) {
		} catch (IllegalArgumentException ex) {
		}
		return false;
	}
}
