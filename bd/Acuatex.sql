SELECT *
from dbo.grupo_articulos ga 
;







/******* ARTICULOS ******/

SELECT REPLACE(a.nombre, a.subgrupo, '') nombre, a.*
--a.codigo, SUBSTRING(a.codigo, 0, CHARINDEX('T', a.codigo)+1) as codigo_sin_talla, a.grupo, a.detalle, 1 as cantidad, ROUND(pa.precio, 2) precio, SUBSTRING(a.codigo, CHARINDEX('T', a.codigo)+1, 5) as talla, a.umedida 
from dbo.articulos a 
inner join dbo.precios_articulos pa on pa.cod_articulo = a.codigo 
where 1=1
--a.codigo in ('3002001001T04','3002001002T02','3002001003T08','3003001003T04','3003001005T04','3005004044T18','3005004045T10','3005004047T3','3007002001T12M','3007002001T18M','3007002001T24','3007002001T6-9M','3007002002T12M','3012004001T1','3012005002TL','3012008001TXL','3008007001T14','3004001054TS','3004001058TXL','3005006028T9','3005005086T24','3012008002TXL','3007002066T18','3007002066T12','3007002066T18','3007002066T24','3007002067T12','3007002002T18M','3007002002T24','3007002067T18','3007002067T24','3007002067T6-9','3012005002TM','3012005002TS','3012005002TXL','3012008001TL','3012008001TM','3012008001TS','3012008002TL','3012008002TM','3012008002TS','3004001054TM','3004001054TS','3004001058TL','3004001058TM','3004001058TS') 
and pa.cod_listaprecios = 01
--and a.codigo like '%3002002001T02%'
and a.grupo = 'NIÑO'
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

--DETALLE = NOMBRE ARTÍCULO-DESCRIPCIÓN ARTÍCULO-LOOK 1-LOOK 2-LOOK 3

SELECT a.detalle det, a.* 
from dbo.articulos a 
where 1 = 1 
--a.codigo like '%3002001001T04%'
--AND a.codigo in ('3002001001T04','3002001002T02','3002001003T08','3003001003T04','3003001005T04','3005004044T18','3005004045T10','3005004047T3','3007002001T12M','3007002001T18M','3007002001T24','3007002001T6-9M','3007002002T12M','3012004001T1','3012005002TL','3012008001TXL','3008007001T14','3004001054TS','3004001058TXL','3005006028T9','3005005086T24','3012008002TXL','3007002066T18','3007002066T12','3007002066T18','3007002066T24','3007002067T12','3007002002T18M','3007002002T24','3007002067T18','3007002067T24','3007002067T6-9','3012005002TM','3012005002TS','3012005002TXL','3012008001TL','3012008001TM','3012008001TS','3012008002TL','3012008002TM','3012008002TS','3004001054TM','3004001054TS','3004001058TL','3004001058TM','3004001058TS')
--and a.detalle is not null
;



 
 
 
 




SELECT *
from dbo.parametros
;



/******* CIUDADES ******/
SELECT (c.codigo_provincia+c.codigo_ciudad) as id, c.codigo_pais, c.codigo_provincia, CASE c.codigo_ciudad WHEN '' THEN c.codigo_ciudad ELSE (c.codigo_provincia + '-' + c.codigo_ciudad) END codigo_ciudad, c.nombre 
from ciudades c
where 1=1
and c.codigo_provincia in ('09')
--and (codigo_ciudad = '' or codigo_ciudad = '001')
order by c.codigo_provincia, c.codigo_ciudad, c.nombre 
;




/******* CLIENTES ******/

--POSTMAN
UPDATE dbo.clientes_registro 
set 
--correo = 'ivan.hernandez.coral11@gmail.com', 
--clave= '$2a$10$O3jiJVKkdk/cUOTNndkW9egKXd23hc/Sd64GRFYCpAKEu.EpjywGm',
direccion = 'dir',
--telefono = '22222',
--direccion_entrega = 'dir entrega',
--latitud = 'lt',
cedula = '13072207'
where cedula = '13072207';





