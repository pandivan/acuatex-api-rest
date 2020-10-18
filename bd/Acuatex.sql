SELECT *
from dbo.grupo_articulos ga 
;

SELECT * 
from dbo.articulos a
where 1=1
and a.codigo in ('3002001002T12', '3002001002T14')
--and grupo != 'NIÑA'
and fecha BETWEEN CONVERT(DATETIME,'01/01/2020') and CONVERT(DATETIME,'31/12/2020')
;



/******+ PRODUCTOS ******/
SELECT a.codigo id, ga.nombre_grupo as categoria, a.nombre, a.nombre as descripcion, 1 as cantidad, pa.precio, 'XL' as talla
from dbo.articulos a
inner join dbo.grupo_articulos ga on ga.nombre_grupo = a.grupo and ga.nombre_subgrupo = a.subgrupo
inner join dbo.precios_articulos pa on pa.cod_articulo = a.codigo
where a.codigo in ('3002001002T12', '3002001002T14')
and pa.cod_listaprecios = 01
;





SELECT *
FROM dbo.lista_precios lp 
;

SELECT * 
FROM dbo.precios_articulos pa 
where cod_articulo in ('3002001002T12', '3002001002T14')
and cod_listaprecios = 01
;


SELECT *
FROM dbo.clientes c 
;

SELECT *
FROM dbo.direcciones d 
;

SELECT * from dbo.pedido_detalle pd 
