package com.ihc.apirest.models;

import javax.persistence.Entity;
import javax.persistence.Id;

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
	private String grupo;
	private String nombre;
	private String descripcion;
	private int cantidad;
	private Double precio;
	private String talla;
}