ALTER TABLE [dbo].[clientes_registro] ALTER COLUMN clave varchar(400);

ALTER TABLE [dbo].[clientes_registro] ADD sexo varchar(1);
ALTER TABLE [dbo].[clientes_registro] ADD fecha_nacimiento datetime;


--Please enter 6 - 20 characters (A-Z, a-z, 0-9 only)

politicas = 1-0 
fechaNacimiento = ''


TRUNCATE table dbo.clientes_registro ;

TRUNCATE table pedido_encabezado;
TRUNCATE table pedido_detalle;

--

SELECT *
from dbo.clientes_registro 
--where correo like '%carden%'
;





/******* PEDIDOS ******/
SELECT * 
from dbo.pedido_encabezado p
inner join pedido_detalle pd on pd.nro_pedido = p.nro_pedido
where 1=1
--and p.ruc = '13072207'
;

SELECT *
from pedido_encabezado p
where 1 = 1
--and p.nro_pedido = '1010'
--and p.horaEnvio = ''
;


SELECT pd.*, (pd.precio_venta * pd.cantidad * 0.12) iva2 
from pedido_detalle pd
where 1=1
--and pd.cod_articulo = '3007002001T24'
--and pd.nro_pedido = '000000980000003'
;


SELECT a.codigo,pa.precio, a.iva
from dbo.articulos a 
inner join dbo.precios_articulos pa on pa.cod_articulo = a.codigo 
where 1=1
and pa.cod_listaprecios = 03
--and iva <> 1
--and a.codigo in ('3007002001T24')
;


IVA JHOANA = (6 * 8.9285) * 0.12 = 6,42

Precio = 8.9286

Porcentaje Ivan = 12 / 100 = 0,12

IVA = (8.9286 * 0,12) = 1.071

PrecioVenta = 8.9286 + 1.071 = 10.0016




SELECT ROUND((cast(p.valor as float) / 100), 2) valor FROM dbo.parametros p where p.codigo = 'ParamPorcIva';




--DELETE from pedido_encabezado where nro_pedido = '000000980000002';
--DELETE from pedido_detalle where nro_pedido = '000000980000002';


UPDATE dbo.articulos set detalle = null;


--OJO!!! A LOS PRODUCTOS LE ESTAN COLOCANDO - GUION Y NO SE PUEDE PORQUE EL GUION ES RESERVADO PARA SEPARAR
UPDATE dbo.articulos set nombre = REPLACE(nombre,'-',' ')
where codigo in 
(
'3002001001T02','3002001001T14','3002001007T14','3002001008T12','3002001008T14','3002001009T12','3002001009T14','3002001010T12','3002001010T14','3002001011T12','3002001011T14',
'3005002002T12M','3005002002T3M','3005002002T6M','3005002003T12M','3005002003T3M','3005002003T6M','3005002004T12M','3005002004T3M','3005002004T6M','3005002005T1','3005002005T2','3005002005T4','3005002006T12M','3005002006T24M','3005002006T6M','3005002007T12M','3005002007T24M','3005002007T6M',
'3003012005T04','3003012005T06','3003012006T04','3003012006T06','3003012007T04','3003012007T06','3003012012T2','3003013009T10','3003013009T12','3003013009T14','3003013009T16','3003013009T2','3003013009T4','3003013009T6','3003013009T8','3003013010T10','3003013010T12','3003013010T14','3003013010T16','3003013010T2','3003013010T4','3003013010T6','3003013010T8','3003013011T10','3003013011T2','3003013011T4','3003013011T6','3003013011T8',
'3007008001T1','3007008001T2','3007008001T4','3007008002T1','3007008002T2','3007008002T4','3007008003T12','3007008003T18','3007008003T24','3007008004T12','3007008004T18','3007008004T6-9','3007014001T12','3007014002T12','3007008008T18','3007008008T24',
'3004011009TL','3004011009TM','3004011009TS','3004011010TL','3004011010TM','3004011010TS','3004011011TM','3004011011TS','3004011011TXS','3004011012TL','3004011012TM','3004011012TS','3004011013TL','3004011013TM','3004011013TS','3004011014TL','3004011014TM','3004011014TS',
'3012002001TXXL','3012002002TXXL','3012002003TXXL','3012002016TXS','3012002017TXS','3012002018TXS'
)
;

