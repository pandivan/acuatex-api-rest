SELECT *
from dbo.grupo_articulos ga 
;


SELECT 
SUBSTRING(a.codigo, CHARINDEX('T', a.codigo)+1, 5) talla, a.*
from dbo.articulos a
where 1=1
--and a.codigo like '%XP%'
and a.codigo in ('3012005002TL','3012005002TM','3012005002TS','3012005002TXL','3012008001TL', '3004001001TS', '3004001001TXL', '3005005082T18','3005005083T10','3005005083T2', '3007002001T12M','3007002001T18M','3007002001T24', '3003001002T06','3003001003T04','3003001005T08', '3002001001T08','3002001001T10','3002001001T12')
--and grupo != 'NIÑA'
--and fecha BETWEEN CONVERT(DATETIME,'01/01/2020') and CONVERT(DATETIME,'31/12/2020')
order by 1
;


SELECT SUBSTRING('3002011002T10', CHARINDEX('T', '3002011002T10')+1, 5), '3002011002T10'; 



/******+ ARTICULOS ******/

SELECT  
a.codigo, SUBSTRING(a.codigo, 0, CHARINDEX('T', a.codigo)+1) as nuevo_codigo, a.grupo, a.nombre, a.nombre as descripcion, 1 as cantidad, pa.precio, SUBSTRING(a.codigo, CHARINDEX('T', a.codigo)+1, 5) as talla, a.fecha 
from dbo.articulos a
inner join dbo.precios_articulos pa on pa.cod_articulo = a.codigo
where 1=1
and a.codigo in ('3012005002TL','3012005002TM','3012005002TS','3012005002TXL','3012008001TL', '3004001001TS', '3004001001TXL', '3005005082T18','3005005083T10','3005005083T2', '3007002001T12M','3007002001T18M','3007002001T24', '3003001002T06','3003001003T04','3003001005T08', '3002001001T08','3002001001T10','3002001001T12')
and pa.cod_listaprecios = 01
order by 1
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
where 1=1
and codigo_provincia in (01,02)
and (codigo_ciudad = '' or codigo_ciudad = '001')
;




insert into dbo.clientes_registro(cedula,nombres,codprovincia,codciudad,direccion,correo,telefono,clave,fecha,direccion_entrega,latitud,longitud,estado)
values ('13072207', 'ivan hernandez', '20', '002', 'valle lili', 'ivan.hernandez.coral@gmail.com', '3014317636', '12345', getdate(), 'calle 45', null, null, 1)
;

SELECT * from dbo.clientes_registro cr order by 1;

--TRUNCATE table dbo.clientes_registro ;

UPDATE dbo.clientes_registro set correo = 'luisa.hernandez.cardenas@gmail.com', clave= '12345' where cedula = '12121212';


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




SELECT * from pedido_encabezado;


SELECT * from pedido_detalle;


"nro_pedido": "",
"fecha": "",
"hora": "",
"fechaEnvio": "",
"horaEnvio": "",
"cliente": "",
"sucursal": "",
"ruc": "",
"nombre": "",
"direccion": "",
"correo": "",
"telefono": "",
"codprovincia": "",
"codciudad": "",
"direccion_entrega": "",
"telefono_entrega": "",
"codprovincia_entrega": "",
"codciudad_entrega": "",
"dias_plazo": "",
"detalle": "",
"estado": "",
"vendedor": "",
"fechaDespacho": "",




"nro_pedido": "",
"secuencia": "",
"cod_articulo": "",
"cod_promo": "",
"umedida": "",
"cantidad": "",
"bonificacion": "",
"precio_venta": "",
"porc_descuento": "",
"ice": "",
"iva": "",
"detalle: "";


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







