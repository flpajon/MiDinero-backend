package ar.com.midinero.MIDinero.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {

	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Integer expiration;

	public String generateToken(String userName) {
		return Jwts.builder().setSubject(userName).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public String getUserNameFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}

	public Boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("Malformed token");
		} catch (UnsupportedJwtException e) {
			logger.error("Unsupported token");
		} catch (ExpiredJwtException e) {
			logger.info("Expired token");
		} catch (IllegalArgumentException e) {
			logger.error("Empty token");
		} catch (SignatureException e) {
			logger.error("Signature failure");
		}
		return false;
	}
}
