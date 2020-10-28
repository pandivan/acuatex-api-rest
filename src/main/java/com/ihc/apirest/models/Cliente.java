package com.ihc.apirest.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(schema="dbo", name = "clientes_registro")
public class Cliente
{
	@Id
	private String cedula;
	private String nombres;
	@Column(name="codprovincia")
	private String codProvincia;
	@Column(name="codciudad")
	private String codCiudad;
	private String direccion;
	private String correo;
	private String telefono;
	private String clave;
	private Date fecha;
	private String direccionEntrega;
	private String latitud;
	private String longitud;
	private int estado;

	@Transient
	private String claveIngresada;
	@Transient
	private String nuevaClave;
	@Transient
	private String nuevoCorreo;

	//Pendiente por definir en tiny
	// private String sexo;
	// private String pais;
	// private String politicas;


	public Cliente(String cedula) 
	{
		this.cedula = cedula;
	}
}