UPDATE dbo.articulos set 
detalle = REPLACE(SUBSTRING(nombre, 0, CHARINDEX('T.', nombre)), subgrupo, '') + '-Producto elaborado con amor y con alta calidad, tela fria, absorve el sudor y dura mil lavadas-3005002003T-3005002002T-3005002004T'
where codigo in 
(
'3002001001T02','3002001001T14','3002001007T14','3002001008T12','3002001008T14','3002001009T12','3002001009T14','3002001010T12','3002001010T14','3002001011T12','3002001011T14',
'3005002002T12M','3005002002T3M','3005002002T6M','3005002003T12M','3005002003T3M','3005002003T6M','3005002004T12M','3005002004T3M','3005002004T6M','3005002005T1','3005002005T2','3005002005T4','3005002006T12M','3005002006T24M','3005002006T6M','3005002007T12M','3005002007T24M','3005002007T6M',
'3003012005T04','3003012005T06','3003012006T04','3003012006T06','3003012007T04','3003012007T06','3003012012T2','3003013009T10','3003013009T12','3003013009T14','3003013009T16','3003013009T2','3003013009T4','3003013009T6','3003013009T8','3003013010T10','3003013010T12','3003013010T14','3003013010T16','3003013010T2','3003013010T4','3003013010T6','3003013010T8','3003013011T10','3003013011T2','3003013011T4','3003013011T6','3003013011T8',
'3007008001T1','3007008001T2','3007008001T4','3007008002T1','3007008002T2','3007008002T4','3007008003T12','3007008003T18','3007008003T24','3007008004T12','3007008004T18','3007008004T6-9','3007014001T12','3007014002T12','3007008008T18','3007008008T24',
'3004011009TL','3004011009TM','3004011009TS','3004011010TL','3004011010TM','3004011010TS','3004011011TM','3004011011TS','3004011011TXS','3004011012TL','3004011012TM','3004011012TS','3004011013TL','3004011013TM','3004011013TS','3004011014TL','3004011014TM','3004011014TS',
'3012002001TXXL','3012002002TXXL','3012002003TXXL','3012002016TXS','3012002017TXS','3012002018TXS'
)
;

--Conjunto Niña Anna-Es una prenda confortable y de temporada-3002001007T-3002001008T-3002001008T

SELECT * from dbo.articulos a where a.detalle is not null;




--SELECT distinct codigo_talla from (
SELECT REPLACE(SUBSTRING(a.nombre, 0, CHARINDEX('T.', a.nombre)), a.subgrupo, '') nombre, a.*
--a.codigo, SUBSTRING(a.codigo, 0, CHARINDEX('T', a.codigo)+1) as codigo_talla, a.grupo, a.detalle, 1 as cantidad, ROUND(pa.precio, 2) precio, SUBSTRING(a.codigo, CHARINDEX('T', a.codigo)+1, 5) as talla, a.umedida, a.iva 
from dbo.articulos a 
inner join dbo.precios_articulos pa on pa.cod_articulo = a.codigo 
where 1=1
--and a.codigo in 
--(
--'3002001001T02','3002001001T14','3002001007T14','3002001008T12','3002001008T14','3002001009T12','3002001009T14','3002001010T12','3002001010T14','3002001011T12','3002001011T14',
--'3005002002T12M','3005002002T3M','3005002002T6M','3005002003T12M','3005002003T3M','3005002003T6M','3005002004T12M','3005002004T3M','3005002004T6M','3005002005T1','3005002005T2','3005002005T4','3005002006T12M','3005002006T24M','3005002006T6M','3005002007T12M','3005002007T24M','3005002007T6M',
--'3003012005T04','3003012005T06','3003012006T04','3003012006T06','3003012007T04','3003012007T06','3003012012T2','3003013009T10','3003013009T12','3003013009T14','3003013009T16','3003013009T2','3003013009T4','3003013009T6','3003013009T8','3003013010T10','3003013010T12','3003013010T14','3003013010T16','3003013010T2','3003013010T4','3003013010T6','3003013010T8','3003013011T10','3003013011T2','3003013011T4','3003013011T6','3003013011T8',
--'3007008001T1','3007008001T2','3007008001T4','3007008002T1','3007008002T2','3007008002T4','3007008003T12','3007008003T18','3007008003T24','3007008004T12','3007008004T18','3007008004T6-9','3007014001T12','3007014002T12','3007008008T18','3007008008T24',
--'3004011009TL','3004011009TM','3004011009TS','3004011010TL','3004011010TM','3004011010TS','3004011011TM','3004011011TS','3004011011TXS','3004011012TL','3004011012TM','3004011012TS','3004011013TL','3004011013TM','3004011013TS','3004011014TL','3004011014TM','3004011014TS',
--'3012002001TXXL','3012002002TXXL','3012002003TXXL','3012002016TXS','3012002017TXS','3012002018TXS'
--) 
and pa.cod_listaprecios = 03
--and a.grupo = 'CABALLERO'
--and a.codigo like '%3012002018T%'
and a.detalle is not null
--) t order by 1
;




