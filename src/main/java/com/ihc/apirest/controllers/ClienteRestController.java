package com.ihc.apirest.controllers;

import java.text.SimpleDateFormat;

import com.ihc.apirest.models.Cliente;
import com.ihc.apirest.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ClienteRestController 
{
  @Autowired
  ClienteRepository clienteRepository;


  SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
  


  /**
   * Método que permite crear un nuevo cliente en BD
   * @param cliente, Cliente a crear
   * @return Cliente creado
   */
  @PostMapping(value="/cliente")
  public ResponseEntity<Cliente> registrarCliente(@RequestBody Cliente cliente)
  {
    try 
    {
      Cliente clienteExistente = clienteRepository.findByCorreo(cliente.getCorreo());

      //Se valida que el correo del cliente no este registrado en la plataforma
      if(null != clienteExistente)
      {
        return new ResponseEntity<Cliente>(clienteExistente, HttpStatus.CREATED);
      }

      //Este metodo creará un usuario en BD para la app de [mi-bario-app]
      Cliente clienteBD = clienteRepository.save(cliente);

      return new ResponseEntity<Cliente>(clienteBD, HttpStatus.OK);
    } 
    catch (Exception e) 
    {
      return new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }



  /**
   * Método que permite obtener un cliente según el correo
   * @param cliente, Cliente que contiente el correo con el cual se buscara al cliente en BD
   * @return Cliente encontrado
   */
  @PostMapping(value = "/cliente/info")
  public ResponseEntity<Cliente> validarCliente(@RequestBody Cliente cliente) 
  {
    try
    {
      Cliente clienteBD = clienteRepository.findByCorreoAndClave(cliente.getCorreo(), cliente.getClave());

      if(null == clienteBD)
      {
        return new ResponseEntity<Cliente>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
      }
      
      return new ResponseEntity<Cliente>(clienteBD, HttpStatus.OK);
    }
    catch (Exception e) 
    {
      return new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }



  /**
   * Método que permite actualizar un cliente en BD
   * @param cliente, Cliente actualizar
   * @return Cliente actualizado
   */
  @PutMapping("/cliente")
  public ResponseEntity<Cliente> actualizarCliente(@RequestBody Cliente cliente)
  {
    try 
    {
      Cliente clienteActualizado = null;

      boolean isExisteCliente = clienteRepository.existsByCedulaAndClave(cliente.getCedula(), cliente.getClaveIngresada());

      if(isExisteCliente)
      {
        if(null != cliente.getNuevaClave())
        {
          cliente.setClave(cliente.getNuevaClave());
        }

        if(null != cliente.getNuevoCorreo())
        {
          cliente.setCorreo(cliente.getNuevoCorreo());
        }

        clienteActualizado = clienteRepository.save(cliente);
      }

      return new ResponseEntity<Cliente>(clienteActualizado, HttpStatus.OK);
    } 
    catch (Exception e) 
    {
      return new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }



  /**
   * Método que permite actualizar un cliente en BD
   * @param cliente, Cliente actualizar
   * @return True si el cliente fue actualizado, en caso contrario false
   */
  @PutMapping("/cliente/informacion")
  public ResponseEntity<Boolean> actualizarInformacionCliente(@RequestBody Cliente cliente)
  {
    try 
    {
      clienteRepository.save(cliente);

      return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    } 
    catch (Exception e) 
    {
      return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}