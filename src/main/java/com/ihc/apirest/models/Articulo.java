package com.ihc.apirest.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
// @Table(schema="dbo")
public class Articulo
{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String codigo;
	private String grupo;
	private String nombre;
	private String descripcion;
	private int cantidad;
	private Double precio;
	private String talla;
}