--NIÑA= '3002001001T02','3002001001T14','3002001007T14','3002001008T12','3002001008T14','3002001009T12','3002001009T14','3002001010T12','3002001010T14','3002001011T12','3002001011T14'
--BEBE NIÑA= '3005002002T12M','3005002002T3M','3005002002T6M','3005002003T12M','3005002003T3M','3005002003T6M','3005002004T12M','3005002004T3M','3005002004T6M','3005002005T1','3005002005T2','3005002005T4','3005002006T12M','3005002006T24M','3005002006T6M','3005002007T12M','3005002007T24M','3005002007T6M',
--NIÑO='3003012005T04','3003012005T06','3003012006T04','3003012006T06','3003012007T04','3003012007T06','3003012012T2','3003013009T10','3003013009T12','3003013009T14','3003013009T16','3003013009T2','3003013009T4','3003013009T6','3003013009T8','3003013010T10','3003013010T12','3003013010T14','3003013010T16','3003013010T2','3003013010T4','3003013010T6','3003013010T8','3003013011T10','3003013011T2','3003013011T4','3003013011T6','3003013011T8',
--BEBÉ NIÑO='3007008001T1','3007008001T2','3007008001T4','3007008002T1','3007008002T2','3007008002T4','3007008003T12','3007008003T18','3007008003T24','3007008004T12','3007008004T18','3007008004T6-9','3007014001T12','3007014002T12','3007008008T18','3007008008T24',
--DAMA='3004011009TL','3004011009TM','3004011009TS','3004011010TL','3004011010TM','3004011010TS','3004011011TM','3004011011TS','3004011011TXS','3004011012TL','3004011012TM','3004011012TS','3004011013TL','3004011013TM','3004011013TS','3004011014TL','3004011014TM','3004011014TS',
--CABALLERO='3012002001TXXL','3012002002TXXL','3012002003TXXL','3012002016TXS','3012002017TXS','3012002018TXS'


--DESCRIPCIÓN = NOMBRE ARTICULO-DESCRIPCION ARTICULO-DETALLE ARTICULO .... PRUEBA-DESCRIPCION

--Producto al cual le colocamos especificaciones y rayas para separar nombre de la descripcion
--3002002001T06 







 /********************************************************************************************************************
 * 
 * PENDIENTES
 * 
 ********************************************************************************************************************/
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
fechaNacimiento = ''


TRUNCATE table dbo.clientes_registro;

SELECT * from dbo.clientes_registro;

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







  
  
  
 /********************************************************************************************************************
 * 
 * DIMENSION CIUDADES
 * 
 ********************************************************************************************************************/

