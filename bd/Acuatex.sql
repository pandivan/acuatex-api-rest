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









#
# JDBC Properties
#


# PostgreSQL Heroku Unicentro
spring.datasource.url=jdbc:sqlserver://138.185.137.170:14300;databaseName=pedidosbddE02
spring.datasource.username=sa
spring.datasource.password=tinigala
spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver

logging.level.org.hibernate.SQL=off



# Indicar el DBMS
spring.jpa.database: sqlserver

# Indica si debe mostrar el log de las consultas sql ejecutadas
spring.jpa.show-sql: true

# Configurar Hibernate, crea el modelo de BD apartir de los entity o models
# # spring.jpa.hibernate.ddl-auto = update

#  mostrar sql 
spring.jpa.properties.hibernate.format_sql=true


# El dialecto SQL que hace que Hibernate genere un mejor SQL para la base de datos elegida
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.SQLServer2012Dialect

# mostrar el error de sql
logging.level.org.hibernate.SQL=DEBUG


# Your desired user name
spring.security.user.name=pandi
# password
spring.security.user.password=pandi
# Role
#spring.security.user.roles = USER

#security.enable-csrf=false

#logging.level.org.springframework.web=DEBUG

server.port=7788