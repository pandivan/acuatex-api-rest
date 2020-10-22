package com.ihc.apirest.controllers;


import java.util.List;

import com.ihc.apirest.models.Articulo;
import com.ihc.apirest.repository.ArticuloRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ArticuloRestController 
{
  @Autowired
  ArticuloRepository articuloRepository;



  /**
   * Método que permite obtener un articulo según su Id
   * 
   * @param idArticulo, Id Articulo con el cual se buscara el articulo en BD
   * @return Articulo encontrado
   */
  @GetMapping(value = "/articulos")
  public ResponseEntity<List<Articulo>> findAllCategoriasArticulos() 
  {
    try 
    {
      List<Articulo> lstArticulos = articuloRepository.getAllArticulos();

      return new ResponseEntity<List<Articulo>>(lstArticulos, HttpStatus.OK);
    } 
    catch (Exception e) 
    {
      return new ResponseEntity<List<Articulo>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}