package com.ihc.apirest.repository;

import java.util.List;

import com.ihc.apirest.models.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
// import org.springframework.transaction.annotation.Transactional;



@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>
{

  // @Transactional
  @Modifying
  @Query("update Pedido p SET p.estado = ?1 where p.nroPedido = ?2 and p.cliente = ?3")
  void actualizarEstadoPedido(Integer estado, String nroPedido, Integer cliente);


  List<Pedido> findByCliente(Integer cliente);


  @Query(value = "SELECT MAX(CAST(nro_pedido AS numeric)) from pedido_encabezado", nativeQuery = true)
  Long maxNroPedido();


  @Query(value = "SELECT MAX(secuencia) from pedido_detalle", nativeQuery = true)
  Integer maxSecuencia();
}