SELECT 
(c.codigo_provincia+c.codigo_ciudad) as ciudades, 
prov.nombre as provincia,
c.nombre as ciudad
from dbo.ciudades c 
inner join dbo.ciudades prov on prov.codigo_provincia = c.codigo_provincia and prov.codigo_ciudad = ''
where (c.codigo_provincia+c.codigo_ciudad) = '10002'
order by c.codigo_provincia, c.codigo_ciudad, c.nombre
;





 /********************************************************************************************************************
 * 
 * DIMENSION CLIENTES
 * 
 ********************************************************************************************************************/

SELECT 
CAST(cedula AS numeric) as clientes,
cedula,
nombres as cliente,
direccion,
correo,
telefono,
direccion_entrega
from dbo.clientes_registro
where cedula = '1'
;




  


/********************************************************************************************************************
 * 
 * DIMENSION ARTICULOS
 * 
 ********************************************************************************************************************/

SELECT a.codigo as articulos, 
a.grupo, 
a.subgrupo,
a.nombre as articulo,  
pa.precio, 
SUBSTRING(a.codigo, CHARINDEX('T', a.codigo)+1, 5) as talla,
a.stock 
from dbo.articulos a 
inner join dbo.precios_articulos pa on pa.cod_articulo = a.codigo 
where 1=1
--and a.codigo in ('3002001001T04','3002001002T02','3002001003T08','3003001003T04','3003001005T04','3005004044T18','3005004045T10','3005004047T3','3007002001T12M','3007002001T18M','3007002001T24','3007002001T6-9M','3007002002T12M','3012004001T1','3012005002TL','3012008001TXL','3008007001T14','3004001054TS','3004001058TXL','3005006028T9','3005005086T24','3012008002TXL','3007002066T18','3007002066T12','3007002066T18','3007002066T24','3007002067T12','3007002002T18M','3007002002T24','3007002067T18','3007002067T24','3007002067T6-9','3012005002TM','3012005002TS','3012005002TXL','3012008001TL','3012008001TM','3012008001TS','3012008002TL','3012008002TM','3012008002TS','3004001054TM','3004001054TS','3004001058TL','3004001058TM','3004001058TS') 
and pa.cod_listaprecios = 01
and a.codigo = '3007002067T6-9'
;

UPDATE dbo.articulos set stock = 18 where codigo = '3007002002T18M';






 /********************************************************************************************************************
 * 
 * HECHOS PEDIDOS
 * 
 ********************************************************************************************************************/
SELECT p.* 
from pedido_encabezado p
where 1=1
--and cliente = '13072207'
--and nro_pedido = '1010'
order by p.cliente ;


SELECT * 
from pedido_detalle pd
;

UPDATE pedido_encabezado set codprovincia = '17', codciudad = '001'
--fecha = getdate()-7 
where 1=1
--and nro_pedido = '1000'
and cliente = '12121212'
;

select getdate();



SELECT 
p.nro_pedido as pedidos,
p.cliente as clientes,
pd.cod_articulo as articulos,
(p.codprovincia + p.codciudad) as ciudades,
p.hora,
pd.cantidad,
pd.precio_venta as valor,
p.fecha as fecha_pedido,
p.fechaDespacho as fecha_despacho,
p.estado
from dbo.pedido_encabezado p
inner join dbo.pedido_detalle pd on pd.nro_pedido = p.nro_pedido
;


Select @@version 













Microsoft SQL Server 2008 R2 (SP1) - 10.50.2500.0 (Intel X86) 
Jun 17 2011 00:57:23 
Copyright (c) Microsoft Corporation
Express Edition on Windows NT 6.1 <X86> (Build 7600: )


Microsoft SQL Server 2008 R2 (SP3) - 10.50.6000.34 (Intel X86) 
Aug 19 2014 12:21:07 
Copyright (c) Microsoft Corporation
Express Edition on Windows NT 6.1 <X86> (Build 7600: )


Microsoft SQL Server 2008 R2 (SP3-GDR) (KB4057113) - 10.50.6560.0 (Intel X86) 
Dec 28 2017 15:45:46 
Copyright (c) Microsoft Corporation
Express Edition on Windows NT 6.1 <X86> (Build 7600: )


	
	