package com.ihc.apirest.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(schema="dbo", name = "pedido_detalle")
public class PedidoDetalle
{
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer secuencia;
    // private String nro_pedido;
    private String codArticulo;
    private String codPromo;
    private String umedida;
    private Double cantidad;
    private Double bonificacion;
    private Double precioVenta;
    private Double porcDescuento;
    private Double ice;
    private Double iva;
    private String detalle;

    @JoinColumn(name = "nro_pedido")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	// @JsonIgnore
    private Pedido pedido;
}
