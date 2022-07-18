package org.shopcart.jwt;

import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.shopcart.rest.AuthenticationService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public final class JWTSUtils
{
	private static final String API_KEY = "QWEwQWExQWEyQWEzQWE0QWE1QWE2QWE3QWE4QWE5QWIwQWIxQWIyQWIzQWI0QWI1QWI2QWI3QWI4QWI5QWMwQWMxQWMyQWMzQWM0QWM1QWM2QWM3QWM4QWM5QWQwQWQxQWQyQWQzQWQ0QWQ1QWQ2QWQ3QWQ4QWQ5QWUwQWUxQWUyQWUzQWU0QWU1QWU2QWU3QWU4QWU5QWYwQWYxQWYyQWYzQWY0QWY1QWY2QWY3QWY4QWY5QWcwQWcxQWcyQWczQWc0QWc1QWc2QWc3QWc4QWc5QWgwQWgxQWgyQWgzQWg0QWg1QWg2QWg3QWg4QWg5QWkwQWkxQWkyQWkzQWk0QQ==";
	
	private static final String ISSUER = AuthenticationService.class.getName();
	
	private static final long TTL = TimeUnit.MINUTES.toMillis(0x1e);
	
	private JWTSUtils()
	{
	}
	
	public static String createJWT(long id, String subject)
	{
		return createJWT(Long.toString(id), subject);
	}	
	
	@SuppressWarnings("deprecation")
	public static String createJWT(String id, String subject)
	{
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(API_KEY);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes,
				signatureAlgorithm.getJcaName());

		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now)
											.setSubject(subject).setIssuer(ISSUER)
											.signWith(signatureAlgorithm, signingKey);

		long expMillis = nowMillis + TTL;
		Date exp = new Date(expMillis);
		builder.setExpiration(exp);

		return builder.compact();
	}	
	
	
	public static Claims decodeJWT(String jwt)
	{
		Claims claims = Jwts.parser()
							.setSigningKey(DatatypeConverter.parseBase64Binary(API_KEY))
							.parseClaimsJws(jwt).getBody();
		return claims;
	}
	
	public static boolean isValid(String jwt)
	{
		try
		{
			Claims claims = JWTSUtils.decodeJWT(jwt);
			return claims.getExpiration().after(new Date());
		}
		catch (Exception e) 
		{
			return false;
		}
	}
	
	public static void checkAndUpdateJwt(JwtWrapper jwtWrapper)
	{
		try
		{
			Claims claims = JWTSUtils.decodeJWT(jwtWrapper.getToken());
			long remainingTime = claims.getExpiration().getTime() - System.currentTimeMillis();
			if(remainingTime <= 0x0)
			{
				jwtWrapper.markAsInvalid();
				return;
			}
			
			int minutes = (int) ((remainingTime / 1000) / 60);
			if(minutes < 0xa)
			{
				String token = JWTSUtils.createJWT(claims.getId(), claims.getSubject());
				jwtWrapper.setToken(token);
				jwtWrapper.markAsUpdated();
			}
		}
		catch (Exception e) 
		{
			jwtWrapper.markAsInvalid();
		}
	}
}
