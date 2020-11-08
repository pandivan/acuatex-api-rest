package com.ihc.apirest.controllers;

import java.util.List;

import com.ihc.apirest.models.Ciudad;
import com.ihc.apirest.repository.CiudadRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CiudadRestController 
{
  @Autowired
  CiudadRepository ciudadRepository;



  /**
   * Método que permite obtener todas las ciudades
   * 
   * @return Lista de ciudades
   */
  @GetMapping(value = "/ciudades")
  public ResponseEntity<List<Ciudad>> findAllCiudades() 
  {
    try 
    {
      List<Ciudad> lstCiudades = ciudadRepository.getAllCiudades();

      return new ResponseEntity<List<Ciudad>>(lstCiudades, HttpStatus.OK);
    } 
    catch (Exception e) 
    {
      return new ResponseEntity<List<Ciudad>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}