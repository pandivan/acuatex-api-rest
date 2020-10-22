package com.ihc.apirest.controllers;

import java.text.SimpleDateFormat;

import com.ihc.apirest.models.Cliente;
import com.ihc.apirest.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/api/cliente")
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
    @PostMapping(value="/nuevo")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente)
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

            return new ResponseEntity<Cliente>(clienteBD, HttpStatus.CREATED);
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
    @PutMapping("/actualizar")
    public ResponseEntity<Boolean> actualizarCliente(@RequestBody Cliente cliente)
    {
        try 
        {
            //Este metodo creará un cliente en BD
            clienteRepository.save(cliente);
            
            return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
		} 
        catch (Exception e) 
        {
            return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
     }
    
     
    
    /**
     * Método que permite obtener un cliente según el correo
     * @param cliente, Cliente que contiente el correo con el cual se buscara al cliente en BD
     * @return Cliente encontrado
     */
    @PostMapping(value = "/validar")
    public ResponseEntity<Cliente> validarCliente(@RequestBody Cliente cliente) 
    {
        try
        {
            Cliente clienteBD = clienteRepository.findByCorreoAndClave(cliente.getCorreo(), cliente.getClave());
            
            return new ResponseEntity<Cliente>(clienteBD, HttpStatus.OK);
        }
        catch (Exception e) 
        {
			return new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}