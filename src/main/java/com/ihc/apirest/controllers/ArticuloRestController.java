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
  ArticuloRepository productoRepository;



  /**
   * Método que permite obtener un producto según su Id
   * 
   * @param idProducto, Id Producto con el cual se buscara el producto en BD
   * @return Producto encontrado
   */
  @GetMapping(value = "/productos")
  public ResponseEntity<List<Articulo>> findAllCategoriasProductos() 
  {
    try 
    {
      List<Articulo> lstProductos = productoRepository.getAllProductos();

      return new ResponseEntity<List<Articulo>>(lstProductos, HttpStatus.OK);
    } 
    catch (Exception e) 
    {
      return new ResponseEntity<List<Articulo>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}