package com.ihc.apirest.repository;

import java.util.List;

import com.ihc.apirest.models.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>
{
  /**
   * Método que permite actualizar el pedido
   * @param estado Id del estado 
   * @param nroPedido Id del pedido
   * @param cliente Id del que hizo pedido
   */
  @Transactional
  @Modifying
  @Query("update Pedido p SET p.estado = ?1 where p.nroPedido = ?2 and p.cliente = ?3")
  void actualizarEstadoPedido(Integer estado, String nroPedido, Integer cliente);



  /**
   * Método que permite obtener todos los pedidos del cliente
   * @param cliente Id del cliente
   * @return Listado de pedidos asociados al cliente
   */
  List<Pedido> findByCliente(Integer cliente);
}