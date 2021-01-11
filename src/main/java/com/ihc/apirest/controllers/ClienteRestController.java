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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class ClienteRestController 
{
  @Autowired
  ClienteService clienteService;

  @Autowired
  JwtService jwtService;

  @Autowired
  AuthenticationManager authenticationManager;



  SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
  

  /**
   * Método que permite crear un nuevo cliente en BD
   * @param cliente a crear
   * @return Cliente creado
   */
  @PostMapping(value="/signup")
  public ResponseEntity<Cliente> registrarCliente(@RequestBody Cliente cliente)
  {
    try 
    {
      Cliente clienteExistente = clienteService.getClienteByCorreo(cliente.getCorreo());

      //Se valida que el correo del cliente no este registrado en la plataforma
      if(null != clienteExistente)
      {
        return new ResponseEntity<Cliente>(clienteExistente, HttpStatus.CREATED);
      }

      //Este metodo creará un usuario en BD para la app de [mi-bario-app]
      Cliente clienteBD = clienteService.registrarCliente(cliente); 

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

      String jwt = jwtService.generarToken(authentication);

      Cliente clienteBD = (Cliente) authentication.getPrincipal();

      clienteBD.setToken(jwt);
      
      return new ResponseEntity<Cliente>(clienteBD, HttpStatus.OK);
    }
    catch (Exception e) 
    {
      return new ResponseEntity<Cliente>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }
  }



  /**
   * Método que permite actualizar los datos de acceso de un cliente en BD
   * @param cliente, Cliente actualizar
   * @return Cliente actualizado
   */
  @PutMapping("/clientes/datos_acceso")
  public ResponseEntity<Cliente> actualizarDatosAccesoCliente(@RequestBody Cliente cliente)
  {
    try 
    {
      Cliente clienteActualizado = null;

      
      boolean isExisteCliente = clienteService.existeClienteByCedulaAndClave(cliente);

      if(isExisteCliente)
      {
        if(null != cliente.getNuevaClave())
        {
          cliente.setClave(cliente.getNuevaClave());
        }

        if(null != cliente.getNuevoCorreo())
        {
          boolean isExisteCorreo = clienteService.existeClienteByCorreo(cliente);
  
          //Se valida si el correo enviado existe
          if(isExisteCorreo)
          {
            return new ResponseEntity<Cliente>(clienteActualizado, HttpStatus.CREATED);
          }

          cliente.setCorreo(cliente.getNuevoCorreo());
        }

        clienteActualizado = clienteService.registrarCliente(cliente);

        return new ResponseEntity<Cliente>(clienteActualizado, HttpStatus.OK);
      }

      return new ResponseEntity<Cliente>(clienteActualizado, HttpStatus.NO_CONTENT);
    } 
    catch (Exception e) 
    {
      return new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }



  /**
   * Método que permite actualizar todos los datos de un cliente en BD
   * @param cliente, Cliente actualizar
   * @return True si el cliente fue actualizado, en caso contrario false
   */
  @PutMapping("/clientes")
  public ResponseEntity<Boolean> actualizarCliente(@RequestBody Cliente cliente)
  {
    try 
    {
      clienteService.registrarCliente(cliente);

      return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    } 
    catch (Exception e) 
    {
      return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}