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
              "where a.codigo in ('3002001001T04','3002001002T02','3002001003T08','3003001003T04','3003001005T04','3005004044T18','3005004045T10','3005004047T3','3007002001T12M','3007002002T12M','3012004001T1','3012005002TL','3012008001TXL','3008007001T14','3004001054TS','3004001058TXL','3005006028T9','3005005086T24','3012008002TXL','3007002066T18','3007002067T12') " +
              "and pa.cod_listaprecios = 01", nativeQuery = true
        )
  List<Articulo> getAllArticulos();
}