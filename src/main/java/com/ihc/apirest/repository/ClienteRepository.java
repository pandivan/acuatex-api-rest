package com.ihc.apirest.repository;

import com.ihc.apirest.models.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>
{
    //Métodos propios de spring para ejecutar consultas por medio de JPA

    boolean existsByCedulaAndClave(String cedula, String clave);


    boolean existsByCorreo(String correo);


    Cliente findByCorreoAndClave(String correo, String clave);


    Cliente findByCorreo(String correo);
}