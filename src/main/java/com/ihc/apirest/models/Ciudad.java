package com.ihc.apirest.models;

import java.io.Serializable;

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
@Table(schema="dbo", name = "ciudades")
public class Ciudad implements Serializable
{
  private static final long serialVersionUID = 1L;

  // @EmbeddedId CiudadPK id;
  @Id
  private String id;
	private String codigoPais;
  private String nombre;
  private String codigoCiudad;
  private String codigoProvincia;
  

  public String getCodigoCiudad() 
  {
    String idCiudad = codigoCiudad;

    if(!"".equals(codigoCiudad))
    {
      idCiudad = codigoProvincia + "-" + codigoCiudad;
    }
      return idCiudad;
  }
}



// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// @ToString
// @Embeddable
// class CiudadPK implements Serializable 
// {
//   private static final long serialVersionUID = 1L;
//   private String codigoCiudad_;
//   private String codigoProvincia_;



  
// }


