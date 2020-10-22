SELECT *
from dbo.grupo_articulos ga 
;


SELECT 
SUBSTRING(a.codigo, CHARINDEX('T', a.codigo)+1, 5) talla, a.*
from dbo.articulos a
where 1=1
--and a.codigo like '%XP%'
and a.codigo in ('3002001002T12', '3002001002T14', '3006001001TXL', '3004001096TL', '3004001096TXL')
--and grupo != 'NIÑA'
--and fecha BETWEEN CONVERT(DATETIME,'01/01/2020') and CONVERT(DATETIME,'31/12/2020')
order by 1
;


SELECT SUBSTRING('3002011002T10', CHARINDEX('T', '3002011002T10')+1, 5), '3002011002T10'; 



/******+ ARTICULOS ******/

SELECT  
a.codigo, a.grupo, a.nombre, a.nombre as descripcion, 1 as cantidad, pa.precio, SUBSTRING(a.codigo, CHARINDEX('T', a.codigo)+1, 5) as talla, a.fecha 
from dbo.articulos a
inner join dbo.precios_articulos pa on pa.cod_articulo = a.codigo
where a.codigo in ('3002001002T12', '3002001002T14', '3006001001TXL', '3004001096TL', '3004001096TXL')
and pa.cod_listaprecios = 01
;






SELECT *
FROM dbo.lista_precios lp 
;

SELECT * 
FROM dbo.precios_articulos pa 
where 1=1
--and cod_articulo in ('3002001002T12', '3002001002T14')
--and cod_listaprecios = 01
;


--provincias
SELECT * from ciudades c2
where codigo_provincia = 20 
--and codigo_ciudad = ''
;




insert into dbo.clientes_registro(cedula,nombres,codprovincia,codciudad,direccion,correo,telefono,clave,fecha,direccion_entrega,latitud,longitud,estado)
values ('13072207', 'ivan hernandez', '20', '002', 'valle lili', 'ivan.hernandez.coral@gmail.com', '3014317636', '12345', getdate(), 'calle 45', null, null, 1)
;

SELECT * from dbo.clientes_registro cr ;


cedula); --ruc
nombre);
provincia);
ciudad);
direccion);
correo); 
telefono); 
password);
fechaNacimiento);
direccion); --direccion entrega

  
--Por implementar en la tabla dbo.clientes_registro
sexo = F-M
politicas = 1-0
pais = 
fechaNacimiento = ?





SELECT * from pedido_encabezado;


SELECT * from pedido_detalle;







/* Validar bien si no tengo q mirar las tablas para nada
SELECT *
FROM dbo.clientes c 
WHERE 1=1
and c.codigo > 2468
;

SELECT *
FROM dbo.direcciones d 
where 1=1
and codigo in (2500, 2503)
;
*/

SELECT * from dbo.parroquias p ;


imbabura--> ibarra--> zuleta







