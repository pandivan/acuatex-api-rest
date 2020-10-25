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
                  "SELECT a.codigo, SUBSTRING(a.codigo, 0, CHARINDEX('T', a.codigo)+1) as codigo_articulo, a.grupo, a.nombre, a.nombre as descripcion, 1 as cantidad, pa.precio, SUBSTRING(a.codigo, CHARINDEX('T', a.codigo)+1, 5) as talla " +
                  "from dbo.articulos a " +
                  "inner join dbo.precios_articulos pa on pa.cod_articulo = a.codigo " +
                  "where a.codigo in ('3012005002TL','3012005002TM','3012005002TS','3012005002TXL','3012008001TL', '3004001001TS', '3004001001TXL', '3005005082T18','3005005083T10','3005005083T2', '3007002001T12M','3007002001T18M','3007002001T24', '3003001002T06','3003001003T04','3003001005T08', '3002001001T08','3002001001T10','3002001001T12') " +
                  "and pa.cod_listaprecios = 01", nativeQuery = true
          )
    List<Articulo> getAllArticulos();
}