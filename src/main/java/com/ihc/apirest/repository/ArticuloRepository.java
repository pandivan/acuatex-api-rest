package com.ihc.apirest.repository;

import java.util.List;

import com.ihc.apirest.models.Articulo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, String>
{
    /**
     * Método que permite obtener los Articulos seleccionados por acuatex
     */
    @Query(value = 
                  "SELECT a.codigo, a.grupo, a.nombre, a.nombre as descripcion, 1 as cantidad, pa.precio, 'XL' as talla " +
                  "from dbo.articulos a " +
                  "inner join dbo.precios_articulos pa on pa.cod_articulo = a.codigo " +
                  "where a.codigo in ('3002001002T12', '3002001002T14') " +
                  "and pa.cod_listaprecios = 01", nativeQuery = true
          )
    List<Articulo> getAllArticulos();
}