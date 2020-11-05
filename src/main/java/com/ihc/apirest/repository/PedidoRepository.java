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
    // /**
    //  * Método que permite actualizar el pedido
    //  * @param idTienda Id de la tienda que tomo el pedido
    //  * @param idEstado Id del estado Aceptado
    //  * @param idPedido Id del pedido
    //  */
    // @Transactional
    // @Modifying
    // @Query("update Pedido p SET p.idTienda = ?1, p.idEstado = ?2 where p.idPedido = ?3")
    // void actualizarPedido(Long idTienda, Long idEstado, Long idPedido);


    /**
     * Método que permite actualizar el pedido
     * @param idTienda Id de la tienda que tomo el pedido
     * @param idEstado Id del estado Aceptado
     * @param idPedido Id del pedido
     */
    // @Transactional
    // @Modifying
    // @Query("update Pedido p SET p.idEstado = ?1 where p.idPedido = ?2")
    // void actualizarEstadoPedido(Long idEstado, Long idPedido);



    /**
     * Método que permite obtener todos los pedidos del cliente
     * @param cliente Id del cliente
     * @return Listado de pedidos asociados al cliente
     */
    List<Pedido> findByCliente(Integer cliente);
}