SELECT *
from dbo.grupo_articulos ga 
;







/******+ ARTICULOS ******/

SELECT  
--distinct SUBSTRING(a.codigo, 0, CHARINDEX('T', a.codigo)+1) as codigo_articulo
--a.codigo, SUBSTRING(a.codigo, 0, CHARINDEX('T', a.codigo)+1) as nuevo_codigo, a.grupo, a.nombre, a.nombre as descripcion, 1 as cantidad, pa.precio, SUBSTRING(a.codigo, CHARINDEX('T', a.codigo)+1, 5) as talla, a.fecha 
a.codigo, SUBSTRING(a.codigo, 0, CHARINDEX('T', a.codigo)+1) as codigo_articulo, a.grupo, a.nombre, a.nombre as descripcion, 1 as cantidad, pa.precio, SUBSTRING(a.codigo, CHARINDEX('T', a.codigo)+1, 5) as talla 
from dbo.articulos a
inner join dbo.precios_articulos pa on pa.cod_articulo = a.codigo
where 1=1
--and a.codigo in ('3002001001T04','3002001002T02','3002001003T08','3003001003T04','3003001005T04','3005004044T18','3005004045T10','3005004047T3','3007002001T12M','3007002001T18M','3007002001T24','3007002001T6-9M','3007002002T12M','3012004001T1','3012005002TL','3012008001TXL','3008007001T14','3004001054TS','3004001058TXL','3005006028T9','3005005086T24','3012008002TXL','3007002066T18','3007002066T12','3007002066T18','3007002066T24','3007002067T12','3007002002T18M','3007002002T24','3007002067T18','3007002067T24','3007002067T6-9','3012005002TM','3012005002TS','3012005002TXL','3012008001TL','3012008001TM','3012008001TS','3012008002TL','3012008002TM','3012008002TS','3004001054TM','3004001054TS','3004001058TL','3004001058TM','3004001058TS')
--and a.codigo like '%3004001058T%'
and pa.cod_listaprecios = 01
and pa.precio <> 0
--and a.grupo in ('DAMA')
--and a.nombre like '%BUSO HOMBRE AMAC0002%'
and a.codigo like '%.%'
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


ALTER TABLE [dbo].[clientes_registro] ALTER COLUMN clave varchar(400);



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

SELECT MAX(CAST(nro_pedido AS numeric)) from pedido_encabezado;

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
--where cedula = '13072207'
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




if('S' = descodificado, RGB(255,0,0),Black())


 /********************************************************************************************************************
 * 
 * HECHOS PEDIDOS
 * 
 ********************************************************************************************************************/
SELECT *
from pedido_encabezado p
--where cliente = '13072207'
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















select
        cliente0_.cedula as cedula1_1_,
        cliente0_.clave as clave2_1_,
        cliente0_.codciudad as codciuda3_1_,
        cliente0_.codprovincia as codprovi4_1_,
        cliente0_.correo as correo5_1_,
        cliente0_.direccion as direccio6_1_,
        cliente0_.direccion_entrega as direccio7_1_,
        cliente0_.estado as estado8_1_,
        cliente0_.fecha as fecha9_1_,
        cliente0_.latitud as latitud10_1_,
        cliente0_.longitud as longitu11_1_,
        cliente0_.nombres as nombres12_1_,
        cliente0_.telefono as telefon13_1_ 
    from
        dbo.clientes_registro cliente0_ 
    --where cliente0_.correo='1' and cliente0_.clave='pandi'
;
