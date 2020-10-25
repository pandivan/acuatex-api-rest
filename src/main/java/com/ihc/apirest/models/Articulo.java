package com.ihc.apirest.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Articulo
{
    @Id
	private String codigo;
	private String codigoArticulo;
	private String grupo;
	private String nombre;
	private String descripcion;
	private int cantidad;
	private Double precio;
	private String talla;

	@Transient
	private Set<String> lstTallas;
}