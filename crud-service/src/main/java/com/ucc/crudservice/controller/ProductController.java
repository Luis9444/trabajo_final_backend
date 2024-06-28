package com.ucc.crudservice.controller;


import com.ucc.crudservice.model.Product;
import com.ucc.crudservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping ("api/products")
@RequiredArgsConstructor



public class ProductController {
    private  final ProductService productService; 


    
    @Operation(summary = "Este edpoint se encarga de obtener todos los productos")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)

    
    public List<Product> getProducts(){

        return this.productService.getProducts();
    }

    
    @Operation(summary = "Este edpoint se encarga de crear un nuevo producto.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Producto creado con exito",
                    content = {
                            @Content(mediaType = "application/json", 
                                    schema = @Schema(implementation = Product.class))
                    }),
            @ApiResponse(responseCode = "500", description = "error de parametros", content = @Content),
            @ApiResponse(responseCode = "400", description = "error de la respuesta", content = @Content)
    })

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    

    public ResponseEntity<Object> newProduct (@Valid @RequestBody Product product, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            
            List<String> error = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList()); 
            return  new ResponseEntity<>(error , HttpStatus.BAD_REQUEST);
        }
        return  productService.newProduct(product);

    }




    
    @Operation(summary = "Este edpoint se encarga de actualizar/editar un nuevo producto.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Producto Actualizado con exito",
                    content = {
                            @Content(mediaType = "application/json", 
                                    schema = @Schema(implementation = Product.class))
                    }),
            @ApiResponse(responseCode = "500", description = "error de parametros", content = @Content),
            @ApiResponse(responseCode = "400", description = "error de la respuesta", content = @Content)
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> updateProduct(@PathVariable("id")Long id,@RequestBody Product updateProduct){
        return productService.updateProduct(id,updateProduct);
    }

  @GetMapping ("/{sku}")
  @ResponseStatus(HttpStatus.OK)
  public Product getProductSku(@PathVariable("sku")String sku){
      return productService.getProduct(sku);
  }



    
    @Operation(summary = "Este edpoint se encarga de Eliminar un nuevo producto.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Producto eliminado con exito",
                    content = {
                            @Content(mediaType = "application/json", 
                                    schema = @Schema(implementation = Product.class))
                    }),
            @ApiResponse(responseCode = "500", description = "error de parametros", content = @Content),

    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> deleteProduct(@PathVariable("id")Long id){
        return  productService.deleteProduct(id);
    }
}



