package com.ihc.apirest.repository;

import java.util.List;

import com.ihc.apirest.models.Articulo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, String>
{

  @Query(value = 
              "SELECT a.codigo, SUBSTRING(a.codigo, 0, CHARINDEX('T', a.codigo)+1) as codigo_talla, a.grupo, a.detalle, 1 as cantidad, ROUND(pa.precio, 2) precio, SUBSTRING(a.codigo, CHARINDEX('T', a.codigo)+1, 5) as talla, a.umedida, a.iva " +
              "from dbo.articulos a " +
              "inner join dbo.precios_articulos pa on pa.cod_articulo = a.codigo " +
              "where a.detalle is not null " +
              "and pa.cod_listaprecios = 03", nativeQuery = true
        )
  List<Articulo> getAllArticulos();


  @Query(value = "SELECT ROUND((cast(p.valor as float) / 100), 2) valor FROM dbo.parametros p where p.codigo = 'ParamPorcIva'", nativeQuery = true)
  String getIvaArticulos();
}