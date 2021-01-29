package com.ihc.apirest.controllers;

import com.ihc.apirest.models.Cliente;
import com.ihc.apirest.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
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
  BCryptPasswordEncoder bcrypt;

  


  /**
   * Método que permite actualizar los datos de acceso de un cliente en BD
   * @param cliente, Cliente actualizar
   * @return Cliente actualizado
   */
  // @PreAuthorize("hasRole('ROLE_ACUATEX_CLIENTE')")
  @PutMapping("/clientes/datos_acceso")
  public ResponseEntity<Cliente> actualizarDatosAccesoCliente(@RequestBody Cliente cliente)
  {
    try 
    {  
      Cliente clienteBD = clienteService.getClienteByCorreo(cliente.getCorreo());


      if(null != clienteBD && bcrypt.matches(cliente.getClaveIngresada(), clienteBD.getClave()))
      {
        //Se hace set de la clave por temas de sql update
        cliente.setClave(clienteBD.getClave());

        if(null != cliente.getNuevaClave())
        {
          cliente.setClave(bcrypt.encode(cliente.getNuevaClave()));
        }

        if(null != cliente.getNuevoCorreo())
        {
          if(clienteService.existeClienteByCorreo(cliente))
          {
            return new ResponseEntity<Cliente>(HttpStatus.CREATED);
          }
          cliente.setCorreo(cliente.getNuevoCorreo());
        }

        clienteService.actualizarDatosAccesoCliente(cliente);

        cliente.setClave(null);
        cliente.setNuevoCorreo(null);
        cliente.setNuevaClave(null);
        
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
      }

      return new ResponseEntity<Cliente>(clienteBD, HttpStatus.NO_CONTENT);
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
  // @PreAuthorize("hasRole('ACUATEX_CLIENTE')")
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