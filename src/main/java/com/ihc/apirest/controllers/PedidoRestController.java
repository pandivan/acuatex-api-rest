package com.ihc.apirest.controllers;

import java.util.List;

import com.ihc.apirest.models.Pedido;
import com.ihc.apirest.models.PedidoDetalle;
import com.ihc.apirest.repository.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PedidoRestController 
{
  @Autowired
  PedidoRepository pedidoRepository;

  static final Integer ID_ESTADO_PENDIENTE = 100;
  static final Integer ID_ESTADO_ACEPTADO = 101;


  /**
   * Método que permite crear un nuevo pedido
   * @param pedido, Pedido a crear
   * @return True si el pedido fue creado, en caso contrario False
   */
  @PostMapping(value="/pedido")
  public ResponseEntity<String> registrarPedido(@RequestBody Pedido pedido)
  {
    try 
    {
      //Se hace un set de pedido en todos los pedidos detalles, ya que javascript no adminte estructuras ciclicas en el caso de [PedidoDetalle] que contiene a [Pedido] 
      //y este a su vez contiene a [PedidoDetalle], lo cual imposibilita enviar un entity de [Pedido] desde la Web
      for (PedidoDetalle pedidoDetalle : pedido.getLstPedidoDetalle()) 
      {
        pedidoDetalle.setPedido(pedido);    
      }

      //Este metodo creará una pedido
      Pedido pedidoBD = pedidoRepository.save(pedido);

      return new ResponseEntity<String>(pedidoBD.getNroPedido(), HttpStatus.CREATED);
    } 
    catch (Exception e) 
    {
      return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }



  /**
 * Método que permite actualizar el estado de un pedido
 * @param pedido, Pedido actualizar
 * @return True si el pedido fue actualizado, en caso contrario False
 */
  @PutMapping("/pedido")
  public ResponseEntity<Boolean> actualizarEstadoPedido(@RequestBody Pedido pedido)
  {
    try 
    {
      //Actualizando el estado del pedido
      pedidoRepository.actualizarEstadoPedido(pedido.getEstado(), pedido.getNroPedido(), pedido.getCliente());
      
      return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    } 
    catch (Exception e) 
    {
      return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }



  /**
   * Método que permite obtener todos los pedidos PENDIENTES
   * @return Listado de pedidos
   */
  @GetMapping(value = "/pedido/{cedula}")
  public ResponseEntity<List<Pedido>> getAllPedidos(@PathVariable("cedula") Integer cedula) 
  {
    try
    {
      List<Pedido> lstPedidos = pedidoRepository.findByCliente(cedula);

      return new ResponseEntity<List<Pedido>>(lstPedidos, HttpStatus.OK);
    }
    catch (Exception e) 
    {
      return new ResponseEntity<List<Pedido>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}