package com.ihc.apirest.controllers;

import java.text.SimpleDateFormat;

import com.ihc.apirest.models.Cliente;
import com.ihc.apirest.service.ClienteService;
import com.ihc.apirest.service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class AutenticacionRestController 
{
  @Autowired
  ClienteService clienteService;

  @Autowired
  JwtService jwtService;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  BCryptPasswordEncoder bcrypt;



  SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
  

  /**
   * Método que permite crear un nuevo cliente en BD
   * @param cliente a crear
   * @return Cliente creado
   */
  @PostMapping(value="/signup")
  public ResponseEntity<Cliente> signup(@RequestBody Cliente cliente)
  {
    try 
    {
      Cliente clienteExistente = clienteService.getClienteByCorreo(cliente.getCorreo());

      //Se valida que el correo del cliente no este registrado en la plataforma
      if(null != clienteExistente)
      {
        return new ResponseEntity<Cliente>(clienteExistente, HttpStatus.CREATED);
      }

      /**
        Habilitar las siguientes lineas en el caso que se necesite registrar un usuario con ROLES...
        

        // Se crea un array o set de roles con el fin de agregar el nuevo rol que se asigno desde la web,
        // Set<Rol> roles = new HashSet<>();

        // Se crea al usuario con el ROL por defecto ROLE_USER
        // roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
          
        // Se valida si el nuevo usuario tiene el rol ADMIN para agregarlo al Set<> de roles, de lo contrario se crea con el ROL por defecto ROLE_USER
        // if(nuevoUsuario.getRoles().contains("admin"))
        // {
        //   roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        // }
        
        // usuario.setRoles(roles);
       **/

      cliente.setClave(bcrypt.encode(cliente.getClave()));

      //Este metodo creará un usuario en BD para la app de [mi-bario-app]
      Cliente clienteBD = clienteService.registrarCliente(cliente);
      
      String jwt = jwtService.generarToken(clienteBD);

      clienteBD.setToken(jwt);
      clienteBD.setClave(null);

      return new ResponseEntity<Cliente>(clienteBD, HttpStatus.OK);
    } 
    catch (Exception e) 
    {
      return new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }



  /**
   * Método que permite validar un cliente según su correo y clave
   * @param cliente que contiente el correo y clave a validar
   * @return Cliente encontrado
   */
  @PostMapping(value = "/login")
  public ResponseEntity<Cliente> login(@RequestBody Cliente cliente) 
  {
    try
    {
      //El correo es el username de la aplicacion
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(cliente.getCorreo(), cliente.getClave()));
        
      SecurityContextHolder.getContext().setAuthentication(authentication);
      
      Cliente clienteBD = (Cliente) authentication.getPrincipal();

      String jwt = jwtService.generarToken(clienteBD);

      clienteBD.setToken(jwt);
      clienteBD.setClave(null);
      
      return new ResponseEntity<Cliente>(clienteBD, HttpStatus.OK);
    }
    catch (Exception e) 
    {
      return new ResponseEntity<Cliente>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }
  }
}