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
  @GetMapping(value = "/articulos")
  public ResponseEntity<List<Articulo>> getAllArticulos() 
  {
    try 
    {
      Map<String, Articulo> mapArticulos = new HashMap<String, Articulo>();

      List<Articulo> lstArticulos = articuloService.getAllArticulos();

      Double porcentajeIva = getIvaArticulos(articuloService.getIvaArticulos());


      for (Articulo articuloBD : lstArticulos) 
      {
        //Si el articulo existe obtenemos unicamente el campo talla
        if(mapArticulos.containsKey(articuloBD.getCodigoTalla()))
        {
          Articulo articulo = mapArticulos.get(articuloBD.getCodigoTalla());

          articulo.getLstTallas().add(articuloBD.getTalla());

          mapArticulos.put(articuloBD.getCodigoTalla(), articulo);
        }
        else
        {
          //Se construye el listado de tallas para cada articulo
          Set<String> lstTallas = new HashSet<String>();
          lstTallas.add(articuloBD.getTalla());
          
          articuloBD.setTalla(null);
          articuloBD.setLstTallas(lstTallas);

          articuloBD.setCodigo(articuloBD.getCodigoTalla());
          articuloBD.setCodigoTalla(null);

          //Se valida si se debe aplicar iva, 1 aplica, 0 no aplica
          if("1".equals(articuloBD.getIva()))
          {
            Double iva = (articuloBD.getPrecio() * porcentajeIva);
            double precio = articuloBD.getPrecio() + iva;
  
            articuloBD.setPrecio(precio);
            articuloBD.setIva(iva.toString());
          }

          mapArticulos.put(articuloBD.getCodigo(), articuloBD);
        }
      }

      return new ResponseEntity<List<Articulo>>(new ArrayList<Articulo>(mapArticulos.values()), HttpStatus.OK);
    } 
    catch (Exception e) 
    {
      return new ResponseEntity<List<Articulo>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }



  /**
   * Método que permite calcular el ivan que se aplicara a los articulos
   * @return porcentaje IVA
   */
  private Double getIvaArticulos(String ivaBD) 
  {
    double porcentajeIva = (double) 0;

    try 
    {
      if(null != ivaBD)
      {
        porcentajeIva = Double.parseDouble(ivaBD) / 100;
      }
    } 
    catch (NumberFormatException e) 
    {
      //Error controlado en caso de no poder convertir el valor del iva a double
    }
    return porcentajeIva;
  }
}