package com.ihc.apirest.service;

import java.util.Collection;
import java.util.Date;

import com.ihc.apirest.models.Cliente;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JwtService 
{
  private static final String AUTHORITIES = "authorities";

  @Value("${jwt.secret_key}")
  private String secretKey;

  @Value("${jwt.expiration}")
  private int expiration;

  
  /**
   * Método que permite crear un token a partir de la autenticación del cliente (login y clave)
   * @param authentication contiene al cliente
   * @return Token
   */
  public String generarToken(Authentication authentication) 
  {
    Cliente cliente = (Cliente) authentication.getPrincipal();
    Collection<? extends GrantedAuthority> authorities = cliente.getAuthorities();

    return Jwts.builder()
                        .setSubject(cliente.getUsername())
                        .claim(AUTHORITIES, authorities)
                        .setIssuedAt(new Date())
                        // .setExpiration(new Date(System.currentTimeMillis() + expiration))
                        .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                        .signWith(SignatureAlgorithm.HS512, secretKey)
                        .compact();
  }


  
  /**
   * Método que permite obtener el user name a partir del token
   * @param token que contiene el user name
   * @return User name
   */
  public String getUserNameFromToken(String token) 
  {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }


  /**
   * Método que permite validar el token
   * @param token a validar
   * @return true si el token es valido, en caso contrario false
   */
  public boolean validateToken(String token)
  {
    try 
    {
      Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

      return true;

      //TODO:Generar Logger o registrar en bd el error
    } 
    catch (MalformedJwtException e) 
    {
      // logger.error("token mal formado");
    } 
    catch (UnsupportedJwtException e) 
    {
      // logger.error("token no soportado");
    } 
    catch (ExpiredJwtException e) 
    {
      // logger.error("token expirado");
    } 
    catch (IllegalArgumentException e) 
    {
      // logger.error("token vacío");
    } 
    catch (SignatureException e) 
    {
      // logger.error("fail en la firma");
    }
    return false;
  }
}