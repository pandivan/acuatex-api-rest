package com.ihc.apirest.repository;

import com.ihc.apirest.models.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>
{
    /**
     * Método que permite validar si el cliente existe
     * @param correo Correo del cliente
     * @param clave Clave del Cliente
     * @return Cliente
     */
    Cliente findByCorreoAndClave(String correo, String clave);


    /**
     * Método que permite obtener un cliente según su correo
     * @param correo Correo del cliente a buscar
     * @return Cliente
     */
    Cliente findByCorreo(String correo);
}