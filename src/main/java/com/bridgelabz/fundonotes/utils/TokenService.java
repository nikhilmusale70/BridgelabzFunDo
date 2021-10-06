package com.bridgelabz.fundonotes.utils;

import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundonotes.Exception.FunDoNotesCutomException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenService {
	@Value("${secret.token}")
	public String secretToken;
	
	public String createToken(int id) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		JwtBuilder builder = Jwts.builder().setId(String.valueOf(id)).
				signWith(signatureAlgorithm, DatatypeConverter.parseString(secretToken));
		return builder.compact();
	}
	
	public String createToken(int id, Date expireTime) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		JwtBuilder builder = Jwts.builder().setId(String.valueOf(id)).setExpiration(expireTime).
				signWith(signatureAlgorithm, DatatypeConverter.parseString(secretToken));
		return builder.compact();
	}
	
	public int decodeToken(String token) {
		try {
			Claims claim = Jwts.parser().setSigningKey(DatatypeConverter.parseString(secretToken))
					.parseClaimsJws(token).getBody();
			return Integer.parseInt(claim.getId());
		} catch (Exception e) {
			throw new FunDoNotesCutomException(HttpStatus.BAD_REQUEST, "Error while decoding");
		}
	}
}
