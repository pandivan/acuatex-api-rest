package com.ihc.apirest.security;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ihc.apirest.service.JwtService;
import com.ihc.apirest.service.UserDetailsServiceImpl;

import java.io.IOException;
import java.util.ArrayList;

public class JwtTokenFilter extends OncePerRequestFilter 
{

  // private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

  @Autowired
  JwtService jwtService;

  @Autowired
  UserDetailsServiceImpl userDetailsService;


  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException 
  {
    try 
    {
      String token = getToken(req);

      if (token != null && jwtService.validateToken(token)) 
      {
        String userName = jwtService.getUserNameFromToken(token);

        // Permite obtener el cliente desde la BD a traves del [user-name] 
        // UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        // UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        /**
         * Creamos un array de [Authorities] vacio para indicarle a los CONTROLLER que no necesitamos roles para acceder a las rutas, 
         * en caso de necesitar roles debemos activar las lineas de arriba y adcionar @PreAuthorize("hasRole('ACUATEX_CLIENTE')") en el metodo del contralador
         */
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userName, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    } 
    catch (Exception e) 
    {
      System.out.println("-> " + e.getMessage());
      // logger.error("fail en el método doFilter " + e.getMessage());
    }
    
    filterChain.doFilter(req, res);
  }


  

  private String getToken(HttpServletRequest request) 
  {
    String header = request.getHeader("Authorization");

    if (header != null && header.startsWith("Bearer"))
      return header.replace("Bearer ", "");

    return null;
  }
}
