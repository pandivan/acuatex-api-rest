package com.ihc.apirest.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(schema="dbo", name = "clientes_registro")
public class Cliente
{
	@Id
	private String cedula;
	private String nombres;
	private String codProvincia;
	private String codCiudad;
	private String direccion;
	private String correo;
	private String telefono;
	private String clave;
	private Date fechaNacimiento;
	private String direccionEntrega;
	private String latitud;
	private String longitud;
	private int estado;


	//Pendiente por definir en tiny
	// private String sexo;
	// private String pais;
	// private String politicas;


	public Cliente(String cedula) 
	{
		this.cedula = cedula;
	}
}