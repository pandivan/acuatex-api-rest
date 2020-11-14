SELECT *
from dbo.grupo_articulos ga 
;






/******+ ARTICULOS ******/

SELECT  
distinct SUBSTRING(a.codigo, 0, CHARINDEX('T', a.codigo)+1) as codigo_articulo
--a.codigo, SUBSTRING(a.codigo, 0, CHARINDEX('T', a.codigo)+1) as nuevo_codigo, a.grupo, a.nombre, a.nombre as descripcion, 1 as cantidad, pa.precio, SUBSTRING(a.codigo, CHARINDEX('T', a.codigo)+1, 5) as talla, a.fecha 
--a.codigo, SUBSTRING(a.codigo, 0, CHARINDEX('T', a.codigo)+1) as codigo_articulo, a.grupo, a.nombre, a.nombre as descripcion, 1 as cantidad, pa.precio, SUBSTRING(a.codigo, CHARINDEX('T', a.codigo)+1, 5) as talla 
from dbo.articulos a
inner join dbo.precios_articulos pa on pa.cod_articulo = a.codigo
where 1=1
--and a.codigo in ('3002001001T04','3002001002T02','3002001003T08','3003001003T04','3003001005T04','3005004044T18','3005004045T10','3005004047T3','3007002001T12M','3007002002T12M','3012004001T1','3012005002TL','3012008001TXL','3008007001T14','3004001054TS','3004001058TXL','3005006028T9','3005005086T24','3012008002TXL','3007002066T18','3007002067T12')
and pa.cod_listaprecios = 01
and pa.precio <> 0
--and a.grupo in ('BEBÉ NIÑO')
and a.nombre like '%BUSO HOMBRE AMAC0002%'
order by 1
;


SELECT MAX(nro_pedido) from pedido_encabezado;



SELECT *
FROM dbo.lista_precios lp 
;

SELECT * 
FROM dbo.precios_articulos pa 
where 1=1
--and cod_articulo in ('3002001002T12', '3002001002T14')
--and cod_listaprecios = 01
;






--Ciudades
SELECT (c.codigo_provincia+c.codigo_ciudad) as id, c.codigo_pais, c.codigo_provincia, CASE c.codigo_ciudad WHEN '' THEN c.codigo_ciudad ELSE (c.codigo_provincia + '-' + c.codigo_ciudad) END codigo_ciudad, c.nombre 
from ciudades c
where 1=1
and c.codigo_provincia in ('09')
--and (codigo_ciudad = '' or codigo_ciudad = '001')
order by c.codigo_provincia, c.codigo_ciudad, c.nombre 
;




SELECT * from dbo.clientes_registro cr order by 1;

--TRUNCATE table dbo.clientes_registro ;



UPDATE dbo.clientes_registro set correo = 'luisa.hernandez.cardenas@gmail.com', clave= '12345' where cedula = '12121212';

DELETE from dbo.clientes_registro where cedula = 'c';





SD618680-->sd

cedula); --ruc
nombre);
provincia);
ciudad);
direccion);
correo); 
telefono); 
password);
fechaNacimiento); --fecha registro ?
direccion); --direccion entrega

  
--Por implementar en la tabla dbo.clientes_registro
sexo = F-M
politicas = 1-0
pais = 
fechaNacimiento = ''


	cedula
	nombres
	codprovincia
	codciudad
	direccion
	correo
	telefono
	clave
fecha
	direccion_entrega
latitud
longitud
estado

0 !== lstPedidos.length ?


TRUNCATE table pedido_encabezado;
TRUNCATE table pedido_detalle;


SELECT * 
from pedido_encabezado p
inner join pedido_detalle pd on pd.nro_pedido = p.nro_pedido
;

SELECT *
from pedido_encabezado p;


SELECT * 
from pedido_detalle pd
;

SELECT  
pd.nro_pedido, pd.secuencia, SUBSTRING(a.codigo, 0, CHARINDEX('T', a.codigo)+1) as cod_articulo, pd.cantidad, pd.precio_venta, pd.iva, pd.detalle
from dbo.articulos a
inner join pedido_detalle pd on pd.cod_articulo = a.codigo
where 1=1
order by 1 desc
;


SELECT * from dbo.articulos a where a.codigo in ('3003001003TL','3012008001TL','3005005082TL');





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




SD622967 -> caso sp


