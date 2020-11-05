// package com.ihc.apirest.controllers;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.List;
// import java.util.Map;
// import java.util.Set;

// import com.ihc.apirest.models.Articulo;
// import com.ihc.apirest.repository.ArticuloRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/api")
// @CrossOrigin("*")
// public class GeografiaRestController 
// {
//   @Autowired
//   ArticuloRepository articuloRepository;



//   /**
//    * Método que permite obtener un articulo según su Id
//    * 
//    * @param idArticulo, Id Articulo con el cual se buscara el articulo en BD
//    * @return Articulo encontrado
//    */
//   @GetMapping(value = "/articulos")
//   public ResponseEntity<List<Articulo>> findAllCategoriasArticulos() 
//   {
//     try 
//     {
//       Map<String, Articulo> mapArticulos = new HashMap<String, Articulo>();

//       List<Articulo> lstArticulos = articuloRepository.getAllArticulos();

//       for (Articulo articulo : lstArticulos) 
//       {
//         try
//         {
//           if(mapArticulos.containsKey(articulo.getCodigoArticulo()))
//           {
//             Articulo articuloBD = mapArticulos.get(articulo.getCodigoArticulo());

//             articuloBD.getLstTallas().add(articulo.getTalla());
  
//             mapArticulos.put(articulo.getCodigoArticulo(), articuloBD);
//           }
//           else
//           {
//             Set<String> lstTallas = new HashSet<String>();
//             lstTallas.add(articulo.getTalla());
            
//             articulo.setTalla(null);
//             articulo.setLstTallas(lstTallas);

//             articulo.setCodigo(articulo.getCodigoArticulo());
//             articulo.setCodigoArticulo(null);

//             mapArticulos.put(articulo.getCodigo(), articulo);
//           }
//         }
//         catch(Exception ex)
//         {
//           System.out.println("ArticuloRestController.findAllCategoriasArticulos()");
//         }
//       }

//       return new ResponseEntity<List<Articulo>>(new ArrayList<Articulo>(mapArticulos.values()), HttpStatus.OK);
//       // return new ResponseEntity<List<Articulo>>(lstArticulos, HttpStatus.OK);
//     } 
//     catch (Exception e) 
//     {
//       return new ResponseEntity<List<Articulo>>(HttpStatus.INTERNAL_SERVER_ERROR);
//     }
//   }
// }