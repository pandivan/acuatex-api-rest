package com.ihc.apirest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ihc.apirest.models.Articulo;
import com.ihc.apirest.service.ArticuloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class ArticuloRestController 
{
  @Autowired
  ArticuloService articuloService;



  /**
   * Método que permite obtener todos los articulos con sus categorías
   * @return
   */
  @PreAuthorize("hasRole('PANDI')")
  @GetMapping(value = "/articulos")
  public ResponseEntity<List<Articulo>> getAllCategoriasArticulos() 
  {
    try 
    {
      Map<String, Articulo> mapArticulos = new HashMap<String, Articulo>();

      List<Articulo> lstArticulos = articuloService.getAllArticulos();

      for (Articulo articulo : lstArticulos) 
      {
        if(mapArticulos.containsKey(articulo.getCodigoArticulo()))
        {
          Articulo articuloBD = mapArticulos.get(articulo.getCodigoArticulo());

          articuloBD.getLstTallas().add(articulo.getTalla());

          mapArticulos.put(articulo.getCodigoArticulo(), articuloBD);
        }
        else
        {
          Set<String> lstTallas = new HashSet<String>();
          lstTallas.add(articulo.getTalla());
          
          articulo.setTalla(null);
          articulo.setLstTallas(lstTallas);

          articulo.setCodigo(articulo.getCodigoArticulo());
          articulo.setCodigoArticulo(null);

          mapArticulos.put(articulo.getCodigo(), articulo);
        }
      }

      return new ResponseEntity<List<Articulo>>(new ArrayList<Articulo>(mapArticulos.values()), HttpStatus.OK);
      // return new ResponseEntity<List<Articulo>>(lstArticulos, HttpStatus.OK);
    } 
    catch (Exception e) 
    {
      return new ResponseEntity<List<Articulo>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}