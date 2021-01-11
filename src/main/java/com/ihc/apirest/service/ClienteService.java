package com.ihc.apirest.service;

import com.ihc.apirest.models.Cliente;
import com.ihc.apirest.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class ClienteService 
{
  @Autowired
  private ClienteRepository clienteRepository;


  /**
   * Método que permite obtener un cliente según su correo
   * @param correo con el cual se buscara el cliente en BD
   * @return Cliente encontrado
   */
  public Cliente getClienteByCorreo(String correo) 
  {
    return clienteRepository.findByCorreo(correo);
  }


  /**
   * Método que permite registrar un cliente en BD
   * @param cliente a registrar
   * @return Cliente registrado
   */
  public Cliente registrarCliente(Cliente cliente) 
  {
    return clienteRepository.save(cliente);
  }


  /**
   * Método que permite validar si un cliente existe según su cedula y clave
   * @param cliente a validar
   * @return true si existe el cliente, en caso contrario false
   */
  public boolean existeClienteByCedulaAndClave(Cliente cliente) 
  {
    return clienteRepository.existsByCedulaAndClave(cliente.getCedula(), cliente.getClaveIngresada());
  }


  /**
   * Método que permite validar si un correo existe en bd
   * @param cliente que contiene el correo a valdiar
   * @return true si existe el correo, en caso contrario false
   */
  public boolean existeClienteByCorreo(Cliente cliente) 
  {
    return clienteRepository.existsByCorreo(cliente.getNuevoCorreo());
  }
}
